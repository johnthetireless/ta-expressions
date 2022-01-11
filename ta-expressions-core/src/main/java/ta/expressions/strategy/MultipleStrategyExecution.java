package ta.expressions.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import ta.expressions.core.BooleanExpression;
import ta.expressions.signal.Signal;

/**
 * Executes a list of strategies for a single security.
 * This is an initial design; it will likely change.
 *
 */
public class MultipleStrategyExecution implements Consumer<Signal> {
	
	private final String symbol;
	private final List<StrategyExecution> executions = new ArrayList<>();
	private final SymbolBookkeeper bookkeeper;
	
	public MultipleStrategyExecution(String symbol, List<Strategy> strategies) {
		this.symbol = symbol;
		this.bookkeeper = new SymbolBookkeeper(symbol);
		for ( Strategy s : strategies ) {
			StrategyExecution e = new StrategyExecution(s, symbol);
			executions.add(e);
			bookkeeper.add(e.book());
		}
	}

	@Override
	public void accept(Signal signal) {
		executions.forEach(e -> e.accept(signal));
	}

	public void closeCurrentPositions(BigDecimal price, long timestamp) {
		executions.forEach(e -> e.closeCurrentPosition(price, timestamp));
	}

	public String symbol() {
		return symbol;
	}

	public List<StrategyExecution> executions() {
		return executions;
	}

	public Set<BooleanExpression> expressions() {
		Set<BooleanExpression> set = new HashSet<>();
		executions.forEach(e -> set.addAll(e.expressions()));
		return set;
	}
			
	public SymbolBookkeeper bookkeeper() {
		return bookkeeper;
	}
}
