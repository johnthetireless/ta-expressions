package ta.expressions.app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ta.expressions.core.Aggregate;
import ta.expressions.core.NumericExpression;

public class Test {

	public static void main(String[] args) {
		
		List<String> input = List.of("ADX(14)", "PlusDI(14)", "MinusDI(14)");
		
		Set<NumericExpression> expressions = new HashSet<>();
		TermParser parser = new TermParser();
		try {
			for ( String s : input ) {
				NumericExpression e = parser.apply(s);
				expressions.add(e);
			}
		}
		catch ( NumberFormatException nfe ) {
			System.out.println("Cannot parse: " + input);
		}
		
		Calculation calculation = Calculation.of(expressions);		
		List<Aggregate> aggs = CsvReader.readFile("TD.csv");
		for ( int i = 0; i < aggs.size(); i++ ) {
			Aggregate a = aggs.get(i);
			CalculationResult result = calculation.apply(a);
			System.out.println(result);
		}	

		
	}

}
