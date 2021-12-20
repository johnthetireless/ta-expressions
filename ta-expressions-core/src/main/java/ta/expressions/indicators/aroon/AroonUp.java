package ta.expressions.indicators.aroon;

import java.math.BigDecimal;
import java.util.Set;

import ta.expressions.common.stats.HighestValue;
import ta.expressions.core.AnalysisContext;
import ta.expressions.core.AnalyticExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.HighPrice;

public class AroonUp extends AnalyticExpression {
	
	public static final String KEYWORD = "AroonUp";

	public static AroonUp fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new AroonUp(ps.intValue(0));
	}
	
	private final NumericExpression highest;
	private final int n;

	public AroonUp(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.highest = HighestValue.highestHigh(n + 1);
		this.n = n;
	}
	
//	public AroonUp(NumericExpression e, int n) {
//		super(functionRepresentation(KEYWORD, e, n));
//		this.highest = new HighestValue(e, n + 1);
//		this.n = n;	
//	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		BigDecimal highValue = highest.getValue(context, index);
		if ( highValue == null ) {
			return null;
		}
		int i = index;
		boolean found = false;
		while ( !found && i >= index - n ) {
			BigDecimal v = HighPrice.INSTANCE.getValue(context, i);
			if ( v == null ) { // should be impossible or highestHigh would not have value
				return null;
			}
			if ( highValue.compareTo(v) == 0 ) { //sb the same object, but maybe not
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
		return Set.of(HighestValue.highestHigh(n + 1));
	}



}
