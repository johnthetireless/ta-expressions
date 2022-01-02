package ta.expressions.strategy;

import java.util.Set;

import ta.expressions.core.BooleanExpression;
import ta.expressions.signal.Signal;

/**
 * This is a first-cut design to implement simple buy-sell decisions.
 * This will be extended.
 *
 */
public abstract class Strategy {

	public abstract String name();
	public abstract BooleanExpression entry();
	public abstract BooleanExpression exit();
	
	public Position actOn(Signal signal, Position position) {
		if ( signal.causedBy(entry()) ) {
			Position newPosition = position.open(signal.input().close(), signal.timestamp());
			return newPosition;
		}
		if ( signal.causedBy(exit()) ) {
			Position newPosition = position.close(signal.input().close(), signal.timestamp());
			return newPosition;
		}
		return position;
	}

	public Set<BooleanExpression> expressions() {
		return Set.of(entry(), exit());
	}
}
