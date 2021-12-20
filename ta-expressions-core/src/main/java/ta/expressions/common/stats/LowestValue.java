package ta.expressions.common.stats;

import java.math.BigDecimal;
import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.ArithmeticExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.LowPrice;

/**
 * When evaluated, an object of this class will return the lowest of the n most recent values of its operand.
 * This is most commonly used to get the lowest low price over the last n periods. 
 *
 */
public class LowestValue extends ArithmeticExpression {

	public static LowestValue lowestLow(int n) {
		return new LowestValue(LowPrice.INSTANCE, n);
	}
	
	private final NumericExpression e;
	private final int n;
	
	public LowestValue(NumericExpression e, int n) {
		super(functionRepresentation("Lowest", e, n));
		this.e = e;
		this.n = n;
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		//TODO: this can be done somewhat recursively; this iteration might be slow for large n
		int start = index - n + 1;
		BigDecimal lowest = e.getValue(context, start);
		if ( lowest != null ) {
			int i = start + 1;
			while ( i <= index && lowest != null ) {
				BigDecimal v = e.getValue(context, i);
				if ( v == null ) { // this really shouldn't happen
					lowest = null;
				}
				else {
					lowest = lowest.min(v);
				}
				i++;
			}			
		}
		return lowest;	
	}
	
	@Override
	public Set<NumericExpression> operands() {
		return Set.of(e);
	}
	


}
