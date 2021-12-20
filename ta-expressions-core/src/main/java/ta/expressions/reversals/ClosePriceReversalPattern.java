package ta.expressions.reversals;

import ta.expressions.core.BooleanExpression;

public class ClosePriceReversalPattern {

	static ReversalPattern upturn(double nearPercent) {
		BooleanExpression openNearHigh = Reversals.openNearHigh(nearPercent);
		BooleanExpression closeNearLow = Reversals.closeNearLow(nearPercent);
		BooleanExpression lowerClose = Reversals.lowerClose();
		return new ReversalPattern(
					ReversalPatternType.CLOSE_PRICE,
					ReversalTurnType.UPTURN, nearPercent,
					openNearHigh.and(closeNearLow).and(lowerClose)
		);
	}

	static ReversalPattern downturn(double nearPercent) {
		BooleanExpression openNearLow = Reversals.openNearLow(nearPercent);
		BooleanExpression closeNearHigh = Reversals.closeNearHigh(nearPercent);
		BooleanExpression higherClose = Reversals.higherClose();
		return new ReversalPattern(
				ReversalPatternType.CLOSE_PRICE,
				ReversalTurnType.DOWNTURN, nearPercent,
				openNearLow.and(closeNearHigh).and(higherClose)
		);
	}
	

}
