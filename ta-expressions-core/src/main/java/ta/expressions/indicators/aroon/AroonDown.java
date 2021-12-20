package ta.expressions.indicators.aroon;

import java.math.BigDecimal;
import java.util.Set;

import ta.expressions.common.stats.LowestValue;
import ta.expressions.core.AnalysisContext;
import ta.expressions.core.AnalyticExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.LowPrice;

//TODO: merge most logic into AbstractAroon?
public class AroonDown extends AnalyticExpression {

	public static final String KEYWORD = "AroonDown";

	public static AroonDown fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new AroonDown(ps.intValue(0));
	}
	
	private final NumericExpression lowest;
	private final int n;

	public AroonDown(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.lowest = LowestValue.lowestLow(n + 1);
		this.n = n;
	}

//	public AroonDown(NumericExpression e, int n) {
//		super(functionRepresentation(KEYWORD, e, n));
//		this.lowest = new LowestValue(e, n + 1);
//		this.n = n;		
//	}
	
	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		BigDecimal lowValue = lowest.getValue(context, index);
		if ( lowValue == null ) {
			return null;
		}
		int i = index;
		boolean found = false;
		while ( !found && i >= index - n ) {
			BigDecimal v = LowPrice.INSTANCE.getValue(context, i);
			if ( v == null ) { // should be impossible or highestHigh would not have value
				return null;
			}
			if ( lowValue.compareTo(v) == 0 ) { //sb the same object, but maybe not
				found = true;
			}
			else {
				i--;				
			}
		}
		if ( !found ) { // doubt this is possible
			return null;
		}
		int position = index - i;
		//TODO:: clean this up
		return BigDecimal.valueOf((((double) n - position) / n) * 100);
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of(LowestValue.lowestLow(n + 1));
	}

	
}
