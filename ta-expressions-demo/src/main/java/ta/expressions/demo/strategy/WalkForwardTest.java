package ta.expressions.demo.strategy;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.indicators.macd.MACD;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.AnalysisDataset;
import ta.expressions.strategy.MultipleStrategyExecution;
import ta.expressions.strategy.Segmentation;
import ta.expressions.strategy.Strategy;
import ta.expressions.strategy.StrategyExecution;
import ta.expressions.strategy.SymbolBookkeeper;
import ta.expressions.strategy.TradingBook;
import ta.expressions.strategy.WalkForwardSegment;

/**
 * First attempt at walk forward optimization.
 * Creates N "segments" from a large data set 
 * (20 years of daily SPY > 5000 records).
 * Each segment has an in sample and an out of sample "slice".
 * Creates a list of every possible MACD strategy formed with 
 * MACD(n1,n2), where 2 <= n1,n2 <= 100 and n1 < n2.
 * Executes each MACD variant for each of the N in sample slices.
 * Sorts execution results by total price change (descending) and selects best for each section.
 * This takes ~40 minutes for all 4851 MACD combinations from 2..100.  
 * The planet is warm enough, so this will be done sparingly.
 * (With a step of 5 for both n1 and n2 we get 190 MACD combinations 
 * and this test takes less than 10 seconds)
 * 
 * Next we will run the best N over their respective out of sample slices.
 * This gives us N candidates; they will likely all use different parameters.
 * If the same parameters are best for several segments, this is probably the winner.
 * Especially if its OOS results are also good.
 * Otherwise... pick the latest "best strategy" and re-optimize every so often?
 */
public class WalkForwardTest {

	public static void main(String[] args) {
		
		String symbol = "SPY";
		
//		int dataSize = 5000;		// use up to 5000 inputs; the actual size of the input list might lead to strange segments
		int numberOfSegments = 7;
		double oosPercent = 0.25;  //"out of sample" percentage 
		
		int paramStart = 2;
		int paramStep = 1;
		int paramEnd = 100;
		
		String filename = symbol + ".csv";
		List<Aggregate> aggregates = CsvReader.readFile(filename);
		AnalysisDataset dataset = new AnalysisDataset(symbol, aggregates);
		System.out.println(dataset);
		
//		int actualDataSize = Math.min(dataSize,  aggregates.size());
		int actualDataSize = aggregates.size();
//		List<WalkForwardSegment> segments = Segmentation.createSegments(actualDataSize, numberOfSegments, oosPercent);
		List<WalkForwardSegment> segments = Segmentation.createAnchoredSegments(actualDataSize, numberOfSegments, oosPercent);
		segments.forEach(System.out::println);
		
		List<Strategy> strategies = new ArrayList<>();
		for ( int i = paramStart; i <= paramEnd - paramStep; i += paramStep ) {
			for ( int j = i + paramStep; j <= paramEnd; j += paramStep ) {
				strategies.add(new CenterLineCrossoverStrategy(new MACD(i,j), 0));				
			}
		}		
		System.out.println("# strategies: " + strategies.size());
		
		List<TradingBook> bestBooks = new ArrayList<>();
		
		Instant startInstant = Instant.now();
		for ( int i = 0; i < segments.size(); i++ ) {
			
			WalkForwardSegment segment = segments.get(i);
	    	MultipleStrategyExecution exec = new MultipleStrategyExecution(symbol, strategies);
	    	SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);	;

			int start = segment.inSample().start();
			int end = segment.inSample().end();
			for ( int j = start; j <= end; j++ ) {
				sg.accept(dataset.aggregates().get(j));
			}	    
			BigDecimal close = dataset.aggregates().get(end).close();
			long timestamp = dataset.aggregates().get(end).timestamp();
	    	exec.closeCurrentPositions(close, timestamp);
	    	
	    	SymbolBookkeeper bookkeeper = exec.bookkeeper();
//	    	System.out.println(bookkeeper);
	    	List<TradingBook> books = bookkeeper.sort(TradingBook::totalPriceChange);
	    	TradingBook best = books.get(0);
	    	System.out.println("Segment " + i + " best book: " + best);
	    	bestBooks.add(best);
	    }
		System.out.println();
//		bestBooks.forEach(System.out::println);
		
		List<Strategy> bestStrategies = bestBooks.stream().map(TradingBook::strategy).collect(Collectors.toList());
		bestStrategies.forEach(System.out::println);
		System.out.println();
		
		for ( int i = 0; i < segments.size(); i++ ) {
			WalkForwardSegment segment = segments.get(i);
			Strategy candidate = bestStrategies.get(i);
			StrategyExecution exec = new StrategyExecution(candidate, symbol);
			SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
			int start = segment.outOfSample().start();
			int end = segment.outOfSample().end();
			for ( int j = start; j <= end; j++ ) {
				sg.accept(dataset.aggregates().get(j));
			}	    
			BigDecimal close = dataset.aggregates().get(end).close();
			long timestamp = dataset.aggregates().get(end).timestamp();
	    	exec.closeCurrentPosition(close, timestamp);
			System.out.println("Out of sample results # " + i + " " + exec.book());
		}
		Instant endInstant = Instant.now();
		Duration duration = Duration.between(startInstant, endInstant);
		System.out.println("Tota run time (seconds): " + duration.toSeconds());
		
	}

}
