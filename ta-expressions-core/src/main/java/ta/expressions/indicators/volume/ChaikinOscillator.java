package ta.expressions.indicators.volume;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class ChaikinOscillator extends AnalyticFunction {

	public static final String KEYWORD = "ChaikinOsc";

	public static ChaikinOscillator fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ChaikinOscillator(ps.intValue(0), ps.intValue(1));
	}

	private final NumericExpression formula;
	
	public ChaikinOscillator(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		this.formula = new EMA(AccumulationDistribution.INSTANCE, n1)
				.minus(new EMA(AccumulationDistribution.INSTANCE, n2));
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
	
}
