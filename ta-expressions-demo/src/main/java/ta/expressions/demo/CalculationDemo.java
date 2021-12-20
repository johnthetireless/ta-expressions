package ta.expressions.demo;

import java.util.List;

import ta.expressions.app.Calculation;
import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.indicators.adx.ADX;
import ta.expressions.indicators.adx.MinusDI;
import ta.expressions.indicators.adx.PlusDI;

/**
 * Calculates values for a few indicators: ADX, PlusDI and MinusDI
 * 
 * Useful for charting, creating CSV files, etc.
 *
 */
public class CalculationDemo {

	public static void main(String[] args) {

		Calculation calculation = Calculation.of(
				new ADX(14), new PlusDI(14), new MinusDI(14)
				);
		
		List<Aggregate> aggs = CsvReader.readFile("SPY.csv");
		for ( int i = 0; i < aggs.size(); i++ ) {
			System.out.println(calculation.apply(aggs.get(i)));
		}	
	}
}
