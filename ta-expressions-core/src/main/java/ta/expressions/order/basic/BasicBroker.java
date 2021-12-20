package ta.expressions.order.basic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ta.expressions.core.Aggregate;
import ta.expressions.order.Broker;
import ta.expressions.order.FillableOrder;
import ta.expressions.order.Order;
import ta.expressions.order.StopOrder;
import ta.expressions.order.TradeNotification;
import ta.expressions.order.TradingOrder;
import ta.expressions.order.TradingPosition;

public class BasicBroker implements Broker {
	
	private static final Logger LOG = LoggerFactory.getLogger(BasicBroker.class);

	private final String symbol;
	private TradingPosition position;
	
	private Aggregate currentAggregate = null;
//	private int currentIndex = -1;
	
	private final List<Order> orders = new ArrayList<>();
	private final List<TradeNotification> tradeNotifications = new ArrayList<>();

	private final List<FillableOrder> unfilledOrders = new ArrayList<>();

	private final List<StopOrder> stoppedOrders = new ArrayList<>();
	
	private TradingOrder.Action firstAction;
	
	private List<String> actions = new ArrayList<>();

	public BasicBroker(String symbol) {
		super();
		this.symbol = symbol;
		this.position = new BasicTradingPosition(symbol, false);
	}

	public void accept(Aggregate a) {
		LOG.trace("Received input: " + a);
		currentAggregate = a;
//		currentIndex++;
		
		List<StopOrder> toconvert = new ArrayList<>(stoppedOrders);
		stoppedOrders.clear();
		for ( StopOrder o : toconvert ) {
			process(o);
		}	
		
		List<FillableOrder> tofill = new ArrayList<>(unfilledOrders);
		unfilledOrders.clear();
		for ( FillableOrder o : tofill ) {
			process(o);
		}	
	}
	
	@Override
	public TradingPosition position() {
		return position;
	}
	
	@Override
	public List<Order> orders() {
		return orders;
	}

	@Override
	public List<TradeNotification> tradeNotifications() {
		return tradeNotifications;
	}

	public String symbol() {
		return symbol;
	}

	public List<FillableOrder> unfilledOrders() {
		return unfilledOrders;
	}

	public List<StopOrder> stoppedOrders() {
		return stoppedOrders;
	}

	public List<String> actions() {
		return actions;
	}
	
	public List<TradeNotification> purchaseNotifications() {
		return tradeNotifications.stream().filter(t -> t.order().isBuy()).collect(Collectors.toList());
	}

	public List<TradeNotification> salesNotifications() {
		return tradeNotifications.stream().filter(t -> t.order().isSell()).collect(Collectors.toList());
	}
	
	public BigDecimal totalPurchases() {
		List<TradeNotification> notes = purchaseNotifications();
		BigDecimal total = BigDecimal.ZERO;
		for ( int i = 0; i < notes.size(); i++ ) {
			total = total.add(notes.get(i).fillPrice());
		}
		return total;
	}

	public BigDecimal totalSales() {
		List<TradeNotification> notes = salesNotifications();
		BigDecimal total = BigDecimal.ZERO;
		for ( int i = 0; i < notes.size(); i++ ) {
			total = total.add(notes.get(i).fillPrice());
		}
		return total;
	}

	public BigDecimal totalProfit() {
		return totalSales().subtract(totalPurchases());
	}

	@Override
	public void accept(Order order) {
		LOG.debug("Received order: " + order);
		orders.add(order);
		if ( order instanceof FillableOrder ) {
			process((FillableOrder) order);
		}
		else if ( order instanceof StopOrder ) {
			process((StopOrder) order);			
		}	
	}
	
	private void process(StopOrder order) {
		if ( currentAggregate == null ) {
			stoppedOrders.add(order);
		}
		else {
			BigDecimal price = currentAggregate.close();
			long timestamp = currentAggregate.timestamp();
			Optional<FillableOrder> fillable = order.convertAt(price, timestamp);
			if ( fillable.isPresent() ) {
				LOG.info("Converted " + order + " to " + fillable.get() + " at " + price);
				actions.add("Converted " + order + " to " + fillable.get() + " at " + price);
				process(fillable.get());
			}
			else {
				stoppedOrders.add(order);				
			}
		}
		
	}
	
	private void process(FillableOrder order) {
		TradeNotification lastNote = null;
		int numNotes = tradeNotifications.size();
		if ( numNotes > 0 ) {
			lastNote = tradeNotifications.get(numNotes - 1);
		}
		if ( lastNote != null && lastNote.order().action().equals(order.action())) {
			LOG.info("Duplicate order? " + order);
			LOG.info(lastNote.toString());
			return;
		}
		
		if ( currentAggregate == null ) {
			unfilledOrders.add(order);
		}
		else {
			BigDecimal price = currentAggregate.close();
			long timestamp = currentAggregate.timestamp();
			if ( order.fillAt(price) ) {
				LOG.info("Filled " + order + " at " + price);
				actions.add("Filled " + order + " at " + price);
				if ( firstAction == null ) {
					firstAction = order.action();
					LOG.debug("First action " + firstAction);	
					position = new BasicTradingPosition(symbol, true);
					LOG.debug("New position " + position);	
				}
				else {
					if ( order.action().equals(firstAction) ) {
						position = new BasicTradingPosition(symbol, true);
						LOG.debug("New position " + position);							
					}
					else {
						position = new BasicTradingPosition(symbol, false);
						LOG.debug("New position " + position);													
					}
				}
				TradeNotification newNote = new BasicTradeNotification(order, price, timestamp);
				LOG.info("New trade notification " + newNote);
				tradeNotifications.add(newNote);
			}
			else {
				unfilledOrders.add(order);				
			}
		}
	}
	

}
