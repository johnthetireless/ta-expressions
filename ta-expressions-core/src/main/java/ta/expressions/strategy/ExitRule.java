package ta.expressions.strategy;

import java.util.Optional;

public class ExitRule {

	public void execute(Position position) {
		Optional<Entry> existingEntry = position.currentEntry();
		if ( existingEntry.isPresent() ) {
			//create order
			//position.close(order)
		}
		else {
		}
	}
}
