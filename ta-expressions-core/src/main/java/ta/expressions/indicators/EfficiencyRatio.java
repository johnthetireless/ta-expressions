package ta.expressions.indicators;

import ta.expressions.common.change.Change;
import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.ClosePrice;

public class EfficiencyRatio extends AnalyticFunction {

	public static final String KEYWORD = "Volatility";

//	public static Volatility fromString(String params) {
//		ParameterString ps = new ParameterString(params);
//		return new Volatility(ps.intValue(0));
//	}
	
	private final NumericExpression formula;

	public EfficiencyRatio(int n) {
		super(functionRepresentation(KEYWORD, n));
		
		NumericExpression close = ClosePrice.INSTANCE;
		NumericExpression change = new Change(close, n);
		NumericExpression volatility = new Summation(change.abs(), n);
		this.formula = change.divideOrZero(volatility);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
