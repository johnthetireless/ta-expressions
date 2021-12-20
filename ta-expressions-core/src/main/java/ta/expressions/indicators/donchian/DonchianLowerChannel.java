package ta.expressions.indicators.donchian;

import ta.expressions.common.stats.LowestValue;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class DonchianLowerChannel extends AnalyticFunction {

	public static final String KEYWORD = "Donchian_Lower";

	public static DonchianLowerChannel fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new DonchianLowerChannel(ps.intValue(0));
	}

	private final NumericExpression formula;
	
	public DonchianLowerChannel(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new LowestValue(ClosePrice.INSTANCE, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
