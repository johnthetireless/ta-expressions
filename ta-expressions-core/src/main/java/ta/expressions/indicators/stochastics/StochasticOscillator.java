package ta.expressions.indicators.stochastics;

import ta.expressions.common.stats.HighestValue;
import ta.expressions.common.stats.LowestValue;
import ta.expressions.common.stats.SMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class StochasticOscillator extends AnalyticFunction {

	public static final String KEYWORD = "StochK";

	public static StochasticOscillator fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new StochasticOscillator(ps.intValue(0), ps.intValue(1));
	}

	private final NumericExpression formula;

	public StochasticOscillator(int n) {
		this(n, 1);
	}

	public StochasticOscillator(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		NumericExpression dividend = ClosePrice.INSTANCE.minus(LowestValue.lowestLow(n1)).multipliedBy(100);
		NumericExpression divisor = HighestValue.highestHigh(n1).minus(LowestValue.lowestLow(n1));
		NumericExpression raw = dividend.divideOrZero(divisor);
		this.formula = n2 > 1 ? new SMA(raw, n2) : raw;
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
