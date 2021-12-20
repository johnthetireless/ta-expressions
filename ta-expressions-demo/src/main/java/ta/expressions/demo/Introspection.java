package ta.expressions.demo;

import java.util.List;
import java.util.Set;

import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.RSI;
import ta.expressions.indicators.macd.MACD;

/**
 * Shows how to examine expressions to see how they were formed, calculated, etc.
 * 
 */
public class Introspection {

	public static void main(String[] args) {
		
		MACD macd = new MACD(12,26);
		RSI rsi = new RSI(14);
		
		BooleanExpression bullish = macd.crossedOver(macd.signal(9))
									.and(rsi.lessThan(70));

		System.out.println("Rule....");
		System.out.println(bullish);
		System.out.println();
		
		System.out.println("Terms....");
		bullish.terms().forEach(System.out::println);
		System.out.println();
				
		System.out.println("Subexpressions (first term only)...");
		List<NumericExpression> terms = List.copyOf(bullish.terms());
		NumericExpression first = terms.get(0);
		first.subexpressions().forEach(System.out::println);

	}

}
