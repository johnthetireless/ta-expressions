package ta.expressions.indicators;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class DEMA extends AnalyticFunction {
	
	public static final String KEYWORD = "DEMA";

	public static DEMA fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new DEMA(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public DEMA(int n) {
		this(ClosePrice.INSTANCE, n);
	}

	public DEMA(NumericExpression e, int n) {
		super(functionRepresentation(KEYWORD, n));
		EMA ema1 = new EMA(e, n);
		EMA ema2 = new EMA(ema1, n);
		this.formula = ema1.multipliedBy(2).minus(ema2);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
