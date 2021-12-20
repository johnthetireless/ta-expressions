package ta.expressions.strategy;

import java.math.BigDecimal;

public class LimitEntryRule extends EntryRule {
	
	public enum Action { BUY, SELL };

	private Action action;
	private BigDecimal limitPrice;
	
	public LimitEntryRule() {
	}

	@Override
	protected void executeOrder(Position position, Entry entry) {
		
		if ( action.equals(Action.BUY) ) {
			BigDecimal trigger = entry.highestHigh().subtract(limitPrice);
			if ( position.currentHigh().compareTo(trigger) <= 0 ) {
				entry.activate(position.currentClose(), position.currentTimestamp());
			}
		}

		if ( action.equals(Action.SELL) ) {
			BigDecimal trigger = entry.lowestLow().add(limitPrice);
			if ( position.currentLow().compareTo(trigger) >= 0 ) {
				entry.activate(position.currentClose(), position.currentTimestamp());
			}
		}

	}

}
