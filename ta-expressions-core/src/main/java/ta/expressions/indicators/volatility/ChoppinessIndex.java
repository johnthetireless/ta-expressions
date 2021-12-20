package ta.expressions.indicators.volatility;

import ta.expressions.common.stats.HighestValue;
import ta.expressions.common.stats.LowestValue;
import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.ParameterString;

public class ChoppinessIndex extends AnalyticFunction {

	public static final String KEYWORD = "Choppiness";

	public static ChoppinessIndex fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ChoppinessIndex(ps.intValue(0));
	}

	private final NumericExpression formula;
	
	public ChoppinessIndex(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression sumOfTrueRange = new Summation(TrueRange.INSTANCE, n);
		NumericExpression lowestTrueLow = new LowestValue(TrueLow.INSTANCE, n);
		NumericExpression highestTrueHigh = new HighestValue(TrueHigh.INSTANCE, n);
		NumericExpression divisor = highestTrueHigh.minus(lowestTrueLow);
		NumericExpression e = new TernaryOperation(
				divisor.equalTo(0), 
				Constant.valueOf(0), 
				sumOfTrueRange.dividedBy(divisor));
		double log10N = Math.log10(n);
		
		this.formula = e.log10().dividedBy(log10N).multipliedBy(100);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
