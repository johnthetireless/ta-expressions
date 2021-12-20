package ta.expressions.order;

public interface OneTriggersOtherOrder extends Order {

	TradingOrder first();
	TradingOrder second();
}
