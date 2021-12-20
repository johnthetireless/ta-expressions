package ta.expressions.common.stats;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public abstract class MovingAverage extends AnalyticFunction {

	private final NumericExpression e;
	private final int n;
	
	public MovingAverage(String representation, NumericExpression e, int n) {
		super(representation);
		this.e = e;
		this.n = n;
	}

	public NumericExpression operand() {
		return e;
	}

	public int periods() {
		return n;
	}
	
	public abstract MovingAverage periods(int n);
}
