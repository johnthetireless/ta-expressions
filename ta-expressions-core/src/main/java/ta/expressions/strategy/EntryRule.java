package ta.expressions.strategy;

import java.util.Optional;

public abstract class EntryRule {
	
	//called for every input
	public void execute(Position position) {
		Optional<Entry> existingEntry = position.currentEntry();
		if ( existingEntry.isPresent() ) {
			Entry entry = existingEntry.get();
			if ( entry.isActive() ) {
				//do nothing; order already placed
			}
			else {
				entry.update(position.currentInput());
				executeOrder(position, entry);
			}
			
		}
		else {
			position.enter();
		}
	}
	
	protected abstract void executeOrder(Position position, Entry entry);
}
