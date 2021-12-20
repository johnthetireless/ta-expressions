package ta.expressions.common.change;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.BiPredicate;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;

public abstract class RisingFalling extends BooleanExpression {

	private final NumericExpression e;
	private final int n;
	private final int missesAllowed;
	private final BiPredicate<BigDecimal, BigDecimal> comp;

	public RisingFalling(String keyword, NumericExpression e, int n, int missesAllowed, BiPredicate<BigDecimal, BigDecimal> comp) {
		super(functionRepresentation(keyword, e, n, missesAllowed));
		this.e = e;
		this.n = n;
		this.missesAllowed = missesAllowed;
		this.comp = comp;
	}
	
	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		int start = index - n + 1;
		int misses = 0;
		for ( int i = start; i <= index; i++ ) {
			BigDecimal pv = e.getValue(context, i - 1);
			if ( pv == null ) {
				return null;
			}
			BigDecimal cv = e.getValue(context, i);
			if ( cv == null ) {
				return null;
			}
			if ( !comp.test(cv, pv) ) {
				misses++;
			}
		}
		return misses <= missesAllowed;
	}

	@Override
	public Set<NumericExpression> terms() {
		return Set.of(e);
	}



}
