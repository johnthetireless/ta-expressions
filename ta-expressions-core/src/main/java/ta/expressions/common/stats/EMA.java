package ta.expressions.common.stats;

import java.math.BigDecimal;

import ta.expressions.core.NumericExpression;

public class EMA extends RecursiveMA {

	public EMA(NumericExpression e, int n) {
		super(functionRepresentation("EMA", e, n), e, n, new BigDecimal(2.0 / (n + 1)));
	}

	@Override
	public MovingAverage periods(int n) {
		return new EMA(operand(), n);
	}

}
