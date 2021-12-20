package ta.expressions.order.basic;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import ta.expressions.order.Order;

public class BasicOrder implements Order {

	private final long timestamp;

	public BasicOrder(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public long timestamp() {
		return timestamp;
	}
	
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
	
}
