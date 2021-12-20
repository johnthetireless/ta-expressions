package ta.expressions.core;

import java.util.Set;

class IsNull extends BooleanExpression {

	private final NumericExpression e;

	public IsNull(NumericExpression e) {
		super(functionRepresentation("IsNull", e));
		this.e = e;
	}

	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		return e.getValue(context, index) == null;
	}

	@Override
	public Set<NumericExpression> terms() {
		return Set.of(e);
	}
	

}
