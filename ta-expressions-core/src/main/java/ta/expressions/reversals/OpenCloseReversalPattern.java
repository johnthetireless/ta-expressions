package ta.expressions.reversals;

import ta.expressions.core.BooleanExpression;

class OpenCloseReversalPattern  {
	
	static ReversalPattern upturn(double nearPercent) {
		BooleanExpression openNearHigh = Reversals.openNearHigh(nearPercent);
		BooleanExpression closeNearLow = Reversals.closeNearLow(nearPercent);
		BooleanExpression higherClose = Reversals.higherClose();
		return new ReversalPattern(
					ReversalPatternType.OPEN_CLOSE,
					ReversalTurnType.UPTURN, nearPercent,
					openNearHigh.and(closeNearLow).and(higherClose)
		);
	}

	static ReversalPattern downturn(double nearPercent) {
		BooleanExpression openNearLow = Reversals.openNearLow(nearPercent);
		BooleanExpression closeNearHigh = Reversals.closeNearHigh(nearPercent);
		BooleanExpression lowerClose = Reversals.lowerClose();
		return new ReversalPattern(
				ReversalPatternType.OPEN_CLOSE,
				ReversalTurnType.DOWNTURN, nearPercent,
				openNearLow.and(closeNearHigh).and(lowerClose)
		);
	}
	
}
