package ta.expressions.strategy;

import java.math.BigDecimal;

import ta.expressions.core.Aggregate;

public class Entry {
	
	private final long timestamp;
	
	private BigDecimal highestHigh; // since signalDate; may be used by trailing stop rules
	private BigDecimal lowestLow;
	private BigDecimal highestClose; // trailing stops might be implemented this way?
	private BigDecimal lowestClose;

	private boolean active = false;
	private long activeDate = -1;
	private BigDecimal activePrice = null;  //actual purchase/sale price; set by position?  should have trade notification
	
	public Entry(long timestamp) {
		this.timestamp = timestamp;
	}

	public long timestamp() {
		return timestamp;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public BigDecimal highestHigh() {
		return highestHigh;
	}

	public BigDecimal lowestLow() {
		return lowestLow;
	}

	public BigDecimal highestClose() {
		return highestClose;
	}

	public BigDecimal lowestClose() {
		return lowestClose;
	}

	void update(Aggregate current) {
		updateHighLow(current);
	}
	
	void activate(BigDecimal price, long timestamp ) {
		activePrice = price;
		activeDate = timestamp;
		active = true;
	}
	
	
	private void updateHighLow(Aggregate current) {
		highestHigh = highestHigh == null ? current.high() : highestHigh.max(current.high());
		lowestLow = lowestLow == null ? current.low() : lowestLow.min(current.low());
		highestClose = highestClose == null ? current.close() : highestClose.max(current.close());
		lowestClose = lowestClose == null ? current.close() : lowestClose.min(current.close());
	}
}
