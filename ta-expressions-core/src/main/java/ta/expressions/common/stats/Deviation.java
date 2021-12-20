package ta.expressions.common.stats;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class Deviation extends AnalyticFunction {

	private NumericExpression formula;
	
	public Deviation(NumericExpression e, int n) {
		super(functionRepresentation("Deviation", e, n));
		this.formula = e.minus(new SMA(e, n));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
