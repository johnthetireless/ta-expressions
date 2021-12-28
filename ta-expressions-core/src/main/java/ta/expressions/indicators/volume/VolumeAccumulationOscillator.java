package ta.expressions.indicators.volume;

import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.MedianPrice;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.Volume;

public class VolumeAccumulationOscillator extends BasicAnalyticFunction {
	
	public static final String KEYWORD = "VAO";
	
	private static final NumericExpression FORMULA 
						= ClosePrice.INSTANCE.minus(MedianPrice.INSTANCE)
						.multipliedBy(Volume.INSTANCE);
	
	public static final VolumeAccumulationOscillator INSTANCE = new VolumeAccumulationOscillator();

	private VolumeAccumulationOscillator() {
		super(KEYWORD, FORMULA);
	}

}
