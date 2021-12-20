package ta.expressions.indicators.macd;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class PPOHistogram extends AnalyticFunction {

	public static final String KEYWORD = "PPO_Histogram";

	public static PPOHistogram fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new PPOHistogram(ps.intValue(0), ps.intValue(1), ps.intValue(2));
	}

	private final NumericExpression formula;

	public PPOHistogram(int n1, int n2, int n3) {
		super(functionRepresentation(KEYWORD, n1, n3, n3));
		PPO ppo = new PPO(n1, n2);
		this.formula = ppo.minus(ppo.signal(n3));
	}


	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
