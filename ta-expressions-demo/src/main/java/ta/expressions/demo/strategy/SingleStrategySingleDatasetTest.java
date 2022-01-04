package ta.expressions.demo.strategy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import ta.expressions.core.Aggregate;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.Strategy;
import ta.expressions.strategy.StrategyExecution;
import ta.expressions.strategy.TradingBook;

/**
 * Tests a single strategy using a single data set.
 * This simulates buy/sell market orders, using the current close price as the fill price.
 * This is a start.
 *
 */
public class SingleStrategySingleDatasetTest {

	public static void main(String[] args) {

		String symbol = "AAPL";
		String pathname = "src/main/resources/candles/" + symbol + ".csv";
		Path file = Paths.get(pathname);
		List<Aggregate> aggregates = CandleReader.readFile(file);
		
//		Strategy strategy = new ThreeEMAStrategy(7, 21, 63);
		Strategy strategy = new TrendIntensityIndexStrategy1(40);
		StrategyExecution exec = new StrategyExecution(strategy, symbol);
		
		SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
		aggregates.forEach(sg);
		
    	Aggregate last = aggregates.get(aggregates.size() - 1);
    	Instant instant = Instant.ofEpochSecond(last.timestamp());
    	LocalDate date = LocalDate.ofInstant(instant, ZoneId.systemDefault());
    	System.out.println("# inputs: " + aggregates.size());
    	System.out.println("Last date: " + date);
    	
    	exec.closeCurrentPosition(last.close(), last.timestamp());
		TradingBook book = exec.book();
		System.out.println(book);
		book.positions().forEach(System.out::println);
//		book.sort(Position::priceChange).forEach(System.out::println);
	}

}
