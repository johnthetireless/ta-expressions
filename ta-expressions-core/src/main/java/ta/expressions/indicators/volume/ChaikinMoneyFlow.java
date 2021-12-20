package ta.expressions.indicators.volume;

import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.Volume;

public class ChaikinMoneyFlow extends AnalyticFunction {

	public static final String KEYWORD = "CMF";

	public static ChaikinMoneyFlow fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ChaikinMoneyFlow(ps.intValue(0));
	}

	private final NumericExpression formula;
	
	public ChaikinMoneyFlow(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new Summation(MoneyFlowVolume.INSTANCE, n)
				.dividedBy(new Summation(Volume.INSTANCE, n));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
