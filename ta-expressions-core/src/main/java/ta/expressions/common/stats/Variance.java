package ta.expressions.common.stats;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class Variance extends AnalyticFunction {

	private final NumericExpression formula;
	
	public Variance(NumericExpression e, int n) {
		super(functionRepresentation("Variance", e, n));
//		this.formula = new Summation(new Deviation(e, n).squared(), n);
		this.formula = new SMA(new Deviation(e, n).squared(), n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
