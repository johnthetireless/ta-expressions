package ta.expressions.demo.strategy;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.TrendIntensityIndex;
import ta.expressions.strategy.Strategy;

public class TrendIntensityIndexStrategy3 extends Strategy {

	private final String name;
	private final BooleanExpression entry;
	private final BooleanExpression exit;
	
	public TrendIntensityIndexStrategy3(int n1, int n2) {
		this.name = "TrendIntensityIndexStrategy3(" + n1 + "," + n2 + ")";
		NumericExpression tii = new TrendIntensityIndex(n1);
		NumericExpression signal = new EMA(tii, n2);
		this.entry = tii.crossedOver(signal);
		this.exit = tii.crossedUnder(signal);
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
