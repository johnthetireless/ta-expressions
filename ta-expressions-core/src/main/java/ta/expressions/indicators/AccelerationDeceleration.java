package ta.expressions.indicators;

import ta.expressions.common.stats.SMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class AccelerationDeceleration extends AnalyticFunction {

	public static final String KEYWORD = "AccelDecel";

	public static AccelerationDeceleration fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new AccelerationDeceleration(ps.intValue(0), ps.intValue(1));
	}
	
	private final NumericExpression formula;
	
	public AccelerationDeceleration(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		NumericExpression awesome = new AwesomeOscillator(n1, n2);
		NumericExpression sma = new SMA(awesome, n1);
		this.formula = awesome.minus(sma);
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
	

}
