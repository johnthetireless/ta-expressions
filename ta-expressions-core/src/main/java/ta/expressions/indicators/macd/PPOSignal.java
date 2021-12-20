package ta.expressions.indicators.macd;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

//TODO: generalize PPO/MACD, maybe AccelDecel...  MA systems
//TODO: the n1,n2,n3 and n are a bit confusing here
public class PPOSignal extends AnalyticFunction {

	public static final String KEYWORD = "PPO_Histogram";

	public static PPOSignal fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new PPOSignal(ps.intValue(0), ps.intValue(1), ps.intValue(2));
	}

	private final NumericExpression formula;

	public PPOSignal(int n1, int n2, int n3) {
		this(new PPO(n1, n2), n3);
	}

	PPOSignal(PPO ppo, int n) {
		super(functionRepresentation("PPO_Signal", ppo.n1(), ppo.n2(), n));
		this.formula = new EMA(ppo, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
