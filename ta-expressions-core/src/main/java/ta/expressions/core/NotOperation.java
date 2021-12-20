package ta.expressions.core;

import java.util.Set;

/**
 * NOT
 * <P>
 * This is created by calling not() on a Boolean expression.  One can usually avoid needing to use this.
 *
 */
class NotOperation extends BooleanExpression {

	public static final String KEYWORD = "NOT";

	private final BooleanExpression e;
	
	public NotOperation(BooleanExpression e) {
		super(functionRepresentation(KEYWORD, e));
		this.e = e;
	}

	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		Boolean v = e.getValue(context, index);
		return v == null ? false : !v;
	}

	@Override
	public Set<NumericExpression> terms() {
		return e.terms();
	}

}
