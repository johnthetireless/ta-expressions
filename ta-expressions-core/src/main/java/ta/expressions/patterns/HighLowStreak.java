package ta.expressions.patterns;

import java.math.BigDecimal;
import java.util.Set;

import ta.expressions.common.stats.Streak;
import ta.expressions.core.AnalysisContext;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

/**
 * Number of periods over which high and low price increase or decrease together.
 * 
 * A series of higher high and higher low is an up trend, according to Dow theory.
 * A series of lower high and lower low is a down trend.
 * It is not certain how long such a series needs to be.
 * Technically, any value >= 2 is an up trend and any value <= -2 is a down trend.
 * Checking for streaks of >= or <= 3 or 4 would seem more practical
 *
 */
public class HighLowStreak extends NumericExpression {

	private final Streak highStreak = new Streak(HighPrice.INSTANCE);
	private final Streak lowStreak = new Streak(LowPrice.INSTANCE);
	
	public HighLowStreak() {
		super("HighLowStreak");
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		BigDecimal hs = highStreak.getValue(context, index);
		BigDecimal ls = lowStreak.getValue(context, index);
		if ( hs.signum() > 0 && ls.signum() > 0 ) {
			return hs.min(ls);
		}
		if ( hs.signum() < 0 && ls.signum() < 0 ) {
			return hs.max(ls);
		}
		
		return BigDecimal.ZERO;
	}

	@Override
	public Set<NumericExpression> operands() {
		// TODO Auto-generated method stub
		return null;
	}

}
