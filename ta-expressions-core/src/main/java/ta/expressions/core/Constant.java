package ta.expressions.core;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * Constants are used so we can easily create expressions involving numeric literals,
 * as in:
 * <p>
 * <code>
 * HighPrice.INSTANCE.plus(LowPrice.INSTANCE).dividedBy(2);
 *</code>
 *<p>
 * Constants are usually created via one of the NumericExpression methods that accepts a Number argument.
 * On rare occasions one might need to create a constant explicitly, so this is a public class.
 */
public class Constant extends NumericExpression {

	public static Constant valueOf(Number n) {
		Objects.requireNonNull(n);
		return new Constant(n.toString());
	}
	
	private final BigDecimal value;
	
	private Constant(String s) {
		super(s);
		this.value = new BigDecimal(s);
	}
	
	@Override
	public BigDecimal getValue(AnalysisContext context, int index) {
		return value;
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		return value;
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of();
	}
	
	@Override
	public boolean isConstant() {
		return true;
	}
	


}
