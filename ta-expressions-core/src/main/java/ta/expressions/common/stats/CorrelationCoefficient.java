package ta.expressions.common.stats;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;

public class CorrelationCoefficient extends AnalyticFunction {

	public static final String KEYWORD = "CorrelationCoefficient";
	
	private final NumericExpression formula;

	public CorrelationCoefficient(NumericExpression e1, NumericExpression e2, int n) {
		super(functionRepresentation(KEYWORD, e1, e2, n));
		
		Covariance cv = new Covariance(e1, e2, n);
		Variance v1 = new Variance(e1, n);
		Variance v2 = new Variance(e2, n);
		NumericExpression divisor = v1.multipliedBy(v2).sqrt();
		this.formula = cv.divideOrZero(divisor);		
	}


	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
