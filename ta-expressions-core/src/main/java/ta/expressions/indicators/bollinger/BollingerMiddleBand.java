package ta.expressions.indicators.bollinger;

import ta.expressions.common.stats.MovingAverage;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.indicators.ParameterString;

//move to different place in class hierarchy; Keltner middle too
//all other subclasses of BasicAnalyticFunction are basic "terms"
public class BollingerMiddleBand extends BasicAnalyticFunction {

	public static final String KEYWORD = "BB_Middle";

	public static AnalyticFunction fromString(String params) {
		ParameterString ps = new ParameterString(params);
		BollingerBands bb = new BollingerBands(ps.intValue(0), ps.doubleValue(1));
		return bb.middle();
	}

	BollingerMiddleBand(MovingAverage ma) {
		super(functionRepresentation(KEYWORD, ma.periods()), ma);	
	}
	
}
