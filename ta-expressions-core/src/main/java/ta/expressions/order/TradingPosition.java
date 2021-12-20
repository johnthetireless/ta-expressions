package ta.expressions.order;

public interface TradingPosition {

	String symbol();
	boolean isOpen();
	default boolean notOpen() { return !isOpen(); }
}
