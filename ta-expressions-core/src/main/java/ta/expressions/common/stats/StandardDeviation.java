package ta.expressions.common.stats;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class StandardDeviation extends AnalyticFunction {

	private NumericExpression formula;
	
	public StandardDeviation(NumericExpression e, int n) {
		super(functionRepresentation("StdDev", e, n));
		this.formula = new Variance(e, n).sqrt();
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
