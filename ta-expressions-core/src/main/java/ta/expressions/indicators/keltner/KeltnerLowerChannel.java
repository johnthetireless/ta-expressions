package ta.expressions.indicators.keltner;

import ta.expressions.common.stats.MovingAverage;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.volatility.ATR;

public class KeltnerLowerChannel extends AnalyticFunction {

	public static final String KEYWORD = "Keltner_Lower";

	public static AnalyticFunction fromString(String params) {
		ParameterString ps = new ParameterString(params);
		KeltnerChannels kc = new KeltnerChannels(ps.intValue(0), ps.doubleValue(1));
		return kc.lower();
	}

	private final NumericExpression formula;
	
	KeltnerLowerChannel(MovingAverage middle, int n, Number k) {
		super(functionRepresentation(KEYWORD, middle.periods(), n, k));
		NumericExpression atr = new ATR(middle.periods() / 2);
		this.formula = middle.minus(atr.multipliedBy(k));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
