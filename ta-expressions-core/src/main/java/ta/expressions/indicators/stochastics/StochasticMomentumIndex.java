package ta.expressions.indicators.stochastics;

import ta.expressions.common.stats.EMA;
import ta.expressions.common.stats.HighestValue;
import ta.expressions.common.stats.LowestValue;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class StochasticMomentumIndex extends AnalyticFunction {

	public static final String KEYWORD = "SMI";

	public static StochasticMomentumIndex fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new StochasticMomentumIndex(ps.intValue(0));
	}

	private final NumericExpression formula;

	public StochasticMomentumIndex(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression m = HighestValue.highestHigh(n).plus(LowestValue.lowestLow(n)).dividedBy(2);
		NumericExpression d = ClosePrice.INSTANCE.minus(m);
		NumericExpression emaEmaD = new EMA(new EMA(d, n), n);
		NumericExpression hl = HighestValue.highestHigh(n).minus(LowestValue.lowestLow(n));
		NumericExpression emaEmaHL = new EMA(new EMA(hl, n), n);
		NumericExpression dividend = emaEmaD.multipliedBy(100);
		NumericExpression divisor = emaEmaHL;
		
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
