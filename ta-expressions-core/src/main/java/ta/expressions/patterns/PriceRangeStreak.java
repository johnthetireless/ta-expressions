package ta.expressions.patterns;

import ta.expressions.common.stats.Streak;
import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.indicators.PriceRange;

/**
 * Returns a positive number N when price range has widened for N consecutive periods.
 * Returns a negative number -N when price range has narrowed for N consecutive periods.
 * <p>
 * Expanding ranges in an up trend signal increasing eagerness from buyers.
 * Expanding ranges in a down trend signal increasing eagerness from sellers.
 * Contracting ranges show decreasing eagerness.
 */
public class PriceRangeStreak extends BasicAnalyticFunction {

	public PriceRangeStreak() {
		super("PriceRangeStreak", new Streak(PriceRange.INSTANCE));
	}

}
