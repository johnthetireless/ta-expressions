package ta.expressions.indicators.variables;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Function;

import ta.expressions.core.Aggregate;
import ta.expressions.core.AlgebraicExpression;
import ta.expressions.core.AnalysisContext;
import ta.expressions.core.NumericExpression;

/**
 * Variables provide access to input aggregate values, like close price or volume.
 * <p>
 * There are 5 variables used in TA expressions:
 * <ul>
 * <li>OpenPrice
 * <li>HighPrice
 * <li>LowPrice
 * <li>ClosePrice
 * <li>Volume
 * </ul>
 * <p>
 * Each of these subclasses are implemented as a singleton.
 */
public abstract class Variable extends AlgebraicExpression {

	private final Function<Aggregate, BigDecimal> accessor;
	
	public Variable(String name, Function<Aggregate, BigDecimal> accessor) {
		super(name);
		this.accessor = accessor;
	}
	
//	@Override
//	public BigDecimal getValue(AnalysisContext context, int index) {
//		return evaluate(context, index);
//	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		Aggregate a = context.getInput(index);
		return a == null ? null : accessor.apply(a);
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of();
	}
	
	

}
