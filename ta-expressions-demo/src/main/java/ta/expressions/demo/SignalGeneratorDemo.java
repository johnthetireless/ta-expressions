package ta.expressions.demo;

import java.util.List;

import ta.expressions.common.stats.SMA;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.signal.SignalEvaluation;

/**
 * Simple count of signals from entry/exit expressions.
 * 
 * First step in strategy evaluation.
 * 
 * Higher-level components for back-testing and live trading are under development.
 *
 */
public class SignalGeneratorDemo {
	
	public static void main(String[] args) {

		NumericExpression price = ClosePrice.INSTANCE;
		NumericExpression shortSMA = new SMA(price, 5);
		NumericExpression longSMA = new SMA(price, 50);
		
		BooleanExpression bullish = shortSMA.crossedOver(longSMA);
		BooleanExpression bearish = shortSMA.crossedUnder(longSMA);
		
		String symbol = "SPY";

		SignalEvaluation se = new SignalEvaluation(symbol, List.of(bullish, bearish));
		se.run();
		
	}	
}
