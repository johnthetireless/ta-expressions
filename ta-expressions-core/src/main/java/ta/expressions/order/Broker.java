package ta.expressions.order;

import java.util.List;

public interface Broker {

	TradingPosition position();
	
	void accept(Order order);
	
	List<Order> orders();
	List<TradeNotification> tradeNotifications();
}
