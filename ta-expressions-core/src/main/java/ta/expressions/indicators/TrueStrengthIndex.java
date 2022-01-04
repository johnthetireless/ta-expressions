package ta.expressions.indicators;

import ta.expressions.common.change.Change;
import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.ClosePrice;

public class TrueStrengthIndex extends AnalyticFunction {

	public static final String KEYWORD = "TSI";

	public static TrueStrengthIndex fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new TrueStrengthIndex(ps.intValue(0), ps.intValue(1));
	}

	private final NumericExpression formula;
	
	public TrueStrengthIndex(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		NumericExpression change = new Change(ClosePrice.INSTANCE, 1);
		NumericExpression dividend = emaEma(change, n1, n2).multipliedBy(100);
		NumericExpression divisor = emaEma(change.abs(), n1, n2);
		this.formula = dividend.divideOrZero(divisor);
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
	
	private NumericExpression emaEma(NumericExpression e, int n1, int n2) {
		EMA ema1 = new EMA(e, n1);
		return new EMA(ema1, n2);
	}

}
