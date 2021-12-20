package ta.expressions.indicators.chandelier;

import ta.expressions.common.stats.LowestValue;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.volatility.ATR;

public class ChandlelierExitShort extends AnalyticFunction {

	public static final String KEYWORD = "Chandelier_Short";

	public static ChandlelierExitShort fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ChandlelierExitShort(ps.intValue(0), ps.doubleValue(1));
	}

	private final NumericExpression formula;
	
	public ChandlelierExitShort(int n, Number k) {
		super(functionRepresentation(KEYWORD, n, k));
		NumericExpression atr = new ATR(n);
		this.formula = LowestValue.lowestLow(n).minus(atr.multipliedBy(k));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
