package ta.expressions.order.basic;

import java.math.BigDecimal;

import ta.expressions.order.LimitOrder;

public class BasicLimitOrder extends BasicTradingOrder implements LimitOrder {

	private final BigDecimal limitPrice;

	public BasicLimitOrder(String symbol, Action action, BigDecimal limitPrice, long timestamp) {
		super(symbol, action, timestamp);
		this.limitPrice = limitPrice;
	}

	public BasicLimitOrder(String symbol, Action action, double limit, long timestamp) {
		this(symbol, action, BigDecimal.valueOf(limit), timestamp);
	}

	@Override
	public BigDecimal limitPrice() {
		return limitPrice;
	}

	
	
	@Override
	public boolean fillAt(BigDecimal price) {
		if ( isBuy() && buyAt(price) ) return true;
		if ( isSell() && sellAt(price) ) return true;
		return false;
	}

	protected boolean buyAt(BigDecimal price) {
		return price.compareTo(limitPrice) <= 0;
	}

	protected boolean sellAt(BigDecimal price) {
		return price.compareTo(limitPrice) >= 0;
	}

	@Override
	public String toString() {
		return "BasicLimitOrder [action=" + action() + " limitPrice=" + limitPrice + " orderDate= " + date() + "]";
	}
	
	
	
}
