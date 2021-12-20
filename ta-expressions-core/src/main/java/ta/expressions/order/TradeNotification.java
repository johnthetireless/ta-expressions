package ta.expressions.order;

import java.math.BigDecimal;

public interface TradeNotification {
	
	TradingOrder order();
	BigDecimal fillPrice();
	long timestamp();

}
