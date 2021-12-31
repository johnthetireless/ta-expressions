package ta.expressions.indicators;

import ta.expressions.common.change.RateOfChange;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class CoppockCurve extends AnalyticFunction {

	public static final String KEYWORD = "Coppock";

	public static CoppockCurve fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new CoppockCurve(ps.intValue(0), ps.intValue(1), ps.intValue(2));
	}

	private final NumericExpression formula;

	public CoppockCurve(int n1, int n2, int n3) {
		super(functionRepresentation(KEYWORD, n1, n2, n3));
		NumericExpression roc1 = new RateOfChange(ClosePrice.INSTANCE, n1);
		NumericExpression roc2 = new RateOfChange(ClosePrice.INSTANCE, n2);
		this.formula = new WMA(roc1.plus(roc2), n3);
	}


	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
