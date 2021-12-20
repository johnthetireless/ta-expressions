package ta.expressions.reversals;

import ta.expressions.core.BooleanExpression;
import ta.expressions.indicators.PriceRange;
import ta.expressions.indicators.aroon.AroonDown;
import ta.expressions.indicators.aroon.AroonUp;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;
import ta.expressions.indicators.variables.OpenPrice;

public class Reversals {

	public static BooleanExpression uptrend(int n, int missesAllowed) {
		BooleanExpression newHigh = new AroonUp(n + 1).equalTo(100);
		return newHigh;
	}
	
	public static BooleanExpression downtrend(int n, int missesAllowed) {
		BooleanExpression newLow = new AroonDown(n + 1).equalTo(100);
		return newLow;
	}
	
	public static BooleanExpression openNearHigh(double percent) {
		return HighPrice.INSTANCE.minus(OpenPrice.INSTANCE)
				.lessThan(PriceRange.INSTANCE.multipliedBy(percent));
	}
	
	public static BooleanExpression openNearLow(double percent) {
		return OpenPrice.INSTANCE.minus(LowPrice.INSTANCE)
				.lessThan(PriceRange.INSTANCE.multipliedBy(percent));
	}
	
	public static BooleanExpression closeNearHigh(double percent) {
		return HighPrice.INSTANCE.minus(ClosePrice.INSTANCE)
				.lessThan(PriceRange.INSTANCE.multipliedBy(percent));
	}
	
	public static BooleanExpression closeNearLow(double percent) {
		return ClosePrice.INSTANCE.minus(LowPrice.INSTANCE)
				.lessThan(PriceRange.INSTANCE.multipliedBy(percent));
	}
	
	public static BooleanExpression higherClose() {
		return ClosePrice.INSTANCE.greaterThan(ClosePrice.INSTANCE.previous());
	}
	
	public static BooleanExpression lowerClose() {
		return ClosePrice.INSTANCE.lessThan(ClosePrice.INSTANCE.previous());
	}
	
	public static BooleanExpression lowerHigh() {
		return HighPrice.INSTANCE.lessThan(HighPrice.INSTANCE.previous());
	}
	
	public static BooleanExpression higherLow() {
		return LowPrice.INSTANCE.greaterThan(LowPrice.INSTANCE.previous());
	}
	
	public static BooleanExpression insideDay() {
		return lowerHigh().and(higherLow());
	}
	
	public static BooleanExpression openAboveClose() {
		return OpenPrice.INSTANCE.greaterThan(ClosePrice.INSTANCE.previous());
	}
	
	public static BooleanExpression openBelowClose() {
		return OpenPrice.INSTANCE.lessThan(ClosePrice.INSTANCE.previous());
	}
	
	public static BooleanExpression closeBelowLow() {
		return ClosePrice.INSTANCE.lessThan(LowPrice.INSTANCE.previous());
	}
	
	public static BooleanExpression closeAboveHigh() {
		return ClosePrice.INSTANCE.greaterThan(HighPrice.INSTANCE.previous());
	}
	
	
	
}
