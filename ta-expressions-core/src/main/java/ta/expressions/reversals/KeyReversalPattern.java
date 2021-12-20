package ta.expressions.reversals;

import ta.expressions.core.BooleanExpression;

public class KeyReversalPattern {

	static ReversalPattern upturn(double nearPercent) {
		BooleanExpression openAboveClose = Reversals.openAboveClose();
		BooleanExpression closeBelowLow = Reversals.closeBelowLow();
		return new ReversalPattern(
					ReversalPatternType.OPEN_CLOSE,
					ReversalTurnType.UPTURN, nearPercent,
					openAboveClose.and(closeBelowLow)
		);
	}

	static ReversalPattern downturn(double nearPercent) {
		BooleanExpression openBelowClose = Reversals.openBelowClose();
		BooleanExpression closeAboveHigh = Reversals.closeAboveHigh();
		return new ReversalPattern(
					ReversalPatternType.OPEN_CLOSE,
					ReversalTurnType.DOWNTURN, nearPercent,
					openBelowClose.and(closeAboveHigh)
		);
	}
	

}
