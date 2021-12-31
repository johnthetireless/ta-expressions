package ta.expressions.indicators.volume;

import ta.expressions.common.change.RateOfChange;
import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.Volume;

public class RateOfChangeVolume extends AnalyticFunction {

	public static final String KEYWORD = "ROCV";

	public static RateOfChangeVolume fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new RateOfChangeVolume(ps.intValue(0));
	}

	private final NumericExpression formula;
	
	public RateOfChangeVolume(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new RateOfChange(Volume.INSTANCE, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}



}
