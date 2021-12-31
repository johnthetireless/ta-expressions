package ta.expressions.demo;

import java.util.List;

import ta.expressions.app.Calculation;
import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.indicators.CoppockCurve;
import ta.expressions.indicators.HMA;
import ta.expressions.indicators.WMA;
import ta.expressions.indicators.adx.ADX;
import ta.expressions.indicators.adx.ADXR;
import ta.expressions.indicators.adx.MinusDI;
import ta.expressions.indicators.adx.PlusDI;
import ta.expressions.indicators.stochastics.StochasticMomentumIndex;
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
				ClosePrice.INSTANCE,
				new HMA(ClosePrice.INSTANCE, 20)
				);
		
		List<Aggregate> aggs = CsvReader.readFile("SPY.csv");
		for ( int i = 0; i < aggs.size(); i++ ) {
			System.out.println(calculation.apply(aggs.get(i)));
		}	
	}
}
