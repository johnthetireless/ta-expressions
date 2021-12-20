package ta.expressions.common.stats;

import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;

public abstract class RecursiveMA extends MovingAverage {

	private final NumericExpression formula;

	public RecursiveMA(String representation, NumericExpression e, int n, Number factor) {
		super(representation, e, n);
		
		this.formula = new TernaryOperation(
				previous().isNull(), 
				new SMA(e, n), 
				previous().plus(e.minus(previous()).multipliedBy(factor))
				);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
