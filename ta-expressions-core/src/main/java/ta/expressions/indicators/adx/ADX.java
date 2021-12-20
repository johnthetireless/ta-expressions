package ta.expressions.indicators.adx;

import ta.expressions.common.stats.MMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;

public class ADX extends AnalyticFunction {

	public static final String KEYWORD = "ADX";
	
	public static ADX of(int n) {
		return new ADX(n);
	}

	public static ADX fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ADX(ps.intValue(0));
	}
	
	private final NumericExpression formula;
	
	public ADX(int n) {
		super(functionRepresentation(KEYWORD, n));
		this.formula = new MMA(new DX(n), n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
