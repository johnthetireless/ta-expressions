package ta.expressions.demo.strategy;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ta.expressions.core.Aggregate;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.MultipleStrategyExecution;
import ta.expressions.strategy.Strategy;
import ta.expressions.strategy.SymbolBookkeeper;

public class MultipleStrategyMultipleDatasetTest {

	public static void main(String[] args) {
		
		List<Strategy> strategies = List.of(
				new EMACrossoverStrategy(5, 21),
				new ThreeEMAStrategy(5, 21, 63)
				);
		
		List<SymbolBookkeeper> symbolBookkeepers = new ArrayList<>();
		
		
		Path dir = Paths.get("src/main/resources/candles");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		    	SymbolBookkeeper newBookkeeper = processFile(file, strategies);
		    	symbolBookkeepers.add(newBookkeeper);
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    System.err.println(x);
		}
		
		System.out.println(symbolBookkeepers.size());
//		bookkeepers.forEach(System.out::println);
		
		for ( SymbolBookkeeper b : symbolBookkeepers ) {
//			if ( b.symbol().equals("ZION") ) {
				b.activeBooks().forEach(System.out::println);
//				List<TradingBook> byCount = b.sort(TradingBook::count);
//				byCount.forEach(System.out::println);
//			}
			
		}
		
//		System.out.println("positions closed " + book.count());
//		System.out.println("total price change " + book.totalPriceChange());
	}
	
	static SymbolBookkeeper processFile(Path file, List<Strategy> strategies) {
    	String filename = file.getFileName().toString();
    	String symbol = filename.substring(0, filename.length() - 4);
		List<Aggregate> aggs = CandleReader.readFile(file);
		AggregateDataset dataset = new AggregateDataset(symbol, aggs);
		return processDataset(dataset, strategies);
		
	}
	
	static SymbolBookkeeper processDataset(AggregateDataset dataset, List<Strategy> strategies) {
//		if ( dataset.priceChange().signum() < 0 ) {
//			return List.of();
//		}
		MultipleStrategyExecution exec = new MultipleStrategyExecution(dataset.name(), strategies);
    	SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
    	dataset.data().forEach(sg);
		return exec.bookkeeper();
	}

}