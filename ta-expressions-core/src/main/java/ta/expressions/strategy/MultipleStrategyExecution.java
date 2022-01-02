package ta.expressions.strategy;

import java.math.BigDecimal;
import java.math.MathContext;
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
	private final SymbolBookkeeper symbolBookkeeper;
	
	public MultipleStrategyExecution(String symbol, List<Strategy> strategies) {
		this.symbol = symbol;
		this.symbolBookkeeper = new SymbolBookkeeper(symbol);
		for ( Strategy s : strategies ) {
			StrategyExecution e = new StrategyExecution(s, symbol);
			executions.add(e);
			symbolBookkeeper.add(e.book());
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
	
	public BigDecimal totalPriceChange() {
		BigDecimal total = BigDecimal.ZERO;
		for ( int i = 0; i < executions.size(); i++ ) {
			total = total.add(executions.get(i).totalPriceChange(), MathContext.DECIMAL64);
		}
		return total;
	}
	
	public BigDecimal closedPositionCount() {
		BigDecimal count = BigDecimal.ZERO;
		for ( StrategyExecution e : executions ) {
			count = count.add(e.book().count(), MathContext.DECIMAL64);
		}
		return count;
	}
	
	public List<Position> closedPositions() {
		List<Position> list = new ArrayList<>();
		for ( StrategyExecution e : executions ) {
			list.addAll(e.book().positions());
		}
		return list;
	}
	
	public SymbolBookkeeper bookkeeper() {
		return symbolBookkeeper;
	}
}
