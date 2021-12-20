package ta.expressions.indicators.bollinger;

import ta.expressions.common.stats.StandardDeviation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class BollingerLowerBand extends AnalyticFunction {

	public static final String KEYWORD = "BB_Lower";

	public static AnalyticFunction fromString(String params) {
		ParameterString ps = new ParameterString(params);
		BollingerBands bb = new BollingerBands(ps.intValue(0), ps.doubleValue(1));
		return bb.lower();
	}

	private final NumericExpression formula;
	
	BollingerLowerBand(NumericExpression middle, int n, Number k) {
		super(functionRepresentation(KEYWORD, n, k));
		NumericExpression sd = new StandardDeviation(ClosePrice.INSTANCE, n);
		this.formula = middle.minus(sd.multipliedBy(k));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
