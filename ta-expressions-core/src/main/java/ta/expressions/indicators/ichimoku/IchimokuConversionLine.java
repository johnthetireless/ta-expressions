package ta.expressions.indicators.ichimoku;

import ta.expressions.common.stats.HighestValue;
import ta.expressions.common.stats.LowestValue;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class IchimokuConversionLine extends AnalyticFunction {

	public static final String KEYWORD = "Ichimoku_Conversion";

	public static IchimokuConversionLine fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new IchimokuConversionLine(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public IchimokuConversionLine(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = HighestValue.highestHigh(n).plus(LowestValue.lowestLow(n))
							.dividedBy(2);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
}
