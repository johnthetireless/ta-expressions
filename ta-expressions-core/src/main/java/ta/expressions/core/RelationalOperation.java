package ta.expressions.core;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * >, >=, <. <=, ==, !=
 *
 */
class RelationalOperation extends BooleanExpression {
	
	public static BooleanExpression greaterThan(NumericExpression e1, NumericExpression e2) {
		return new RelationalOperation(">", (a,b) -> a.compareTo(b) > 0, e1, e2);
	}
	
	public static BooleanExpression lessThan(NumericExpression e1, NumericExpression e2) {
		return new RelationalOperation("<", (a,b) -> a.compareTo(b) < 0, e1, e2);
	}
	
	public static BooleanExpression greaterOrEqual(NumericExpression e1, NumericExpression e2) {
		return new RelationalOperation(">=", (a,b) -> a.compareTo(b) >= 0, e1, e2);
	}
	
	public static BooleanExpression lessOrEqual(NumericExpression e1, NumericExpression e2) {
		return new RelationalOperation("<=", (a,b) -> a.compareTo(b) <= 0, e1, e2);
	}
	
	public static BooleanExpression equalTo(NumericExpression e1, NumericExpression e2) {
		return new RelationalOperation("==", (a,b) -> a.compareTo(b) == 0, e1, e2);
	}
	
	public static BooleanExpression notEqualTo(NumericExpression e1, NumericExpression e2) {
		return new RelationalOperation("!=", (a,b) -> a.compareTo(b) != 0, e1, e2);
	}
	
	private final BiPredicate<BigDecimal, BigDecimal> predicate;
	private final NumericExpression e1;
	private final NumericExpression e2;
	
	private RelationalOperation(String symbol, BiPredicate<BigDecimal, BigDecimal> predicate, NumericExpression e1, NumericExpression e2) {
		super(operationRepresentation(symbol, e1, e2));
		this.predicate = predicate;
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		BigDecimal v1 = e1.getValue(context, index);
		BigDecimal v2 = e2.getValue(context, index);
		if ( v1 == null || v2 == null ) {
			return null;
		}
		return predicate.test(v1, v2);
	}

	@Override
	public Set<NumericExpression> terms() {
		return Set.of(e1, e2);
	}
	
	

}
