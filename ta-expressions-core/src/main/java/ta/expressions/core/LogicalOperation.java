package ta.expressions.core;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BinaryOperator;

class LogicalOperation extends BooleanExpression {
	
	public static LogicalOperation and(BooleanExpression e1, BooleanExpression e2) {
		return new LogicalOperation("AND", (a, b) -> a && b, e1, e2);
	}
	
	public static LogicalOperation or(BooleanExpression e1, BooleanExpression e2) {
		return new LogicalOperation("OR", (a, b) -> a || b, e1, e2);
	}
	
	private final BinaryOperator<Boolean> operator;
	private final BooleanExpression e1;
	private final BooleanExpression e2;

	private LogicalOperation(String symbol, BinaryOperator<Boolean> operator, 
			BooleanExpression e1, BooleanExpression e2) {
		super(operationRepresentation(symbol, e1, e2));
		this.operator = operator;
		this.e1 = e1;
		this.e2 = e2;
	}


	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		Boolean v1 = e1.getValue(context, index);
		Boolean v2 = e2.getValue(context, index);
		if ( v1 == null || v2 == null ) {
			return null;
		}
		return operator.apply(v1, v2);
	}

	@Override
	public Set<NumericExpression> terms() {
		Set<NumericExpression> s = new HashSet<>();
		s.addAll(e1.terms());
		s.addAll(e2.terms());
		return s;
	}


}
