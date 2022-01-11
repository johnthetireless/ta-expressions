package ta.expressions.demo.strategy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import ta.expressions.core.Aggregate;
import ta.expressions.indicators.AccelerationDeceleration;
import ta.expressions.indicators.AwesomeOscillator;
import ta.expressions.indicators.CCI;
import ta.expressions.indicators.TrendIntensityIndex;
import ta.expressions.indicators.aroon.AroonOscillator;
import ta.expressions.indicators.stochastics.StochasticMomentumIndex;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.strategy.AnalysisDataset;
import ta.expressions.strategy.MultipleStrategyExecution;
import ta.expressions.strategy.Strategy;
import ta.expressions.strategy.SymbolBookkeeper;
import ta.expressions.strategy.TradingBook;

public class CurveFitting {

	public static void main(String[] args) {
		
		List<Strategy> strategies = new ArrayList<>();
		for ( int i = 5; i <= 100; i += 5 ) {
			strategies.add(new CenterLineCrossoverStrategy(new StochasticMomentumIndex(i), 0));
		}
		
		String symbol = "OKE"; 
		String pathname = "src/main/resources/candles/" + symbol + ".csv";
		Path file = Paths.get(pathname);
		List<Aggregate> aggregates = CandleReader.readFile(file);
		
		AnalysisDataset ds = new AnalysisDataset(symbol, aggregates);
		System.out.println(ds);
		
    	MultipleStrategyExecution exec = new MultipleStrategyExecution(symbol, strategies);
    	SignalGenerator sg = new SignalGenerator(exec.expressions(), exec);
    	aggregates.forEach(sg);
    	
    	exec.closeCurrentPositions(ds.last().close(), ds.last().timestamp());
    	
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
