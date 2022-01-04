package ta.expressions.demo.strategy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import ta.expressions.core.BooleanExpression;
import ta.expressions.demo.Filter;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class FilterTest {

	public static void main(String[] args) {
		
		BooleanExpression gapUp = LowPrice.INSTANCE.greaterThan(HighPrice.INSTANCE.previous());
		BooleanExpression gapDown = HighPrice.INSTANCE.lessThan(LowPrice.INSTANCE.previous());
		
//		NumericExpression averagePriceRange = new SMA(PriceRange.INSTANCE, 25);
//		BooleanExpression closeNearLow = ClosePrice.INSTANCE.minus(LowPrice.INSTANCE)
//								.lessThan(averagePriceRange.multipliedBy(0.1));
//		BooleanExpression openNearHigh = HighPrice.INSTANCE.minus(OpenPrice.INSTANCE)
//				.lessThan(averagePriceRange.multipliedBy(0.1));
//		
//		BooleanExpression newHigh = new AroonUp(25).equalTo(100);
//		BooleanExpression openCloseReversal = newHigh.and(openNearHigh).and(closeNearLow);

		Filter filter = new Filter(Set.of(gapUp, gapDown));
		Path dir = Paths.get("src/main/resources/candles");
		
		List<Filter.Match> matches = filter.apply(dir);
		System.out.println("Matches: " + matches.size());
		matches.forEach(System.out::println);
	}

}
