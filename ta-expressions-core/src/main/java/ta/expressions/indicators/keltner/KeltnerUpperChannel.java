package ta.expressions.indicators.keltner;

import ta.expressions.common.stats.MovingAverage;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.volatility.ATR;

public class KeltnerUpperChannel extends AnalyticFunction {

	public static final String KEYWORD = "Keltner_Upper";

	public static AnalyticFunction fromString(String params) {
		ParameterString ps = new ParameterString(params);
		KeltnerChannels kc = new KeltnerChannels(ps.intValue(0), ps.doubleValue(1));
		return kc.upper();
	}

	private final NumericExpression formula;
	
	KeltnerUpperChannel(MovingAverage middle, int n, Number k) {
		super(functionRepresentation(KEYWORD, middle.periods(), n, k));
		NumericExpression atr = new ATR(middle.periods() / 2);
		this.formula = middle.plus(atr.multipliedBy(k));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
