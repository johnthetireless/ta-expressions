package ta.expressions.indicators.donchian;

import ta.expressions.common.stats.HighestValue;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class DonchianUpperChannel extends AnalyticFunction {

	public static final String KEYWORD = "Donchian_Upper";

	public static DonchianUpperChannel fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new DonchianUpperChannel(ps.intValue(0));
	}

	private final NumericExpression formula;
	
	public DonchianUpperChannel(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new HighestValue(ClosePrice.INSTANCE, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
