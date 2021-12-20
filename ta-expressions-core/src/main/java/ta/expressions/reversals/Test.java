package ta.expressions.reversals;

import java.util.Set;

import ta.expressions.core.BooleanExpression;

public class Test {

	public static void main(String[] args) {


		BooleanExpression b1 = ReversalPatternType.OPEN_CLOSE.upturn(0.3);
		BooleanExpression b2 = ReversalPatternType.OPEN_CLOSE.downturn(0.3);
		System.out.println(b1);
		System.out.println(b2);
		
		Set<ReversalPatternType> patternTypes = Set.of(
				ReversalPatternType.OPEN_CLOSE, 
				ReversalPatternType.CLOSE_PRICE,
				ReversalPatternType.KEY,
				ReversalPatternType.HOOK
				);
		
		Reversal r = new Reversal(ReversalTrendType.UPTREND, 5, 1, 0.2, patternTypes);
		System.out.println(r);
		
	}

}
