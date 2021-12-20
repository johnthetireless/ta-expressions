package ta.expressions.order.basic;

import ta.expressions.order.TradingOrder;

public abstract class BasicTradingOrder extends BasicOrder implements TradingOrder {

	private final String symbol;
	private final Action action;
	
	public BasicTradingOrder(String symbol, Action action, long timestamp) {
		super(timestamp);
		this.symbol = symbol;
		this.action = action;
	}

	@Override
	public Action action() {
		return action;
	}

	@Override
	public String symbol() {
		return symbol;
	}


}
