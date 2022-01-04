package ta.expressions.indicators.stochastics;

import ta.expressions.common.stats.HighestValue;
import ta.expressions.common.stats.LowestValue;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class WilliamsR extends AnalyticFunction {

	public static final String KEYWORD = "WilliamsR";

	public static WilliamsR fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new WilliamsR(ps.intValue(0));
	}

	private final NumericExpression formula;

	public WilliamsR(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression dividend = HighestValue.highestHigh(n).minus(ClosePrice.INSTANCE).multipliedBy(-100);
		NumericExpression divisor = HighestValue.highestHigh(n).minus(LowestValue.lowestLow(n));
		this.formula = dividend.divideOrZero(divisor);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
