package ta.expressions.reversals;

import java.util.HashSet;
import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;

public class Reversal extends BooleanExpression {
	

	private static final String KEYWORD = "Reversal";

	private ReversalTrendType trendType;
	private  BooleanExpression trend;	
	private Set<BooleanExpression> patterns;
	
	public Reversal(ReversalTrendType trendType, int risingFallingCount, int missesAllowed, 
			double nearPercentage, Set<ReversalPatternType> patternTypes) {
		super(functionRepresentation(trendType.keyword() + KEYWORD, risingFallingCount, missesAllowed, nearPercentage)
				+ " " + patternTypes);
		this.patterns = new HashSet<>();
		if ( trendType.equals(ReversalTrendType.UPTREND) ) {
			this.trend = Reversals.uptrend(risingFallingCount, missesAllowed);
			for (ReversalPatternType pt : patternTypes ) {
				patterns.add(pt.upturn(nearPercentage));
			}
		}
		else {
			this.trend = Reversals.downtrend(risingFallingCount, missesAllowed);			
			for (ReversalPatternType pt : patternTypes ) {
				patterns.add(pt.downturn(nearPercentage));
			}
		}
	}

	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		Boolean trending = trend.evaluate(context, index);
		if ( trending != null && trending) {
			for ( BooleanExpression pattern : patterns ) {
				Boolean matched = pattern.evaluate(context, index);
				if ( matched != null && matched ) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Set<NumericExpression> terms() {
		return Set.of();
	}

}
