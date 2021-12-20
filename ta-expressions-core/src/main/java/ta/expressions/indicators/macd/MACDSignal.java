package ta.expressions.indicators.macd;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class MACDSignal extends AnalyticFunction {
	
	public static final String KEYWORD = "MACD_Signal";

	public static MACDSignal fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new MACDSignal(ps.intValue(0), ps.intValue(1), ps.intValue(2));
	}

	private final NumericExpression formula;

	public MACDSignal(int n1, int n2, int n3) {
		this(new MACD(n1, n2), n3);
	}

	MACDSignal(MACD macd, int n) {
		super(functionRepresentation(KEYWORD, macd.n1(), macd.n2(), n));
		this.formula = new EMA(macd, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
