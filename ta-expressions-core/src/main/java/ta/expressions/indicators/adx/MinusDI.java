package ta.expressions.indicators.adx;

import ta.expressions.common.stats.MMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.volatility.ATR;

public class MinusDI extends AnalyticFunction {

	public static final String KEYWORD = "MinusDI";

	public static MinusDI of(int n) {
		return new MinusDI(n);
	}

	public static MinusDI fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new MinusDI(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public MinusDI(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new MMA(MinusDM.INSTANCE, n).dividedBy(new ATR(n)).multipliedBy(100);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
