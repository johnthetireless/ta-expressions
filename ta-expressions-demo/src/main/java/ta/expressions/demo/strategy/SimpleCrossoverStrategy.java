package ta.expressions.demo.strategy;

import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.strategy.Strategy;

public abstract class SimpleCrossoverStrategy extends Strategy {

	private final BooleanExpression entry;
	private final BooleanExpression exit;

	public SimpleCrossoverStrategy(NumericExpression e1, NumericExpression e2) {
		this.entry = e1.crossedOver(e2);
		this.exit = e1.crossedUnder(e2);
	}

	@Override
	public BooleanExpression entry() {
		return entry;
	}

	@Override
	public BooleanExpression exit() {
		return exit;
	}

}
