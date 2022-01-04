package ta.expressions.indicators;

import ta.expressions.common.change.Change;
import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class TrendScore extends AnalyticFunction {

	public static final String KEYWORD = "TrendScore";

	public static TrendScore fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new TrendScore(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public TrendScore(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new Summation(new Change(ClosePrice.INSTANCE).signum(), n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
}
