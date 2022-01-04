package ta.expressions.patterns;

import ta.expressions.core.BooleanExpression;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class HookReversal extends Reversal {

	private final BooleanExpression formula;

	public HookReversal(TrendType trendType, int minTrendPeriods, double nearThreshold) {
		super(functionRepresentation("HookReversal_" + trendType.toString(), minTrendPeriods, nearThreshold),
				trendType, minTrendPeriods);

		BooleanExpression highBelowPreviousHigh = HighPrice.INSTANCE.lessThan(HighPrice.INSTANCE.previous());
		BooleanExpression lowAbovePreviousLow = LowPrice.INSTANCE.greaterThan(LowPrice.INSTANCE.previous());
//		BooleanExpression highAbovePreviousHigh = HighPrice.INSTANCE.greaterThan(HighPrice.INSTANCE.previous());
//		BooleanExpression lowBelowPreviousLow = LowPrice.INSTANCE.lessThan(LowPrice.INSTANCE.previous());

		this.formula = trendType.equals(TrendType.UPTREND)
				? trend().and(openNearHigh(nearThreshold)).and(closeNearLow(nearThreshold))
						.and(highBelowPreviousHigh).and(lowAbovePreviousLow)
				: trend().and(openNearLow(nearThreshold)).and(closeNearHigh(nearThreshold))
						.and(highBelowPreviousHigh).and(lowAbovePreviousLow);
			}

	public BooleanExpression formula() {
		return formula;
	}
	
}
