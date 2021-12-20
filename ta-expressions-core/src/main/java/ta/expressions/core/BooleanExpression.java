package ta.expressions.core;

import java.util.Set;

public abstract class BooleanExpression extends AbstractExpression<Boolean> {

	public BooleanExpression(String representation) {
		super(representation);
	}

	@Override
	public Boolean getValue(AnalysisContext context, int index) {
		return evaluate(context, index);
	}

	public BooleanExpression and(BooleanExpression other) {
		return LogicalOperation.and(this, other);
	}
	
	public BooleanExpression or(BooleanExpression other) {
		return LogicalOperation.or(this, other);
	}
	
	public BooleanExpression not() {
		return new NotOperation(this);
	}
	
	public abstract Set<NumericExpression> terms();
}
