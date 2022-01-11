package ta.expressions.strategy;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Consumer;

import ta.expressions.core.BooleanExpression;
import ta.expressions.signal.Signal;

/**
 * Executes a single strategy for a single security.
 * This is an initial design; it will likely change.
 *
 */
public class StrategyExecution implements Consumer<Signal> {
	
	private final Strategy strategy;
	private final String symbol;
	
	private Position currentPosition;
	
	private final TradingBook book;

	public StrategyExecution(Strategy strategy, String symbol) {
		this.strategy = strategy;
		this.symbol = symbol;
		this.currentPosition = new Position(symbol, strategy);
		this.book = new TradingBook(symbol, strategy);
	}

	@Override
	public void accept(Signal signal) {
		Position newPosition = strategy.actOn(signal, currentPosition);
		if ( newPosition.isClosed() ) {
			book.add(newPosition);
			currentPosition = new Position(symbol, strategy);
		}
		else {
			currentPosition = newPosition;
		}
	}

	public Strategy strategy() {
		return strategy;
	}

	public String symbol() {
		return symbol;
	}

	public Position currentPosition() {
		return currentPosition;
	}
	
	public TradingBook book() {
		return book;
	}

	public void closeCurrentPosition(BigDecimal price, long timestamp) {
		Position newPosition = currentPosition.close(price, timestamp);
		if ( newPosition.isClosed() ) {
			book.add(newPosition);
		}
		currentPosition = new Position(symbol, strategy);
	}
	
	public Set<BooleanExpression> expressions() {
		return strategy.expressions();
	}

	public BigDecimal totalPriceChange() {
		return book.totalPriceChange();
	}
	
}
