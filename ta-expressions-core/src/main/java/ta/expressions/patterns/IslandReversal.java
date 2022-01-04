package ta.expressions.patterns;

import ta.expressions.core.BooleanExpression;

public class IslandReversal extends Reversal {

	private final BooleanExpression formula;

	public IslandReversal(TrendType trendType, int minTrendPeriods) {
		super(functionRepresentation("IslandReversal_" + trendType.toString(), minTrendPeriods), 
				trendType, minTrendPeriods);
		
		this.formula = trendType.equals(TrendType.UPTREND)
				? trend().and(Gap.DOWN).and(Gap.previousUp(1))
				: trend().and(Gap.UP).and(Gap.previousDown(1));

	}

	public BooleanExpression formula() {
		return formula;
	}

}
