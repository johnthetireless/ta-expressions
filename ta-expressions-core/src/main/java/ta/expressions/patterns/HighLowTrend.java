package ta.expressions.patterns;

import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;

/**
 * A streak of consecutive high and low at least minPeriods long.
 * We can check for a current streak or one that occurred some previous number of periods in the past.
 * The reversal patterns check for high-low streaks up to the previous period.  
 *
 */
public class HighLowTrend extends BooleanExpression {

	public static HighLowTrend current(TrendType trendType, int minPeriods) {
		return new HighLowTrend(trendType, minPeriods, 0);
	}
	
	public static HighLowTrend previous(TrendType trendType, int minPeriods, int previousN) {
		return new HighLowTrend(trendType, minPeriods, previousN);
	}
	
	private final BooleanExpression formula;
	
	private HighLowTrend(TrendType trendType, int minPeriods, int previousN) {
		super(functionRepresentation("HighLow_" + trendType.toString(), minPeriods, previousN));
		NumericExpression highLowStreak = new HighLowStreak();
		
		if ( previousN > 0 ) {
			this.formula = trendType.equals(TrendType.UPTREND) 
					? highLowStreak.previous(previousN).greaterOrEqual(minPeriods)
					: highLowStreak.previous(previousN).lessOrEqual(minPeriods * -1);			
		}
		else {
			this.formula = trendType.equals(TrendType.UPTREND) 
					? highLowStreak.greaterOrEqual(minPeriods)
					: highLowStreak.lessOrEqual(minPeriods * -1);			
			
		}
	}

	public BooleanExpression formula() {
		return formula;
	}
	
	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		return formula.evaluate(context, index);
	}

	@Override
	public Set<NumericExpression> terms() {
		return Set.of();
	}

}
