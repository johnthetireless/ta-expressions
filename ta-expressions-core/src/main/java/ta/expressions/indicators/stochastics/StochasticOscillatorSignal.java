package ta.expressions.indicators.stochastics;

import ta.expressions.common.stats.SMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class StochasticOscillatorSignal extends AnalyticFunction {

	public static final String KEYWORD = "StochD";

	public static StochasticOscillatorSignal fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new StochasticOscillatorSignal(ps.intValue(0), ps.intValue(1), ps.intValue(2));
	}

	private final NumericExpression formula;
	
	public StochasticOscillatorSignal(int n1, int n2, int n3) {
		super(functionRepresentation(KEYWORD, n1, n2, n3));
		this.formula = new SMA(new StochasticOscillator(n1,n2),n3);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
