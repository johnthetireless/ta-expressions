package ta.expressions.signal;

import java.math.BigDecimal;
import java.util.Map;

import ta.expressions.core.Aggregate;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;

public interface Signal {
	
	int index();
	BooleanExpression cause();
	Map<NumericExpression, BigDecimal> values();
	Aggregate input();
	
	default long timestamp() {
		return input().timestamp();
	}
	
	default boolean causedBy(BooleanExpression e) {
		return cause().equals(e);
	}
}
