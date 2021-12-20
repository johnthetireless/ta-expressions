package ta.expressions.indicators.keltner;

import ta.expressions.common.stats.MovingAverage;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.indicators.ParameterString;

public class KeltnerMiddleChannel extends BasicAnalyticFunction {

	public static final String KEYWORD = "Keltner_Middle";

	public static AnalyticFunction fromString(String params) {
		ParameterString ps = new ParameterString(params);
		KeltnerChannels kc = new KeltnerChannels(ps.intValue(0), ps.doubleValue(1));
		return kc.middle();
	}

	KeltnerMiddleChannel(MovingAverage ma) {
		super(functionRepresentation(KEYWORD, ma.periods()), ma);	
	}
	

}
