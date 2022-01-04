package ta.expressions.demo.strategy;

import java.math.MathContext;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import ta.expressions.core.Aggregate;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.MultipleStrategyExecution;
import ta.expressions.strategy.Strategy;
import ta.expressions.strategy.SymbolBookkeeper;
import ta.expressions.strategy.TradingBook;

/**
 * Compares a list of strategies by running each over a single data set.
 * <p>
 * Current close prices are recorded as the strategies entry and exit positions.
 * These prices are used to calculate a prices change, which is simply summed.
 * This is a naive approach, but it is considered a good start.
 */
public class MultipleStrategySingleDatasetTest {

	public static void main(String[] args) {

		List<Strategy> strategies = new ArrayList<>();
		for ( int i = 7; i < 15; i+=2 ) {
			strategies.add(new EMACrossoverStrategy(i, i * 3));
			strategies.add(new ThreeEMAStrategy(i, i * 3, i * 9));
		}
		strategies.add(new TrendIntensityIndexStrategy1(40));
		strategies.add(new TrendIntensityIndexStrategy2(40));
		strategies.add(new TrendIntensityIndexStrategy3(40,12));
		
		String symbol = "AMZN";
		String pathname = "src/main/resources/candles/" + symbol + ".csv";
		Path file = Paths.get(pathname);
		List<Aggregate> aggregates = CandleReader.readFile(file);
		
    	Aggregate first = aggregates.get(0);
    	Aggregate last = aggregates.get(aggregates.size() - 1);
    	System.out.println("# inputs: " + aggregates.size());
    	System.out.println("First date: " + localDate(first.timestamp()));
    	System.out.println("Last date: " + localDate(last.timestamp()));
    	System.out.println("First close: " + first.close());
    	System.out.println("Last close: " + last.close());
    	System.out.println("Change: " + last.close().subtract(first.close(), MathContext.DECIMAL64));

    	MultipleStrategyExecution exec = new MultipleStrategyExecution(symbol, strategies);
    	SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
    	aggregates.forEach(sg);
    	
    	exec.closeCurrentPositions(last.close(), last.timestamp());
    	
    	SymbolBookkeeper bookkeeper = exec.bookkeeper();
    	System.out.println(bookkeeper);
    	List<TradingBook> books = bookkeeper.sort(TradingBook::totalPriceChange);
    	for ( TradingBook b : books ) {
    		System.out.println(b);
//    		b.positions().forEach(System.out::println);
//    		System.out.println();
    	}

	}
	
	static LocalDate localDate(long timestamp) {
		Instant i = Instant.ofEpochSecond(timestamp);
		return LocalDate.ofInstant(i, ZoneId.systemDefault());
	}

}
