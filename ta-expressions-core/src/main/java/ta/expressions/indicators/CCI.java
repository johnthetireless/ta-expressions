package ta.expressions.indicators;

import ta.expressions.common.stats.SMA;
import ta.expressions.common.stats.Variance;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class CCI extends AnalyticFunction {

	public static final String KEYWORD = "CCI";

	public static CCI fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new CCI(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public CCI(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = TypicalPrice.INSTANCE.minus(new SMA(TypicalPrice.INSTANCE, n))
				.dividedBy(new Variance(TypicalPrice.INSTANCE, n).multipliedBy(0.015));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
}
