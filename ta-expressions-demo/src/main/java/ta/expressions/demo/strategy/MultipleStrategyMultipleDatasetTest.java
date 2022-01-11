package ta.expressions.demo.strategy;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ta.expressions.core.Aggregate;
import ta.expressions.indicators.CCI;
import ta.expressions.indicators.CMO;
import ta.expressions.indicators.TrendIntensityIndex;
import ta.expressions.indicators.stochastics.StochasticMomentumIndex;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.Bookkeeper;
import ta.expressions.strategy.MultipleStrategyExecution;
import ta.expressions.strategy.Strategy;
import ta.expressions.strategy.StrategyBookkeeper;
import ta.expressions.strategy.SymbolBookkeeper;
import ta.expressions.strategy.TradingBook;

/**
 * This is a rough attempt to compare a list of strategies
 * by running them over 505 data sets.
 * Each data set has 5 years of daily OHLCV for an S&P500 stock; there are ~1300 OHLCV values per file.
 * <p>
 * Current close prices are recorded as the strategies entry and exit positions.
 * These prices are used to calculate a price change, which is simply summed.
 * This is a naive approach, but it is considered a good start.
 * 
 * This is not a natural use case; one would never do this with real data/money.
 * The code below is a bit convoluted as a result.  
 * A database to record all the data produced by this example would be a good idea.
 * Over 100,000 positions are opened and closed for most tests.
 * 20 strategies x 505 securities x 5 years of data
 * This takes 2-3 minutes to run.  It could be optimized, but this is unnatural, so it may not be.
 *  <p>
 * 
 */
public class MultipleStrategyMultipleDatasetTest {

	public static void main(String[] args) {
		
		List<Strategy> strategies = new ArrayList<>();
		for ( int i = 5; i <= 100; i += 5 ) {
			strategies.add(new CenterLineCrossoverStrategy(new StochasticMomentumIndex(i), 0));
		}

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
					if ( book.strategyName().equalsIgnoreCase(s.name()) ) {
						sb.add(book);
					}
				}
			}
		}
		
		List<BookkeeperValue> values = new ArrayList<>();
		for ( StrategyBookkeeper sb : strategyBookkeepers.values() ) {
//			System.out.println(sb.toString() + " " + sb.total(TradingBook::totalPriceChange));
			BigDecimal total = sb.total(TradingBook::totalPriceChange);
			BigDecimal count = sb.total(TradingBook::count);
			values.add(new BookkeeperValue(sb, total, count));
		}	
		
		Comparator<BookkeeperValue> comp = (a,b) -> b.value.compareTo(a.value);
		Collections.sort(values, comp);
		values.forEach(System.out::println);
	}
	
	static SymbolBookkeeper processFile(Path file, List<Strategy> strategies) {
    	String filename = file.getFileName().toString();
    	String symbol = filename.substring(0, filename.length() - 4);
		List<Aggregate> aggs = CandleReader.readFile(file);
		return processDataset(symbol, aggs, strategies);
		
	}
	
	static SymbolBookkeeper processDataset(String symbol, List<Aggregate> aggs, List<Strategy> strategies) {
		MultipleStrategyExecution exec = new MultipleStrategyExecution(symbol, strategies);
    	SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
    	aggs.forEach(sg);
		return exec.bookkeeper();
	}
	
	static class BookkeeperValue {
		private final Bookkeeper bookkeeper;
		private final BigDecimal value;
		private final BigDecimal count;
		public BookkeeperValue(Bookkeeper bookkeeper, BigDecimal value, BigDecimal count) {
			super();
			this.bookkeeper = bookkeeper;
			this.value = value;
			this.count = count;
		}
		@Override
		public String toString() {
			return "BookkeeperValue [bookkeeper=" + bookkeeper + ", value=" + value + ", count=" + count + "]";
		}
		
	}

}
