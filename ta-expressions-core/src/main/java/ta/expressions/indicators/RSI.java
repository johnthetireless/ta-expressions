package ta.expressions.indicators;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class RSI extends AnalyticFunction {
	
	public static final String KEYWORD = "RSI";

	public static RSI fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new RSI(ps.intValue(0));
	}
	
	private final NumericExpression formula;

	public RSI(int n) {
		this(ClosePrice.INSTANCE, n);
	}

	public RSI(NumericExpression e, int n) {
		super(functionRepresentation(KEYWORD, e, n));
		NumericExpression rs = new RelativeStrength(e, n);
		this.formula = Constant.valueOf(100).minus(Constant.valueOf(100).dividedBy(rs.plus(1)));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
