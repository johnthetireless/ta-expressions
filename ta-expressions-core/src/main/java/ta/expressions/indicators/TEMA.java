package ta.expressions.indicators;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class TEMA extends AnalyticFunction {

	public static final String KEYWORD = "TEMA";

	public static TEMA fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new TEMA(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public TEMA(int n) {
		this(ClosePrice.INSTANCE, n);
	}

	public TEMA(NumericExpression e, int n) {
		super(functionRepresentation(KEYWORD, n));
		EMA ema1 = new EMA(e, n);
		EMA ema2 = new EMA(ema1, n);
		EMA ema3 = new EMA(ema2, n);
		this.formula = ema1.multipliedBy(3).minus(ema2.multipliedBy(3)).plus(ema3);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
