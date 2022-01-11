package ta.expressions.demo.strategy;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.NumericExpression;

public class SignalLineCrossoverStrategy extends SimpleCrossoverStrategy {
	
	private final String name;

	public SignalLineCrossoverStrategy(NumericExpression e, int n) {
		super(e, new EMA(e, n));
		this.name = "SignalLineCrossoverStrategy(" + e.representation() + ", " + n + ")";
	}

	@Override
	public String name() {
		return name;
	}


}
