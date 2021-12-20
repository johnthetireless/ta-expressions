package ta.expressions.indicators.adx;

import ta.expressions.common.stats.MMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.volatility.ATR;

public class PlusDI extends AnalyticFunction {

	public static final String KEYWORD = "PlusDI";

	public static PlusDI of(int n) {
		return new PlusDI(n);
	}

	public static PlusDI fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new PlusDI(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public PlusDI(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new MMA(PlusDM.INSTANCE, n).dividedBy(new ATR(n)).multipliedBy(100);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
