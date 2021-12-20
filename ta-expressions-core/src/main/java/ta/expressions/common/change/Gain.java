package ta.expressions.common.change;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;

/**
 * Gain measures the positive change to the value of an expression relative to its Nth previous value.
 * If the change is negative, gain is zero.  N is usually 1.
 * Gain is used to define RSI and a few other TA expressions.
 * <p>
 * Gain(Close,1) = Change(Close,1) > 0 ? Change(Close,1) : 0
 * <p>
 */
public class Gain extends AnalyticFunction {

	private final NumericExpression formula;

	public Gain(NumericExpression e) {
		this(e, 1);
	}

	public Gain(NumericExpression e, int n) {
		super(functionRepresentation("Gain", e, n));
		Change change = new Change(e, n);
		this.formula = new TernaryOperation(change.greaterThan(0), change, Constant.valueOf(0));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
