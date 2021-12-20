package ta.expressions.order.basic;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import ta.expressions.order.TradeNotification;
import ta.expressions.order.TradingOrder;

public class BasicTradeNotification implements TradeNotification {

	private final TradingOrder order;
	private final BigDecimal fillPrice;
	private final long timestamp;
	
	public BasicTradeNotification(TradingOrder order, BigDecimal fillPrice, long timestamp) {
		this.order = order;
		this.fillPrice = fillPrice;
		this.timestamp = timestamp;
	}

	@Override
	public TradingOrder order() {
		return order;
	}

	@Override
	public BigDecimal fillPrice() {
		return fillPrice;
	}

	@Override
	public long timestamp() {
		return timestamp;
	}

	//TODO: transaction superclass of this and order
	public LocalDateTime dateTime() {
		Instant i = Instant.ofEpochSecond(timestamp);
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(i, zone);
	}
	
	public LocalDate date() {
		Instant i = Instant.ofEpochSecond(timestamp);
		ZoneId zone = ZoneId.systemDefault();
		return LocalDate.ofInstant(i, zone);
	}
	
	@Override
	public String toString() {
		return "BasicTradeNotification [order=" + order + ", fillPrice=" + fillPrice + ", fillDate=" + date() + "]";
	}

}
