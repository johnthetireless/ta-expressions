package ta.expressions.indicators.volatility;

import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.HighPrice;

public class TrueHigh extends BasicAnalyticFunction {

	public static final String KEYWORD = "TrueHigh";

	private static final NumericExpression FORMULA = HighPrice.INSTANCE.max(ClosePrice.INSTANCE.previous());
	
	public static final TrueHigh INSTANCE = new TrueHigh();

	private TrueHigh() {
		super(KEYWORD, FORMULA);
	}

}
