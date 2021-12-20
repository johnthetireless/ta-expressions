package ta.expressions.common.stats;

import ta.expressions.core.NumericExpression;

public class SMA extends MovingAverage {
	
	private final NumericExpression formula;

	public SMA(NumericExpression e, int n) {
		super(functionRepresentation("SMA", e, n), e, n);
		this.formula = new Summation(e, n).dividedBy(n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

	@Override
	public MovingAverage periods(int n) {
		return new SMA(operand(), n);
	}


}
