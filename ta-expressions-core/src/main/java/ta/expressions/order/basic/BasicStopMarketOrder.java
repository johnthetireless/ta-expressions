package ta.expressions.order.basic;

import java.math.BigDecimal;
import java.util.Optional;

import ta.expressions.order.FillableOrder;
import ta.expressions.order.StopMarketOrder;

public class BasicStopMarketOrder extends BasicTradingOrder implements StopMarketOrder {

	private final BigDecimal triggerPrice;
	
	public BasicStopMarketOrder(String symbol, Action action, BigDecimal triggerPrice, long timestamp) {
		super(symbol, action, timestamp);
		this.triggerPrice = triggerPrice;
	}

	public BasicStopMarketOrder(String symbol, Action action, double trigger, long timestamp) {
		this(symbol, action, BigDecimal.valueOf(trigger), timestamp);
	}

	@Override
	public BigDecimal triggerPrice() {
		return triggerPrice;
	}

	@Override
	public Optional<FillableOrder> convertAt(BigDecimal price, long timestamp) {
		if ( isBuy() && buyAt(price) ) {
			return Optional.of(new BasicMarketOrder(symbol(), Action.BUY, timestamp));
		}
		else if ( isSell() && sellAt(price) ) {
			return Optional.of(new BasicMarketOrder(symbol(), Action.SELL, timestamp));
		}
		return Optional.empty();
	}

	protected boolean buyAt(BigDecimal price) {
		return price.compareTo(triggerPrice) <= 0;
	}

	protected boolean sellAt(BigDecimal price) {
		return price.compareTo(triggerPrice) >= 0;
	}
	
	@Override
	public String toString() {
		return "BasicStopMarketOrder [action()=" + action() + " triggerPrice=" + triggerPrice + "]";
	}

	
	
}
