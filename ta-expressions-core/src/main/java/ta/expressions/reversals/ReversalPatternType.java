package ta.expressions.reversals;

import java.util.function.DoubleFunction;

import ta.expressions.core.BooleanExpression;

public enum ReversalPatternType {

	OPEN_CLOSE("OpenClose", OpenCloseReversalPattern::upturn, OpenCloseReversalPattern::downturn),
	CLOSE_PRICE("ClosePrice", ClosePriceReversalPattern::upturn, ClosePriceReversalPattern::downturn),
	HOOK("Hook", HookReversalPattern::upturn, HookReversalPattern::downturn),
	KEY("Key", KeyReversalPattern::upturn, KeyReversalPattern::downturn);

	private ReversalPatternType(String keyword, DoubleFunction<BooleanExpression> upturn,
			DoubleFunction<BooleanExpression> downturn) {
		this.keyword = keyword;
		this.upturn = upturn;
		this.downturn = downturn;
	}

	private String keyword;
	private DoubleFunction<BooleanExpression> upturn;
	private DoubleFunction<BooleanExpression> downturn;

	public String keyword() {
		return keyword;
	}

	public BooleanExpression upturn(double nearPercent) {
		return upturn.apply(nearPercent);
	}
	
	public BooleanExpression downturn(double nearPercent) {
		return downturn.apply(nearPercent);
	}
	
}
