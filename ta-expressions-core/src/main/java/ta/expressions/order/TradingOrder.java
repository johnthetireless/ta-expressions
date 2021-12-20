package ta.expressions.order;

import java.math.BigDecimal;

public interface TradingOrder extends Order {
	
	public enum Action { BUY, SELL };
	
	Action action();
	String symbol();
	
	default BigDecimal quantity() { return BigDecimal.ONE; }
	
	default boolean isBuy() { return action().equals(Action.BUY); }
	default boolean isSell() { return action().equals(Action.SELL); }

}
