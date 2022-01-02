package ta.expressions.indicators.volatility;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

/**
 * True range divided by its EMA
 * 
 * Wide-ranging days will have VR > 2.0
 *
 */
public class VolatilityRatio extends AnalyticFunction {

	public static final String KEYWORD = "VR";

	public static VolatilityRatio fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new VolatilityRatio(ps.intValue(0));
	}

	private final NumericExpression formula;
	
	public VolatilityRatio(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = TrueRange.INSTANCE.dividedBy(new EMA(TrueRange.INSTANCE, n));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
