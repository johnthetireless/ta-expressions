package ta.expressions.patterns;

import ta.expressions.core.BooleanExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class OpenCloseReversal extends Reversal {

	private final BooleanExpression formula;

	public OpenCloseReversal(TrendType trendType, int minTrendPeriods, double nearThreshold) {
		
		super(functionRepresentation("OpenCloseReversal_" + trendType.toString(), minTrendPeriods, nearThreshold),
				trendType, minTrendPeriods);
		
		BooleanExpression closeAbovePreviousClose = ClosePrice.INSTANCE.greaterThan(ClosePrice.INSTANCE.previous());
		BooleanExpression closeBelowPreviousClose = ClosePrice.INSTANCE.lessThan(ClosePrice.INSTANCE.previous());

		this.formula = trendType.equals(TrendType.UPTREND)
				? trend().and(openNearHigh(nearThreshold)).and(closeNearLow(nearThreshold)).and(closeAbovePreviousClose)
				: trend().and(openNearLow(nearThreshold)).and(closeNearHigh(nearThreshold)).and(closeBelowPreviousClose);
	}

	public BooleanExpression formula() {
		return formula;
	}
	
}
