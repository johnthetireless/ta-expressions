package ta.expressions.indicators.volatility;

import ta.expressions.common.change.RateOfChange;
import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.PriceRange;

public class ChaikinVolatility extends AnalyticFunction {

	public static final String KEYWORD = "ChaikinVolatility";

	public static ChaikinVolatility fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ChaikinVolatility(ps.intValue(0), ps.intValue(1));
	}

	private final NumericExpression formula;
	
	public ChaikinVolatility(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		NumericExpression ema = new EMA(PriceRange.INSTANCE, n1); 
		this.formula = new RateOfChange(ema, n2);
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
	

}
