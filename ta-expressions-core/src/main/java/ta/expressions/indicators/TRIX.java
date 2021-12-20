package ta.expressions.indicators;

import ta.expressions.common.change.RateOfChange;
import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class TRIX extends AnalyticFunction {

	public static final String KEYWORD = "TRIX";

	public static TRIX fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new TRIX(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public TRIX(int n) {
		this(ClosePrice.INSTANCE, n);
	}

	public TRIX(NumericExpression e, int n) {
		super(functionRepresentation(KEYWORD, n));
		EMA ema1 = new EMA(e, n);
		EMA ema2 = new EMA(ema1, n);
		EMA ema3 = new EMA(ema2, n);
		this.formula = new RateOfChange(ema3, 1);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
