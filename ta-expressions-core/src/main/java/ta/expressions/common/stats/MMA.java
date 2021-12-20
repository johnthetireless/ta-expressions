package ta.expressions.common.stats;

import java.math.BigDecimal;

import ta.expressions.core.NumericExpression;

public class MMA extends RecursiveMA {

	public MMA(NumericExpression e, int n) {
		super(functionRepresentation("MMA", e, n), e, n, new BigDecimal(1.0 / n));
	}

	@Override
	public MovingAverage periods(int n) {
		return new EMA(operand(), n);
	}

}
