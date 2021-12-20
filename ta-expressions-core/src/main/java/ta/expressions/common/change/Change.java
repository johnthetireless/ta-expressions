package ta.expressions.common.change;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

/**
 * Change measures the difference between the current and some previous value of an expression.
 * Change is used to define many other expressions.
 * <p>
 * Change(e,n) = e - Previous(e,n)
 * <p>
 * Change(Close,5) = Close - Previous(Close,5)
 * <p>
 */
public class Change extends AnalyticFunction {

	private final NumericExpression formula;

	public Change(NumericExpression e, int n) {
		super(functionRepresentation("Change", e, n));
		this.formula = e.minus(e.previous(n));
	}

	public Change(NumericExpression e) {
		this(e, 1);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
