package ta.expressions.indicators;

import ta.expressions.common.stats.EMA;
import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class MassIndex extends AnalyticFunction {

	public static final String KEYWORD = "MassIndex";

	public static MassIndex fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new MassIndex(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	//TODO: maybe parameterize ema N instead of 9
	public MassIndex(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression ema1 = new EMA(PriceRange.INSTANCE, 9);
		NumericExpression ema2 = new EMA(ema1, 9);
		NumericExpression emaRatio = ema1.dividedBy(ema2);
		this.formula = new Summation(emaRatio, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
