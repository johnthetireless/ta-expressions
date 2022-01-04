package ta.expressions.demo;

import java.util.Set;

import ta.expressions.app.CsvReader;
import ta.expressions.common.stats.SMA;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.signal.SignalPrinter;

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
		
		SignalGenerator generate = new SignalGenerator(Set.of(bullish, bearish), new SignalPrinter());

		CsvReader.readFile("SPY.csv").forEach(generate);
	}	
}
