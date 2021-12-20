package ta.expressions.common.change;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

/**
 * Rate of Change (ROC) measures the relative change to the value of an expression relative to its Nth previous value.
 * This sometimes called "momentum".
 * <p>
 * ROC(Close,10) = (Change(Close,10) / Previous(Close,10)) * 100;
 * <p>
 */
public class RateOfChange extends AnalyticFunction {

	private final NumericExpression formula;

	public RateOfChange(NumericExpression e, int n) {
		super(functionRepresentation("ROC", e, n));
		//TODO: check for divide by zero
		this.formula = new Change(e, n).dividedBy(e.previous(n)).multipliedBy(100);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
