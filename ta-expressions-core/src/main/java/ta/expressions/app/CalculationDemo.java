package ta.expressions.app;

import java.util.List;

import ta.expressions.core.Aggregate;
import ta.expressions.indicators.adx.ADX;
import ta.expressions.indicators.adx.MinusDI;
import ta.expressions.indicators.adx.PlusDI;

public class CalculationDemo {

	public static void main(String[] args) {

		Calculation calculation = Calculation.of(
				new ADX(14), new PlusDI(14), new MinusDI(14)
				);
		
		List<Aggregate> aggs = CsvReader.readFile("SPY.csv");
		for ( int i = 0; i < aggs.size(); i++ ) {
			Aggregate input = aggs.get(i);
			CalculationResult result = calculation.apply(input);
			System.out.println(result);
		}	
	}
}
