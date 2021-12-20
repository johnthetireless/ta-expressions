package ta.expressions.app;

import java.math.BigDecimal;
import java.util.Map;

import ta.expressions.core.NumericExpression;

public class CalculationResult {
	
	private final int index;
	private final long timestamp;
	private final Map<NumericExpression, BigDecimal> map;

	public CalculationResult(int index, long timestamp, Map<NumericExpression, BigDecimal> map) {
		super();
		this.index = index;
		this.timestamp = timestamp;
		this.map = map;
	}
	
	public int index() {
		return index;
	}
	
	public long timestamp() {
		return timestamp;
	}
	
	public BigDecimal valueOf(NumericExpression e) {
		return map.get(e);
	}

	@Override
	public String toString() {
		return "index: " + index + " timestamp: " + timestamp + " results : " + map;
	}

	
	
}
