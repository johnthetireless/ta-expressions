package ta.expressions.app;

import java.math.BigDecimal;

import ta.expressions.core.Aggregate;

public class BasicAggregate implements Aggregate {

	private final BigDecimal openPrice;
	private final BigDecimal highPrice;
	private final BigDecimal lowPrice;
	private final BigDecimal closePrice;
	private final BigDecimal volume;
	private final long timestamp;
	
	public BasicAggregate(BigDecimal openPrice, BigDecimal highPrice, BigDecimal lowPrice, BigDecimal closePrice,
			BigDecimal volume, long timestamp) {
		this.openPrice = openPrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		this.volume = volume;
		this.timestamp = timestamp;
	}
	
	public BigDecimal open() {
		return openPrice;
	}
	
	public BigDecimal high() {
		return highPrice;
	}
	
	public BigDecimal low() {
		return lowPrice;
	}
	
	public BigDecimal close() {
		return closePrice;
	}
	
	public BigDecimal volume() {
		return volume;
	}
	
	public long timestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("open: ").append(openPrice.toString());
		sb.append(" high: ").append(highPrice.toString());
		sb.append(" low: ").append(lowPrice.toString());
		sb.append(" close: ").append(closePrice.toString());
		sb.append(" volume: ").append(volume.toString());
		sb.append(" timestamp: ").append(timestamp);
		return sb.toString();
	}

	
}
