package ta.expressions.order.basic;

import java.math.BigDecimal;
import java.time.Instant;

import ta.expressions.core.Aggregate;

public class ClosePriceOnlyAggregate implements Aggregate {

	private final BigDecimal close;
	private final long timestamp;  //in millis for testing only

	public ClosePriceOnlyAggregate(double close) {
		this(BigDecimal.valueOf(close));
	}

	public ClosePriceOnlyAggregate(BigDecimal close) {
		this(close, Instant.now().toEpochMilli());
	}

	public ClosePriceOnlyAggregate(BigDecimal close, long timestamp) {
		this.close = close;
		this.timestamp = timestamp;
	}

	@Override
	public BigDecimal open() {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal high() {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal low() {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal close() {
		return close;
	}

	@Override
	public BigDecimal volume() {
		return BigDecimal.ZERO;
	}

	@Override
	public long timestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "ClosePriceOnlyAggregate [close=" + close + ", timestamp=" + timestamp + "]";
	}

}
