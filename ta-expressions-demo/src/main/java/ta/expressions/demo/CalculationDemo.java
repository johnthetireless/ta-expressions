package ta.expressions.demo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ta.expressions.app.Calculation;
import ta.expressions.common.stats.CorrelationCoefficient;
import ta.expressions.common.stats.SMA;
import ta.expressions.core.Aggregate;
import ta.expressions.demo.strategy.CandleReader;
import ta.expressions.indicators.TrendIntensityIndex;
import ta.expressions.indicators.TrendScore;
import ta.expressions.indicators.adx.ADX;
import ta.expressions.indicators.adx.ADXR;
import ta.expressions.indicators.adx.MinusDI;
import ta.expressions.indicators.adx.PlusDI;
import ta.expressions.indicators.variables.ClosePrice;

/**
 * Basic demo to show how to calculate and display values.
 * <p>
 * This example calculates values for PlusDI, MinusDI, ADX and ADXR. 
 * These are part of the Directional Movement System.
 * <p>
 * This simple approach can be useful for charting, creating CSV files, etc.
 * <p>
 */
public class CalculationDemo {

	public static void main(String[] args) {

		Calculation calculation = Calculation.of(
//				new PlusDI(14), new MinusDI(14), new ADX(14), new ADXR(14, 5)
				new CorrelationCoefficient(ClosePrice.INSTANCE, new SMA(ClosePrice.INSTANCE, 20), 20)
				);
		
		Path path = Paths.get("src/main/resources/candles/AAPL.csv");
		List<Aggregate> aggs = CandleReader.readFile(path);
		
		for ( Aggregate a : aggs ) {
			System.out.println(calculation.apply(a));
		}	
	}
}
