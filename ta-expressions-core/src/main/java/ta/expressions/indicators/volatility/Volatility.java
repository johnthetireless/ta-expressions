package ta.expressions.indicators.volatility;

import ta.expressions.common.stats.SMA;
import ta.expressions.common.stats.StandardDeviation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class Volatility extends AnalyticFunction {

	public static final String KEYWORD = "Volatility";

	public static Volatility fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new Volatility(ps.intValue(0));
	}

	private final NumericExpression formula;
	
	public Volatility(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression close = ClosePrice.INSTANCE;
		this.formula = new StandardDeviation(close, n).dividedBy(new SMA(close, n));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
