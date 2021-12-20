package ta.expressions.common.stats;

import java.math.BigDecimal;
import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.ArithmeticExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.HighPrice;

/**
 * When evaluated, an object of this class will return the highest of the n most recent values of its operand.
 * This is most commonly used to get the highest high price over the last n periods. 
 *
 */
public class HighestValue extends ArithmeticExpression {
	
	public static HighestValue highestHigh(int n) {
		return new HighestValue(HighPrice.INSTANCE, n);
	}

	private final NumericExpression e;
	private final int n;
	
	public HighestValue(NumericExpression e, int n) {
		super(functionRepresentation("Highest", e, n));
		this.e = e;
		this.n = n;
	}


	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		//TODO: this can be done somewhat recursively; this iteration might be slow for large n
		int start = index - n + 1;
		BigDecimal highest = e.getValue(context, start);
		if ( highest != null ) {
			int i = start + 1;
			while ( i <= index && highest != null ) {
				BigDecimal v = e.getValue(context, i);
				if ( v == null ) { // this really shouldn't happen
					highest = null;
				}
				else {
					highest = highest.max(v);
				}
				i++;
			}			
		}
		return highest;
		
	}
	
	@Override
	public Set<NumericExpression> operands() {
		return Set.of(e);
	}
	


}
