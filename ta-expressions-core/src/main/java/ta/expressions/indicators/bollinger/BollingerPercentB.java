package ta.expressions.indicators.bollinger;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class BollingerPercentB extends AnalyticFunction {
	
	public static final String KEYWORD = "BB_PercentB";

	public static BollingerPercentB fromString(String params) {
		ParameterString ps = new ParameterString(params);
		BollingerBands bb = new BollingerBands(ps.intValue(0), ps.doubleValue(1));
		return new BollingerPercentB(ClosePrice.INSTANCE, bb.upper(), bb.lower(), ps.intValue(0), ps.doubleValue(1));
	}

	private final NumericExpression formula;
	
	public BollingerPercentB(NumericExpression price, NumericExpression upper, NumericExpression lower, int n, Number k) {
		super(functionRepresentation(KEYWORD, n, k));	
		this.formula = price.minus(lower).dividedBy(upper.minus(lower));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
