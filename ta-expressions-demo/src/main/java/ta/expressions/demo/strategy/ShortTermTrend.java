package ta.expressions.demo.strategy;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.macd.MACD;
import ta.expressions.indicators.stochastics.StochasticOscillator;
import ta.expressions.strategy.Strategy;

public class ShortTermTrend extends Strategy {

	private BooleanExpression entry;
	private BooleanExpression exit;
	
	public ShortTermTrend() {
		
		MACD macd = new MACD(10,30);
		NumericExpression macdSignal = macd.signal(10);
		StochasticOscillator stoch = new StochasticOscillator(20, 3);
		NumericExpression stochSignal = new EMA(stoch, 3);
		
		BooleanExpression entry1 = macd.crossedOver(macdSignal)
							.and(stoch.greaterThan(stochSignal));
		BooleanExpression entry2 = stoch.crossedOver(stochSignal)
							.and(macd.greaterThan(macdSignal));
		
		BooleanExpression exit1 = macd.crossedUnder(macdSignal)
				.and(stoch.lessThan(stochSignal));
		BooleanExpression exit2 = stoch.crossedUnder(stochSignal)
				.and(macd.lessThan(macdSignal));
		
		
//		this.entry = macd.greaterThan(macdSignal).and(stoch.greaterThan(stochSignal));
//		this.exit = macd.lessThan(macdSignal).and(stoch.lessThan(stochSignal));
		this.entry = entry1.or(entry2);
		this.exit = exit1.or(exit2);
	}

	@Override
	public String name() {
		return "ShortTermTrend";
	}

	public BooleanExpression entry() {
		return entry;
	}

	public BooleanExpression exit() {
		return exit;
	}
	
	
	
	
}
