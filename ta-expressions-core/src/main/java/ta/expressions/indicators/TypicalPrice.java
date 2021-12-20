package ta.expressions.indicators;

import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class TypicalPrice extends BasicAnalyticFunction {
	
	public static final String KEYWORD = "TypicalPrice";

	private static final NumericExpression FORMULA = 
			HighPrice.INSTANCE.plus(LowPrice.INSTANCE).plus(ClosePrice.INSTANCE).dividedBy(3);
	
	public static final TypicalPrice INSTANCE = new TypicalPrice();

	private TypicalPrice() {
		super(KEYWORD, FORMULA);
	}

}
