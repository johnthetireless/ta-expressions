package ta.expressions.core;

import java.util.Set;

/**
 * This is a shortcut for creating expressions like a > b AND a.previous() < b.previous()
 * 
 * The static factory methods are called by NumericExpression methods.  
 *
 */
abstract class Cross extends BooleanExpression {
	
	public static BooleanExpression over(NumericExpression e1, NumericExpression e2) {
		return new Over(e1, e2);
	}

	public static BooleanExpression under(NumericExpression e1, NumericExpression e2) {
		return new Under(e1, e2);
	}

	protected final NumericExpression e1;
	protected final NumericExpression e2;

	
	public Cross(String symbol, NumericExpression e1, NumericExpression e2) {
		super(operationRepresentation(symbol, e1, e2));
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Set<NumericExpression> terms() {
		return Set.of(e1, e2);
	}
	
	static class Over extends Cross {

		public Over(NumericExpression e1, NumericExpression e2) {
			super("crossedOver", e1, e2);
		}

		@Override
		public Boolean evaluate(AnalysisContext context, int index) {
			return e1.greaterThan(e2).and(e1.previous().lessThan(e2.previous())).evaluate(context, index);
		}

	}
	static class Under extends Cross {

		public Under(NumericExpression e1, NumericExpression e2) {
			super("crossedUnder", e1, e2);
		}

		@Override
		public Boolean evaluate(AnalysisContext context, int index) {
			return e1.lessThan(e2).and(e1.previous().greaterThan(e2.previous())).evaluate(context, index);
		}
	}

}
