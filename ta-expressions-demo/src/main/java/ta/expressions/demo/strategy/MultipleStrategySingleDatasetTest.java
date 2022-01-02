package ta.expressions.demo.strategy;

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

public class MultipleStrategySingleDatasetTest {

	public static void main(String[] args) {

		List<Strategy> strategies = new ArrayList<>();
		for ( int i = 7; i < 15; i+=2 ) {
			strategies.add(new EMACrossoverStrategy(i, i * 3));
			strategies.add(new ThreeEMAStrategy(i, i * 3, i * 9));
		}
		
		
		String symbol = "AMZN";
		String pathname = "src/main/resources/candles/" + symbol + ".csv";
		Path file = Paths.get(pathname);
		List<Aggregate> aggregates = CandleReader.readFile(file);
		
		MultipleStrategyExecution exec = new MultipleStrategyExecution(symbol, strategies);
    	SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
    	aggregates.forEach(sg);
    	
    	Aggregate last = aggregates.get(aggregates.size() - 1);
    	Instant instant = Instant.ofEpochSecond(last.timestamp());
    	LocalDate date = LocalDate.ofInstant(instant, ZoneId.systemDefault());
    	System.out.println("# inputs: " + aggregates.size());
    	System.out.println("Last date: " + date);
    	
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

}
