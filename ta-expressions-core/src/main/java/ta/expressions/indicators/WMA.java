package ta.expressions.indicators;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.AnalyticExpression;
import ta.expressions.core.NumericExpression;

public class WMA extends AnalyticExpression {
	
	private final NumericExpression e;
	private final int n;
	private final BigDecimal sumN;

	public WMA(NumericExpression e, int n) {
		super(functionRepresentation("WMA", e, n));
		this.e = e;
		this.n = n;
		this.sumN = BigDecimal.valueOf(((n + 1) * n) / 2);
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		int firstIndex = index - n + 1;
//		System.out.println("index= " + index);
//		System.out.println("firstIndex= " + firstIndex);
		BigDecimal firstValue = e.getValue(context, firstIndex);
		if ( firstValue == null ) {
			return null; // not enough input values
		}
		MathContext mc = context.getMathContext();
		BigDecimal sum = firstValue;
		int i = 2;
		while ( i <= n  ) {
			BigDecimal v = e.getValue(context, firstIndex + i - 1);
			if ( v == null ) {
				return null;
			}
			BigDecimal w = BigDecimal.valueOf(i);
			BigDecimal wv = v.multiply(w, mc);
			sum = sum.add(wv, mc);
			i++;
		}
		return sum.divide(sumN, mc);
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of(e);
	}
	
	

}
