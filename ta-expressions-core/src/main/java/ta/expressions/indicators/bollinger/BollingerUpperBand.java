package ta.expressions.indicators.bollinger;

import ta.expressions.common.stats.StandardDeviation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class BollingerUpperBand extends AnalyticFunction {

	public static final String KEYWORD = "BB_Upper";

	//TODO: this is a convoluted way to create these objects
	public static AnalyticFunction fromString(String params) {
		ParameterString ps = new ParameterString(params);
		BollingerBands bb = new BollingerBands(ps.intValue(0), ps.doubleValue(1));
		return bb.upper();
	}

	private final NumericExpression formula;
	
	BollingerUpperBand(NumericExpression middle, int n, Number k) {
		super(functionRepresentation(KEYWORD, n, k));
		NumericExpression sd = new StandardDeviation(ClosePrice.INSTANCE, n);
		this.formula = middle.plus(sd.multipliedBy(k));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
