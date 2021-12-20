package ta.expressions.indicators.ultimate;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class UltimateOscillator extends AnalyticFunction {
	
	public static final String KEYWORD = "UltimateOsc";

	public static UltimateOscillator fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new UltimateOscillator(ps.intValue(0), ps.intValue(1), ps.intValue(2));
	}

	private final NumericExpression formula;

	public UltimateOscillator(int n1, int n2, int n3) {
		super(functionRepresentation(KEYWORD, n1, n2, n3));
		NumericExpression avg1 = new AverageBuyingPressure(n1);
		NumericExpression avg2 = new AverageBuyingPressure(n2);
		NumericExpression avg3 = new AverageBuyingPressure(n3);
		this.formula = avg1.multipliedBy(4).plus(avg2.multipliedBy(2)).plus(avg3)
				.dividedBy(7).multipliedBy(100);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
