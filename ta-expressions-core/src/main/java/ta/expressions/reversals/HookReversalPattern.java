package ta.expressions.reversals;

import ta.expressions.core.BooleanExpression;

public class HookReversalPattern {

	static ReversalPattern upturn(double nearPercent) {
		BooleanExpression openNearHigh = Reversals.openNearHigh(nearPercent);
		BooleanExpression closeNearLow = Reversals.closeNearLow(nearPercent);
		BooleanExpression insideDay = Reversals.insideDay();
		return new ReversalPattern(
					ReversalPatternType.HOOK,
					ReversalTurnType.UPTURN, nearPercent,
					openNearHigh.and(closeNearLow).and(insideDay)
		);
	}

	static ReversalPattern downturn(double nearPercent) {
		BooleanExpression openNearLow = Reversals.openNearLow(nearPercent);
		BooleanExpression closeNearHigh = Reversals.closeNearHigh(nearPercent);
		BooleanExpression insideDay = Reversals.insideDay();
		return new ReversalPattern(
				ReversalPatternType.OPEN_CLOSE,
				ReversalTurnType.DOWNTURN, nearPercent,
				openNearLow.and(closeNearHigh).and(insideDay)
		);
	}
	
}
