package ta.expressions.indicators.volume;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.RSI;
import ta.expressions.indicators.variables.Volume;

public class VolumeRSI extends AnalyticFunction {

	public static final String KEYWORD = "VolumeRSI";

	public static VolumeRSI fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new VolumeRSI(ps.intValue(0));
	}
	
	private final NumericExpression formula;

	public VolumeRSI(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new RSI(Volume.INSTANCE, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
