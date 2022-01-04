package ta.expressions.demo.strategy;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ta.expressions.core.Aggregate;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.MultipleStrategyExecution;
import ta.expressions.strategy.Strategy;
import ta.expressions.strategy.StrategyBookkeeper;
import ta.expressions.strategy.SymbolBookkeeper;
import ta.expressions.strategy.TradingBook;

public class MultipleStrategyMultipleDatasetTest {

	public static void main(String[] args) {
		
		List<Strategy> strategies = new ArrayList<>();
		for ( int i = 7; i < 15; i+=2 ) {
			strategies.add(new EMACrossoverStrategy(i, i * 3));
			strategies.add(new ThreeEMAStrategy(i, i * 3, i * 9));
		}
		strategies.add(new TrendIntensityIndexStrategy1(40));
		strategies.add(new TrendIntensityIndexStrategy2(40));
		strategies.add(new TrendIntensityIndexStrategy3(40,12));
		
		List<SymbolBookkeeper> symbolBookkeepers = new ArrayList<>();
		Map<String, StrategyBookkeeper> strategyBookkeepers = new HashMap<>();
		
		for ( Strategy s : strategies ) {
			strategyBookkeepers.put(s.name(), new StrategyBookkeeper(s));
		}
		
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

		for ( SymbolBookkeeper b : symbolBookkeepers ) {
			for ( Strategy s : strategies ) {
				StrategyBookkeeper sb = strategyBookkeepers.get(s.name());
				for ( TradingBook book : b.books() ) {
					if ( book.traderName().equalsIgnoreCase(s.name()) ) {
						sb.add(book);
					}
				}
			}
		}
		
		for ( StrategyBookkeeper sb : strategyBookkeepers.values() ) {
			System.out.println(sb.toString() + " " + sb.total(TradingBook::totalPriceChange));
		}	
		
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
