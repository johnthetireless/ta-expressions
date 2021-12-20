package ta.expressions.indicators;

import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class MedianPrice extends BasicAnalyticFunction {

	public static final String KEYWORD = "MedianPrice";

	private static final NumericExpression FORMULA = 
			HighPrice.INSTANCE.plus(LowPrice.INSTANCE).dividedBy(2);
	
	public static final MedianPrice INSTANCE = new MedianPrice();

	private MedianPrice() {
		super(KEYWORD, FORMULA);
	}


}
