package ta.expressions.core;

import java.util.Set;

class NotOperation extends BooleanExpression {

	private final BooleanExpression e;
	
	public NotOperation(BooleanExpression e) {
		super(functionRepresentation("NOT", e));
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
