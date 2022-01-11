package ta.expressions.demo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ta.expressions.app.Calculation;
import ta.expressions.app.CalculationResult;
import ta.expressions.core.Aggregate;
import ta.expressions.demo.strategy.CandleReader;
import ta.expressions.indicators.volatility.ATR;

public class VolatileStocks {

	public static void main(String[] args) {
		
		List<StockATR> atrReadings = new ArrayList<>();
		ATR atr = new ATR(14);
		
		Path dir = Paths.get("src/main/resources/candles");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		    	String filename = file.getFileName().toString();
		    	String symbol = filename.substring(0, filename.length() - 4);
				List<Aggregate> aggs = CandleReader.readFile(file);
				Calculation calc = Calculation.of(atr);
				for ( int i = 0; i < aggs.size(); i++ ) {
					Aggregate a = aggs.get(i);
					CalculationResult result = calc.apply(a);
					if ( i == aggs.size() - 1 ) {
						BigDecimal atrValue = result.valueOf(atr);
						BigDecimal normalizedATR = atrValue.divide(a.close(), MathContext.DECIMAL64).movePointRight(2);
						atrReadings.add(new StockATR(symbol, atrValue, normalizedATR));
					}	
				}
				
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    System.err.println(x);
		}

		Comparator<StockATR> comp = (a,b) -> b.natr.compareTo(a.natr);
		Collections.sort(atrReadings, comp);
		atrReadings.forEach(System.out::println);
		
	}

	
	static class StockATR {
		private final String symbol;
		private final BigDecimal atr;
		private final BigDecimal natr;
		public StockATR(String symbol, BigDecimal atr, BigDecimal natr) {
			super();
			this.symbol = symbol;
			this.atr = atr;
			this.natr = natr;
		}
		@Override
		public String toString() {
			return "StockATR [symbol=" + symbol + ", atr=" + atr + ", natr=" + natr + "]";
		}
				
	}
}
