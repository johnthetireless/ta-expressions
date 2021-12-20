package ta.expressions.indicators.stochastics;

import ta.expressions.common.stats.HighestValue;
import ta.expressions.common.stats.LowestValue;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.RSI;

public class StochRSI extends AnalyticFunction {
	
	public static final String KEYWORD = "StochRSI";

	public static StochRSI fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new StochRSI(ps.intValue(0));
	}

	private final NumericExpression formula;

	public StochRSI(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression rsi = new RSI(n);
		NumericExpression lowest = new LowestValue(rsi, n);
		NumericExpression highest = new HighestValue(rsi, n);
		NumericExpression dividend = rsi.minus(lowest).multipliedBy(100);
		NumericExpression divisor = highest.minus(lowest);
		this.formula = new TernaryOperation(
				divisor.equalTo(0), 
				Constant.valueOf(0), 
				dividend.dividedBy(divisor)
				);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}



}
