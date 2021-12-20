package ta.expressions.indicators.adx;

import ta.expressions.common.change.Gain;
import ta.expressions.common.change.Loss;
import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class PlusDM extends BasicAnalyticFunction {

	public static final String KEYWORD = "PlusDM";

	private static final NumericExpression HIGH_GAIN = new Gain(HighPrice.INSTANCE, 1);
	private static final NumericExpression LOW_LOSS = new Loss(LowPrice.INSTANCE, 1);
	
	public static final NumericExpression FORMULA = new TernaryOperation(
			HIGH_GAIN.greaterThan(LOW_LOSS),
			HIGH_GAIN, 
			Constant.valueOf(0)
			);
	
	public static final PlusDM INSTANCE = new PlusDM();
	
	private PlusDM() {
		super(KEYWORD, FORMULA);
	}

}
