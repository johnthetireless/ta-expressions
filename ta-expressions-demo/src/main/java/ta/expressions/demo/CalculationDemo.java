package ta.expressions.demo;

import java.util.List;

import ta.expressions.app.Calculation;
import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.indicators.adx.ADX;
import ta.expressions.indicators.adx.ADXR;
import ta.expressions.indicators.adx.MinusDI;
import ta.expressions.indicators.adx.PlusDI;

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
				new PlusDI(14), new MinusDI(14), new ADX(14), new ADXR(14, 5)
				);
		
		List<Aggregate> aggs = CsvReader.readFile("SPY.csv");
		for ( int i = 0; i < aggs.size(); i++ ) {
			System.out.println(calculation.apply(aggs.get(i)));
		}	
	}
}
