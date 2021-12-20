package ta.expressions.indicators;

import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class PriceRange extends BasicAnalyticFunction {

	public static final String KEYWORD = "PriceRange";

	private static final NumericExpression FORMULA = 
			HighPrice.INSTANCE.minus(LowPrice.INSTANCE);
	
	public static final PriceRange INSTANCE = new PriceRange();

	private PriceRange() {
		super(KEYWORD, FORMULA);
	}

}
