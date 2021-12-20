package ta.expressions.common.change;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;

/**
 * Loss measures the negative change to the value of an expression relative to its Nth previous value.
 * If the change is positive, loss is zero; if change is negative, loss is the absolute value of change.  N is usually 1.
 * Loss is used to define RSI and a few other TA expressions.
 * <p>
 * Loss(Close,1) = Change(Close,1) < 0 ? ABS(Change(Close,1)) : 0
 * <p>
 */
public class Loss extends AnalyticFunction {

	private final NumericExpression formula;

	public Loss(NumericExpression e) {
		this(e, 1);
	}

	public Loss(NumericExpression e, int n) {
		super(functionRepresentation("Loss", e, n));
		Change change = new Change(e, n);
		this.formula = new TernaryOperation(change.lessThan(0), change.abs(), Constant.valueOf(0));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
