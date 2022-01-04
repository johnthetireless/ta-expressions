package ta.expressions.indicators;

import ta.expressions.common.stats.Deviation;
import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.ClosePrice;

public class TrendIntensityIndex extends AnalyticFunction {

	public static final String KEYWORD = "TII";

	public static TrendIntensityIndex fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new TrendIntensityIndex(ps.intValue(0));
	}
	
	private final NumericExpression formula;

	public TrendIntensityIndex(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression dev = new Deviation(ClosePrice.INSTANCE, n);
		NumericExpression plusDev = new TernaryOperation(
				dev.greaterThan(0), 
				dev, 
				Constant.valueOf(0)
			);
		NumericExpression minusDev = new TernaryOperation(
				dev.lessThan(0), 
				dev.abs(), 
				Constant.valueOf(0)
			);
		int m = (int) Math.ceil(n / 2.0);
		NumericExpression sumPlusDev = new Summation(plusDev, m);
		NumericExpression sumMinusDev = new Summation(minusDev, m);
		
		NumericExpression dividend = sumPlusDev.multipliedBy(100);
		NumericExpression divisor = sumPlusDev.plus(sumMinusDev);
		
		this.formula = new TernaryOperation(
				divisor.equalTo(0), 
				Constant.valueOf(0), 
				dividend.dividedBy(divisor));
	}


	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
