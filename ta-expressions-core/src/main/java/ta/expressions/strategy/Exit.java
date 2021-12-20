package ta.expressions.strategy;

import java.math.BigDecimal;

public class Exit {
	
	private final Entry entry;
	
	private final long timestamp;

	private boolean active = false;
	private long activeDate = -1;
	private BigDecimal activePrice = null;  //actual purchase/sale price; set by position?  should have trade notification
	
	public Exit(Entry entry, long timestamp) {
		this.entry = entry;
		this.timestamp = timestamp;
	}
	
	
}
