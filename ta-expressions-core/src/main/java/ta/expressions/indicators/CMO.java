package ta.expressions.indicators;

import ta.expressions.common.change.Gain;
import ta.expressions.common.change.Loss;
import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

/**
 * Chande Momentum Oscillator
 *
 */
public class CMO extends AnalyticFunction {

	public static final String KEYWORD = "CMO";

	public static CMO fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new CMO(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public CMO(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression sumOfGains = new Summation(new Gain(ClosePrice.INSTANCE), n);
		NumericExpression sumOfLosses = new Summation(new Loss(ClosePrice.INSTANCE), n);
		this.formula = sumOfGains.minus(sumOfLosses).dividedBy(sumOfGains.plus(sumOfLosses)).multipliedBy(100);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
