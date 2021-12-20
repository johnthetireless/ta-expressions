package ta.expressions.order.basic;

import java.math.BigDecimal;

import ta.expressions.order.MarketOrder;

public class BasicMarketOrder extends BasicTradingOrder implements MarketOrder {

	public BasicMarketOrder(String symbol, Action action, long timestamp) {
		super(symbol, action, timestamp);
	}
	
	@Override
	public boolean fillAt(BigDecimal price) {
		return true;
	}

	@Override
	public String toString() {
		return "BasicMarketOrder [action=" + action() + " orderDate=" + date() + "]";
	}


}
