package ta.expressions.indicators.ultimate;

import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.volatility.TrueLow;

public class BuyingPressure extends BasicAnalyticFunction {

	public static final String KEYWORD = "BuyingPressure";

	private static final NumericExpression FORMULA = ClosePrice.INSTANCE.minus(TrueLow.INSTANCE);
	
	public static final BuyingPressure INSTANCE = new BuyingPressure();

	private BuyingPressure() {
		super(KEYWORD, FORMULA);
	}


}
