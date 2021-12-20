package ta.expressions.common.change;

import ta.expressions.core.NumericExpression;

public class Falling extends RisingFalling {

	public static final String KEYWORD = "Falling";
	
	public Falling(NumericExpression e, int n, int missesAllowed) {
		super(KEYWORD, e, n, missesAllowed, (a,b) -> a.compareTo(b) <= 0);
	}

}
