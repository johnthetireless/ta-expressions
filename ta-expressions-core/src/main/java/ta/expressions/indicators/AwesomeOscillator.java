package ta.expressions.indicators;

import ta.expressions.common.stats.SMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class AwesomeOscillator extends AnalyticFunction {

	public static final String KEYWORD = "Awesome";

	public static AwesomeOscillator fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new AwesomeOscillator(ps.intValue(0), ps.intValue(1));
	}
	
	private final NumericExpression formula;

	public AwesomeOscillator(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		this.formula = new SMA(MedianPrice.INSTANCE, n1)
				.minus(new SMA(MedianPrice.INSTANCE, n2));
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
	
}
