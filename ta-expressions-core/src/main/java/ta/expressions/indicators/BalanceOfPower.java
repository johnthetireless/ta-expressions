package ta.expressions.indicators;

import ta.expressions.common.stats.SMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.OpenPrice;

public class BalanceOfPower extends AnalyticFunction {

	public static final String KEYWORD = "BOP";

	public static BalanceOfPower fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new BalanceOfPower(ps.intValue(0));
	}
	
	private final NumericExpression formula;

	public BalanceOfPower(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression closeMinusOpen = ClosePrice.INSTANCE.minus(OpenPrice.INSTANCE);
		NumericExpression bop = closeMinusOpen.divideOrZero(PriceRange.INSTANCE);
		this.formula = new SMA(bop, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
