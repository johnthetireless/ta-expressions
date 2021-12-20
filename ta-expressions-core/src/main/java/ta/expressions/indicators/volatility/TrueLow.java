package ta.expressions.indicators.volatility;

import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.LowPrice;

public class TrueLow extends BasicAnalyticFunction {

	public static final String KEYWORD = "TrueLow";

	private static final NumericExpression FORMULA = LowPrice.INSTANCE.min(ClosePrice.INSTANCE.previous());
	
	public static final TrueLow INSTANCE = new TrueLow();

	private TrueLow() {
		super(KEYWORD, FORMULA);
	}


}
