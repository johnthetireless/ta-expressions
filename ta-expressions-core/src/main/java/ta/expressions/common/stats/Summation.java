package ta.expressions.common.stats;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.ArithmeticExpression;
import ta.expressions.core.NumericExpression;

/**
 * Calculates the sum of the last N values of another expression.
 * 
 */
public class Summation extends ArithmeticExpression {
	
	public static final String KEYWORD = "Summation";
	
	private final NumericExpression e;
	private final int n;
	
	public Summation(NumericExpression e, int n) {
		super(functionRepresentation(KEYWORD, e, n));
		this.e = e;
		this.n = n;
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		MathContext mc = context.getMathContext();
		BigDecimal previousValue = getValue(context, index - 1);
		if ( previousValue == null ) {
			BigDecimal firstInput = e.getValue(context, index - n + 1);
			if ( firstInput == null ) {
				return null;
			}
			BigDecimal sum = firstInput;
			int i = index - n + 2;
			while ( i <= index && sum != null ) {
				BigDecimal nextInput = e.getValue(context, i);
				if ( nextInput == null ) {  // should not happen, but..
					sum = null;
				}
				else {
					sum = sum.add(nextInput, mc);
				}
				i++;
			}
			return sum;
		}
		else {
			BigDecimal currentInput = e.getValue(context, index);
			BigDecimal oldInput = e.getValue(context, index - n);
			if ( currentInput == null || oldInput == null ) {
				return null;
			}
			BigDecimal sum = previousValue.add(currentInput, mc);
			sum = sum.subtract(oldInput, mc);
			return sum;
		}
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of(e);
	}
}
