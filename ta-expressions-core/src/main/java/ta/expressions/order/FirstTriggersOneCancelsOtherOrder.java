package ta.expressions.order;

public interface FirstTriggersOneCancelsOtherOrder extends Order {

	TradingOrder first();
	TradingOrder second();
	TradingOrder third();

}
