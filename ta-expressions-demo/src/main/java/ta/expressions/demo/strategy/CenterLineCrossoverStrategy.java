package ta.expressions.demo.strategy;

import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;

public class CenterLineCrossoverStrategy extends SimpleCrossoverStrategy {

	private final String name;

	public CenterLineCrossoverStrategy(NumericExpression e, Number n) {
		super(e, Constant.valueOf(n));
		this.name = "CenterLineCrossoverStrategy(" + e.representation() + ", " + n + ")";
	}

	@Override
	public String name() {
		return name;
	}

}
