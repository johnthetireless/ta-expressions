package ta.expressions.core;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Predicate;

public class NumericPredicate extends BooleanExpression {
	
	public static NumericPredicate isZero(NumericExpression e) {
		return new NumericPredicate("IsZero", a -> a.signum() == 0, e); 
	}

	private final Predicate<BigDecimal> predicate;
	private final NumericExpression e;

	private NumericPredicate(String name, Predicate<BigDecimal> predicate, NumericExpression e) {
		super(functionRepresentation(name, e));
		this.predicate = predicate;
		this.e = e;
	}

	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		BigDecimal v = e.getValue(context, index);
		return v == null ? null : predicate.test(v);
	}

	@Override
	public Set<NumericExpression> terms() {
		// TODO Auto-generated method stub
		return null;
	}

}
