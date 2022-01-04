package ta.expressions.patterns;

import ta.expressions.core.BooleanExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class ClosePriceReversal extends Reversal {

	private final BooleanExpression formula;

	public ClosePriceReversal(TrendType trendType, int minTrendPeriods, double nearThreshold) {
		
		super(functionRepresentation("ClosePriceReversal_" + trendType.toString(), minTrendPeriods, nearThreshold),
				trendType, minTrendPeriods);
		
		BooleanExpression closeAbovePreviousClose = ClosePrice.INSTANCE.greaterThan(ClosePrice.INSTANCE.previous());
		BooleanExpression closeBelowPreviousClose = ClosePrice.INSTANCE.lessThan(ClosePrice.INSTANCE.previous());

		this.formula = trendType.equals(TrendType.UPTREND)
				? trend().and(openNearHigh(nearThreshold)).and(closeNearLow(nearThreshold)).and(closeBelowPreviousClose)
				: trend().and(openNearLow(nearThreshold)).and(closeNearHigh(nearThreshold)).and(closeAbovePreviousClose);
	}

	public BooleanExpression formula() {
		return formula;
	}
	

}
