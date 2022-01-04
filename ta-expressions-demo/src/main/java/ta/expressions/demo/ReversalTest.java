package ta.expressions.demo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import ta.expressions.indicators.volatility.VolatilityRatio;
import ta.expressions.patterns.ClosePriceReversal;
import ta.expressions.patterns.Gap;
import ta.expressions.patterns.HookReversal;
import ta.expressions.patterns.IslandReversal;
import ta.expressions.patterns.KeyReversal;
import ta.expressions.patterns.OpenCloseReversal;
import ta.expressions.patterns.PivotPointReversal;
import ta.expressions.patterns.TrendType;

public class ReversalTest {

	public static void main(String[] args) {
		
		TrendType trendType = TrendType.UPTREND;
		int minTrendPeriods = 2;  // at least 2 consecutive higher high and higher low
		double nearThreshold = 0.2; // near means less than 20% of high low range
		
		KeyReversal keyReversal = new KeyReversal(trendType, minTrendPeriods);
		System.out.println(keyReversal.toString() + " = " + keyReversal.formula());
		
		OpenCloseReversal openCloseReversal = new OpenCloseReversal(trendType, minTrendPeriods, nearThreshold);
		System.out.println(openCloseReversal.toString() + " = " + openCloseReversal.formula());
		
		ClosePriceReversal closePriceReversal = new ClosePriceReversal(trendType, minTrendPeriods, nearThreshold);
		System.out.println(closePriceReversal.toString() + " = " + closePriceReversal.formula());
		
		HookReversal hookReversal = new HookReversal(trendType, minTrendPeriods, nearThreshold);
		System.out.println(hookReversal.toString() + " = " + hookReversal.formula());
		
		IslandReversal islandReversal = new IslandReversal(trendType, minTrendPeriods);
		System.out.println(islandReversal.toString() + " = " + islandReversal.formula());
		
		PivotPointReversal pivotPointReversal = new PivotPointReversal(trendType, minTrendPeriods);
		System.out.println(pivotPointReversal.toString() + " = " + pivotPointReversal.formula());

		System.out.println();
		
		Filter filter = new Filter(Set.of(
				Gap.UP, Gap.DOWN, new VolatilityRatio(14).greaterThan(1.5)
//				keyReversal, openCloseReversal, closePriceReversal, hookReversal, islandReversal, pivotPointReversal
				));
		Path dir = Paths.get("src/main/resources/candles");
		
		List<Filter.Match> matches = filter.apply(dir);
		System.out.println("Matches: " + matches.size());
		matches.forEach(System.out::println);
		
	}

}
