package ta.expressions.indicators;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.AnalyticExpression;
import ta.expressions.core.NumericExpression;

//TODO: completely broken; always returns null
public class WMA extends AnalyticExpression {
	
	private final NumericExpression e;
	private final int n;

	public WMA(NumericExpression e, int n) {
		super(functionRepresentation("WMA", e, n));
		this.e = e;
		this.n = n;
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		int firstIndex = index - n + 1;
		BigDecimal firstValue = e.getValue(context, firstIndex);
		if ( firstValue == null ) {
			return null; // not enough input values
		}
		MathContext mc = context.getMathContext();
		BigDecimal sum = firstValue;
		int i = 1;
		while ( i <= n  ) {
			BigDecimal v = e.getValue(context, firstIndex + i);
			if ( v == null ) {
				return null;
			}
			BigDecimal w = BigDecimal.valueOf((double)(i + 1) / n);
			BigDecimal wv = v.multiply(w, mc);
			sum = sum.add(wv, mc);
			i++;
		}
		return sum;
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of(e);
	}
	
	

}
