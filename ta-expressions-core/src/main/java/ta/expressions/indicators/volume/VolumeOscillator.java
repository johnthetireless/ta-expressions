package ta.expressions.indicators.volume;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.macd.MACD;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.Volume;

public class VolumeOscillator extends AnalyticFunction {

	public static final String KEYWORD = "VolumeOsc";

	public static VolumeOscillator fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new VolumeOscillator(ps.intValue(0), ps.intValue(1));
	}

	private final NumericExpression formula;
	
	public VolumeOscillator(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		NumericExpression ema1 = new EMA(Volume.INSTANCE, n1); 
		NumericExpression ema2 = new EMA(Volume.INSTANCE, n2); 
		this.formula = ema1.minus(ema2);
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
