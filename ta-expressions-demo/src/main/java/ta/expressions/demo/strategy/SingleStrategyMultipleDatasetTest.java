package ta.expressions.demo.strategy;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ta.expressions.core.Aggregate;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.Strategy;
import ta.expressions.strategy.StrategyBookkeeper;
import ta.expressions.strategy.StrategyExecution;
import ta.expressions.strategy.TradingBook;

public class SingleStrategyMultipleDatasetTest {

	public static void main(String[] args) {

		Strategy strategy = new ThreeEMAStrategy(5, 21, 63);
		StrategyBookkeeper bookkeeper = new StrategyBookkeeper(strategy);
		
		Path dir = Paths.get("src/main/resources/candles");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		    	String filename = file.getFileName().toString();
		    	String symbol = filename.substring(0, filename.length() - 4);
				List<Aggregate> aggs = CandleReader.readFile(file);
		    	StrategyExecution exec = new StrategyExecution(strategy, symbol);
		    	SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
		    	aggs.forEach(sg);
		    	bookkeeper.add(exec.book());
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    System.err.println(x);
		}
		
		System.out.println("Active books: " + bookkeeper.activeBookCount());
		System.out.println(bookkeeper.total(TradingBook::totalPriceChange));
		bookkeeper.sort(TradingBook::totalPriceChange).forEach(System.out::println);
	}

}
