package ta.expressions.indicators.volatility;

import ta.expressions.common.stats.MMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class ATR extends AnalyticFunction {

	public static final String KEYWORD = "ATR";

	public static ATR fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ATR(ps.intValue(0));
	}

	private final NumericExpression formula;
	
	public ATR(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new MMA(TrueRange.INSTANCE, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
