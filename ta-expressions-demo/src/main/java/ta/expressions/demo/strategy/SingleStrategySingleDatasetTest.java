package ta.expressions.demo.strategy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ta.expressions.core.Aggregate;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.Strategy;
import ta.expressions.strategy.StrategyExecution;
import ta.expressions.strategy.TradingBook;

public class SingleStrategySingleDatasetTest {

	public static void main(String[] args) {

		String symbol = "AAPL";
		String pathname = "src/main/resources/candles/" + symbol + ".csv";
		Path file = Paths.get(pathname);
		List<Aggregate> aggregates = CandleReader.readFile(file);
		
		Strategy strategy = new ThreeEMAStrategy(7, 21, 63);
		StrategyExecution exec = new StrategyExecution(strategy, symbol);
		
		SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
		aggregates.forEach(sg);
		
		TradingBook book = exec.book();
		System.out.println(book);
		book.positions().forEach(System.out::println);
	}

}
