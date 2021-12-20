package ta.expressions.core;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Returns the Nth previous value of an expression.
 * N = 1 in many cases.
 * <p>
 * Previous value is used to define a lot of other numeric expressions.
 * <p>
 * It is also very useful for creating high-level expressions for trading rules.
 * 
 */
//TODO: need a Boolean equivalent, so this will become a subclass of generic xxx
class PreviousValue extends NumericExpression {
	 
	public static final String KEYWORD = "Previous";

	public static PreviousValue of(NumericExpression e, int n) {
		return new PreviousValue(e, n);
	}

	private final NumericExpression e;
	private final int n;
	
	private PreviousValue(NumericExpression e, int n) {
		super(functionRepresentation(KEYWORD, e, n));
		this.e = e;
		this.n = n;
	}

	@Override
	public BigDecimal getValue(AnalysisContext context, int index) {
		return evaluate(context, index);
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		return e.getValue(context, index - n);
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of();
	}
	
	

}
