package ta.expressions.demo.strategy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import ta.expressions.core.Aggregate;
import ta.expressions.indicators.macd.MACD;
import ta.expressions.indicators.stochastics.StochasticMomentumIndex;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.AnalysisDataset;
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
 * <p>
 * This compares 4851 simple MACD strategies, created using every valid combination
 * of n1 and n2 from 2 to 100 (n1 < n2).  Data set has ~1300 records.  
 * Run time is 3-4 minutes.  
 */
public class MultipleStrategySingleDatasetTest {

	public static void main(String[] args) {

		String symbol = "AAPL";

		int start = 2;
		int step = 1;
		int end = 100;
		
		List<Strategy> strategies = new ArrayList<>();
		for ( int i = start; i <= end - step; i += step ) {
			for ( int j = i + step; j <= end; j+= step ) {
				strategies.add(new CenterLineCrossoverStrategy(new MACD(i,j), 0));				
			}
		}
		
		System.out.println("# strategies: " + strategies.size());
		Instant startInstant = Instant.now();
		
		AnalysisDataset ds = createDataset(symbol);
		System.out.println(ds);
		
    	MultipleStrategyExecution exec = new MultipleStrategyExecution(symbol, strategies);
    	SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
    	ds.aggregates().forEach(sg);
    	
    	exec.closeCurrentPositions(ds.last().close(), ds.last().timestamp());
    	
    	SymbolBookkeeper bookkeeper = exec.bookkeeper();
    	System.out.println(bookkeeper);
    	List<TradingBook> books = bookkeeper.sort(TradingBook::totalPriceChange);
    	for ( TradingBook b : books ) {
    		System.out.println(b);
//    		b.positions().forEach(System.out::println);
//    		System.out.println();
    	}
		Instant endInstant = Instant.now();
		Duration duration = Duration.between(startInstant, endInstant);
		System.out.println("Duration: " + duration.getSeconds());
		
	}
	
	static LocalDate localDate(long timestamp) {
		Instant i = Instant.ofEpochSecond(timestamp);
		return LocalDate.ofInstant(i, ZoneId.systemDefault());
	}
	
	static AnalysisDataset createDataset(String symbol) {
		String pathname = "src/main/resources/candles/" + symbol + ".csv";
		Path file = Paths.get(pathname);
		List<Aggregate> aggregates = CandleReader.readFile(file);
		return new AnalysisDataset(symbol, aggregates);

	}

}
