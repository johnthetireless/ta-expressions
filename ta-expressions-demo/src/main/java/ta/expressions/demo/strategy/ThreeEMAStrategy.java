package ta.expressions.demo.strategy;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.strategy.Strategy;

public class ThreeEMAStrategy extends Strategy {

	private final String name;
	private final BooleanExpression entry;
	private final BooleanExpression exit;

	public ThreeEMAStrategy(int n1, int n2, int n3) {
		this.name = "ThreeEMAStrategy(" + n1 + "," + n2 + "," + n3 + ")";
		NumericExpression price = ClosePrice.INSTANCE;
		NumericExpression ema1 = new EMA(price, n1);
		NumericExpression ema2 = new EMA(price, n2);
		NumericExpression ema3 = new EMA(price, n3);
		this.entry = ema2.crossedOver(ema3).and(ema1.greaterThan(ema2));
		this.exit = ema1.crossedUnder(ema2);
	}

	@Override
	public String name() {
		return name;
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
