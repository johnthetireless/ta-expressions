package ta.expressions.indicators.chandelier;

import ta.expressions.common.stats.HighestValue;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.volatility.ATR;

public class ChandlelierExitLong extends AnalyticFunction {

	public static final String KEYWORD = "Chandelier_Long";

	public static ChandlelierExitLong fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ChandlelierExitLong(ps.intValue(0), ps.doubleValue(1));
	}

	private final NumericExpression formula;
	
	public ChandlelierExitLong(int n, Number k) {
		super(functionRepresentation(KEYWORD, n, k));
		NumericExpression atr = new ATR(n);
		this.formula = HighestValue.highestHigh(n).minus(atr.multipliedBy(k));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
