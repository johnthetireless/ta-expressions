package ta.expressions.order.basic;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ta.expressions.order.TrailingStopDelta;
import ta.expressions.order.TrailingStopDeltaType;

public class BasicTrailingStopDelta implements TrailingStopDelta {

	private static final Logger LOG = LoggerFactory.getLogger(BasicTrailingStopDelta.class);
	
	public static TrailingStopDelta amount(BigDecimal value) {
		return new BasicTrailingStopDelta(TrailingStopDeltaType.AMOUNT, value);
	}
	
	public static TrailingStopDelta percent(BigDecimal value) {
		return new BasicTrailingStopDelta(TrailingStopDeltaType.PERCENT, value);
	}
	
	public static TrailingStopDelta amount(double value) {
		return new BasicTrailingStopDelta(TrailingStopDeltaType.AMOUNT, BigDecimal.valueOf(value));
	}
	
	public static TrailingStopDelta percent(double value) {
		return new BasicTrailingStopDelta(TrailingStopDeltaType.PERCENT, BigDecimal.valueOf(value));
	}
	
	private final TrailingStopDeltaType type;
	private final BigDecimal value;
	
	
	public BasicTrailingStopDelta(TrailingStopDeltaType type, BigDecimal value) {
		this.type = type;
		this.value = value;
	}
	
	

	@Override
	public boolean buyAt(BigDecimal price, BigDecimal triggerPrice) {
		if ( type().equals(TrailingStopDeltaType.AMOUNT) ) {
			LOG.trace("buyAt price = " + price);
			LOG.trace("price - value = " + price.subtract(value()));
			LOG.trace("triggerPrice = " + triggerPrice);
			return price.subtract(value()).compareTo(triggerPrice) >= 0;
		}
		else {
			return price.multiply(BigDecimal.ONE.subtract(value())).compareTo(triggerPrice) <= 0;
		}
	}

	@Override
	public boolean sellAt(BigDecimal price, BigDecimal triggerPrice) {
		if ( type().equals(TrailingStopDeltaType.AMOUNT) ) {
			LOG.trace("sellAt price = " + price);
			LOG.trace("price + value = " + price.add(value()));
			LOG.trace("triggerPrice = " + triggerPrice);
			return price.add(value()).compareTo(triggerPrice) <= 0;
		}
		else {
			return price.multiply(BigDecimal.ONE.subtract(value())).compareTo(triggerPrice) >= 0;
		}
	}

	@Override
	public TrailingStopDeltaType type() {
		return type;
	}

	@Override
	public BigDecimal value() {
		return value;
	}

	@Override
	public String toString() {
		return type + " " + value;
	}

	
	
}
