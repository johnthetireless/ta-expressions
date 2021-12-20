package ta.expressions.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ta.expressions.order.Broker;
import ta.expressions.order.Order;
import ta.expressions.order.TradingPosition;
import ta.expressions.signal.Signal;

public class StrategyExecution implements Consumer<Signal> {

	private final Strategy strategy;
	private final Broker broker;
	
	
	private final List<Order> orders = new ArrayList<>();
	

	public StrategyExecution(Strategy strategy, Broker broker) {
		this.strategy = strategy;
		this.broker = broker;
	}


	@Override
	public void accept(Signal signal) {
		
		TradingPosition position = broker.position();

		List<Order> newOrders = strategy.act(signal, position);
		orders.addAll(newOrders);
		for ( Order o : newOrders ) {
			broker.accept(o);
		}
	}
	
	public List<Order> orders() {
		return orders;
	}
	

}
