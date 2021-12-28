package ta.expressions.indicators.adx;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class ADXR extends AnalyticFunction {

	public static final String KEYWORD = "ADXR";

	public static ADXR fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ADXR(ps.intValue(0), ps.intValue(1));
	}

	private final NumericExpression formula;
	
	public ADXR(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		NumericExpression adx = new ADX(n1);
		NumericExpression prev = adx.previous(n2);
		this.formula = adx.plus(prev).dividedBy(2);
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
	

}
