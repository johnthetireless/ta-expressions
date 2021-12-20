package ta.expressions.order.basic;

import ta.expressions.order.TradingPosition;

public class BasicTradingPosition implements TradingPosition {

	private final String symbol;
	private boolean open;
	
	public BasicTradingPosition(String symbol, boolean open) {
		super();
		this.symbol = symbol;
		this.open = open;
	}

	@Override
	public String symbol() {
		return symbol;
	}

	@Override
	public boolean isOpen() {
		return open;
	}

	@Override
	public String toString() {
		return "BasicTradingPosition [symbol=" + symbol + ", open=" + open + "]";
	}

}
