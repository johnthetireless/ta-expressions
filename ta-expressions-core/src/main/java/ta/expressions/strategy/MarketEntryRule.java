package ta.expressions.strategy;

public class MarketEntryRule extends EntryRule {
	
	public enum PriceType { CURRENT_CLOSE, NEXT_OPEN }

	private PriceType priceType;
	
	@Override
	protected void executeOrder(Position position, Entry entry) {
		if ( priceType.equals(PriceType.CURRENT_CLOSE) ) {
			entry.activate(position.currentClose(), position.currentTimestamp());
		}
		else {
			if ( position.currentTimestamp() > entry.timestamp() ) {
				entry.activate(position.currentOpen(), position.currentTimestamp());				
			}
		}
	};
	
	

}
