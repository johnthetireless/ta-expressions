package ta.expressions.indicators.macd;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class MACDHistogram extends AnalyticFunction {
	
	public static final String KEYWORD = "MACD_Histogram";

	public static MACDHistogram fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new MACDHistogram(ps.intValue(0), ps.intValue(1), ps.intValue(2));
	}

	private final NumericExpression formula;

	public MACDHistogram(int n1, int n2, int n3) {
		super(functionRepresentation(KEYWORD, n1, n2, n3));
		MACD macd = new MACD(n1, n2);
		this.formula = macd.minus(macd.signal(n3));
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
