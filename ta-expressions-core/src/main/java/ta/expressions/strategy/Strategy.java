package ta.expressions.strategy;

import java.util.Set;

import ta.expressions.core.BooleanExpression;
import ta.expressions.signal.Signal;

/**
 * This is a first-cut design to implement simple buy-sell decisions.
 * This will be changed/extended....
 * <p>
 * A strategy will be used for a set of securities.
 * The strategy will also target a specific "aggregation period": daily, 1 minute, etc.
 * It is doubtful any strategy will have the same performance using daily vs 15 minute data, for example.
 * <p>
 * At some point in the near future  we need the concept of "strategy X for security Y with aggregation period P"
 * <ul>
 * <li> "MACD crossover - AAPL Daily"
 * <li> "Channel Breakout XYZ - QQQ 1 minute"
 * </ul>
 * We aren't quite there yet.  
 * This class will act as the "model"; we will need "instance" classes to associate the model with the data as described above.
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
		else if ( signal.causedBy(exit()) ) {
			Position newPosition = position.close(signal.input().close(), signal.timestamp());
			return newPosition;
		}
		return position;
	}

	public Set<BooleanExpression> expressions() {
		return Set.of(entry(), exit());
	}
	@Override
	public String toString() {
		return "Strategy [name()=" + name() + "]";
	}
	
	
}
