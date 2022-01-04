package ta.expressions.patterns;

import ta.expressions.core.BooleanExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class PivotPointReversal extends Reversal {

	private final BooleanExpression formula;

	public PivotPointReversal(TrendType trendType, int minTrendPeriods) {
		super(functionRepresentation("PivotPointReversal_" + trendType.toString(), minTrendPeriods), 
				trendType, minTrendPeriods);
		
		BooleanExpression closeBelowPreviousLow = ClosePrice.INSTANCE.lessThan(LowPrice.INSTANCE.previous());
		BooleanExpression closeAbovePreviousHigh = ClosePrice.INSTANCE.greaterThan(HighPrice.INSTANCE.previous());
		BooleanExpression highBelowPreviousHigh = HighPrice.INSTANCE.lessThan(HighPrice.INSTANCE.previous());
		BooleanExpression lowAbovePreviousLow = LowPrice.INSTANCE.greaterThan(LowPrice.INSTANCE.previous());

		this.formula = trendType.equals(TrendType.UPTREND)
				? trend().and(closeBelowPreviousLow).and(highBelowPreviousHigh)
				: trend().and(closeAbovePreviousHigh).and(lowAbovePreviousLow);
	}

	@Override
	public BooleanExpression formula() {
		return formula;
	}

}
