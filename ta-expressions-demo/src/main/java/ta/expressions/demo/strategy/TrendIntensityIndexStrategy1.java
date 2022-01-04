package ta.expressions.demo.strategy;

import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.TrendIntensityIndex;
import ta.expressions.strategy.Strategy;

public class TrendIntensityIndexStrategy1 extends Strategy {

	private final String name;
	private final BooleanExpression entry;
	private final BooleanExpression exit;
	
	public TrendIntensityIndexStrategy1(int n) {
		this.name = "TrendIntensityIndexStrategy1(" + n + ")";
		NumericExpression tii = new TrendIntensityIndex(n);
		this.entry = tii.crossedOver(20);
		this.exit = tii.crossedUnder(80);
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
