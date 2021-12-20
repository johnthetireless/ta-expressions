package ta.expressions.demo;

import java.util.Set;

import ta.expressions.app.CsvReader;
import ta.expressions.core.BooleanExpression;
import ta.expressions.indicators.RSI;
import ta.expressions.indicators.macd.MACD;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.signal.SignalPrinter;

/**
 * Generates signals for simple MACD/signal crossovers
 *
 */
public class QuickStart {

	public static void main(String[] args) {
		
		MACD macd = new MACD(12,26);
		RSI rsi = new RSI(14);
		
		BooleanExpression bullish = macd.crossedOver(macd.signal(9))
									.and(rsi.lessThan(70));
		
		BooleanExpression bearish = macd.crossedUnder(macd.signal(9));
		
		SignalGenerator sg = new SignalGenerator(
									Set.of(bullish, bearish), 
									new SignalPrinter());

		CsvReader.readFile("SPY.csv") 
					.forEach(sg::accept);
	}

}
