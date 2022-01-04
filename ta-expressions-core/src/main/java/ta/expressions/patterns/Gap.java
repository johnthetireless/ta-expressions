package ta.expressions.patterns;

import ta.expressions.core.BooleanExpression;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class Gap {

	public static final BooleanExpression UP = LowPrice.INSTANCE.greaterThan(HighPrice.INSTANCE.previous());
	public static final BooleanExpression DOWN = HighPrice.INSTANCE.lessThan(LowPrice.INSTANCE.previous());
	
	public static BooleanExpression previousUp(int n) {
		return LowPrice.INSTANCE.previous(n).greaterThan(HighPrice.INSTANCE.previous(n + 1));
	}
	
	public static BooleanExpression previousDown(int n) {
		return HighPrice.INSTANCE.previous(n).lessThan(LowPrice.INSTANCE.previous(n + 1));
	}
	
	private Gap() {}
}
