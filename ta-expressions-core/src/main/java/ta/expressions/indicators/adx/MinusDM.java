package ta.expressions.indicators.adx;

import ta.expressions.common.change.Gain;
import ta.expressions.common.change.Loss;
import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class MinusDM extends BasicAnalyticFunction {

	public static final String KEYWORD = "MinusDM";

	private static final NumericExpression HIGH_GAIN = new Gain(HighPrice.INSTANCE, 1);
	private static final NumericExpression LOW_LOSS = new Loss(LowPrice.INSTANCE, 1);
	
	public static final NumericExpression FORMULA = new TernaryOperation(
			LOW_LOSS.greaterThan(HIGH_GAIN),
			LOW_LOSS, 
			Constant.valueOf(0)
			);
	
	public static final MinusDM INSTANCE = new MinusDM();
	
	private MinusDM() {
		super(KEYWORD, FORMULA);
	}



}
