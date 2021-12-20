package ta.expressions.common.stats;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class Covariance extends AnalyticFunction {

	public static final String KEYWORD = "Covariance";
	
	private final NumericExpression formula;

	public Covariance(NumericExpression e1, NumericExpression e2, int n) {
		super(functionRepresentation(KEYWORD, e1, e2, n));
		Deviation d1 = new Deviation(e1, n);
		Deviation d2 = new Deviation(e2, n);
		this.formula = new SMA(d1.multipliedBy(d2), n);
	}


	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
