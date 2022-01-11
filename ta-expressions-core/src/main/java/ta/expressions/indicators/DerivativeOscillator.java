package ta.expressions.indicators;

import ta.expressions.common.stats.EMA;
import ta.expressions.common.stats.SMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class DerivativeOscillator extends AnalyticFunction {

	public static final String KEYWORD = "DerivativeOsc";

	public static DerivativeOscillator fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new DerivativeOscillator(ps.intValue(0), ps.intValue(1), ps.intValue(2), ps.intValue(3));
	}

	private final NumericExpression formula;

	public DerivativeOscillator(int rsiN, int emaN1, int emaN2, int smaN) {
		super(functionRepresentation(KEYWORD, rsiN, emaN1, emaN2, smaN));
		NumericExpression rsi = new RSI(rsiN);
		NumericExpression ema1 = new EMA(rsi, emaN1);
		NumericExpression ema2 = new EMA(ema1, emaN2);
		NumericExpression sma = new SMA(ema2, smaN);
		this.formula = ema2.minus(sma);
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
