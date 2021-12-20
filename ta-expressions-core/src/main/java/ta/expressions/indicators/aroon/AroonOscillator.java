package ta.expressions.indicators.aroon;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class AroonOscillator extends AnalyticFunction {

	public static final String KEYWORD = "AroonOsc";

	public static AroonOscillator fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new AroonOscillator(ps.intValue(0));
	}

	private final NumericExpression formula;
	
	public AroonOscillator(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new AroonUp(n).minus(new AroonDown(n));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
