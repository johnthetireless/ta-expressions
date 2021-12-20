package ta.expressions.order;

public interface OneCancelsOtherOrder extends Order {

	TradingOrder first();
	TradingOrder second();
}
