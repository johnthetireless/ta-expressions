package ta.expressions.patterns;

import ta.expressions.core.BooleanExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;
import ta.expressions.indicators.variables.OpenPrice;

public class KeyReversal extends Reversal {
	
	private final BooleanExpression formula;
	
	public KeyReversal(TrendType trendType, int minTrendPeriods) {
		super(functionRepresentation("KeyReversal_" + trendType.toString(), minTrendPeriods), 
				trendType, minTrendPeriods);
				
		BooleanExpression openAbovePreviousClose = OpenPrice.INSTANCE.greaterThan(ClosePrice.INSTANCE.previous());
		BooleanExpression closeBelowPreviousLow = ClosePrice.INSTANCE.lessThan(LowPrice.INSTANCE.previous());
		BooleanExpression openBelowPreviousClose = OpenPrice.INSTANCE.lessThan(ClosePrice.INSTANCE.previous());
		BooleanExpression closeAbovePreviousHigh = ClosePrice.INSTANCE.greaterThan(HighPrice.INSTANCE.previous());
		
		this.formula = trendType.equals(TrendType.UPTREND)
				? trend().and(openAbovePreviousClose).and(closeBelowPreviousLow)
				: trend().and(closeAbovePreviousHigh).and(openBelowPreviousClose);
	}

	public BooleanExpression formula() {
		return formula;
	}
	
}
