package ta.expressions.demo.strategy;

import ta.expressions.common.stats.EMA;
import ta.expressions.common.stats.Slope;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.strategy.Strategy;

public class EMACrossoverStrategy extends Strategy {
	
	private final String name;
	private final BooleanExpression entry;
	private final BooleanExpression exit;

	public EMACrossoverStrategy(int n1, int n2) {
		this.name = "EMACrossoverStrategy(" + n1 + "," + n2 + ")";
		NumericExpression price = ClosePrice.INSTANCE;
		NumericExpression ema1 = new EMA(price, n1);
		NumericExpression ema2 = new EMA(price, n2);
		NumericExpression slope = new Slope(ema2, n2);
		this.entry = ema1.crossedOver(ema2).and(slope.greaterThan(0));
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
