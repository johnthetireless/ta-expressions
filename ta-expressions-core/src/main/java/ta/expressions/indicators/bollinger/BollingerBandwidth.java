package ta.expressions.indicators.bollinger;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class BollingerBandwidth extends AnalyticFunction {

	public static final String KEYWORD = "BB_Bandwidth";

	public static BollingerBandwidth fromString(String params) {
		ParameterString ps = new ParameterString(params);
		BollingerBands bb = new BollingerBands(ps.intValue(0), ps.doubleValue(1));
		return new BollingerBandwidth(bb.upper(), bb.middle(), bb.lower(), ps.intValue(0), ps.doubleValue(1));
	}

	private final NumericExpression formula;
	
	public BollingerBandwidth(NumericExpression upper, NumericExpression middle, NumericExpression lower, int n, Number k) {
		super(functionRepresentation(KEYWORD, n, k));
		this.formula = upper.minus(lower).dividedBy(middle).multipliedBy(100);
//		this.formula = upper.minus(lower).dividedBy(middle);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
