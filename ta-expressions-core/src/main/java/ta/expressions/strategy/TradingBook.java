package ta.expressions.strategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A list of closed positions.
 * This supports initial strategy testing.  It will likely be extended.
 *
 */
public class TradingBook {

	private final String symbol;
	private final String traderName;
	private final List<Position> positions = new ArrayList<>();
	
	public TradingBook(String symbol, String traderName) {
		this.symbol = symbol;
		this.traderName = traderName;
	}

	public String symbol() {
		return symbol;
	}
	
	public String traderName() {
		return traderName;
	}
	
	public void add(Position p) {
		positions.add(p);
	}

	public void addAll(List<Position> ps) {
		positions.addAll(ps);
	}
	
	public List<Position> positions() {
		return positions;
	}
	
	public BigDecimal count() {
		return BigDecimal.valueOf(positions.size());
	}
	
	public BigDecimal totalPriceChange() {
		return total(Position::priceChange);
	}
	
	public BigDecimal totalGain() {
		return total(Position::gain);
	}
	
	public BigDecimal totalLoss() {
		return total(Position::loss);
	}
	
	public BigDecimal total(Function<Position, BigDecimal> function) {
		BigDecimal total = BigDecimal.ZERO;
		for ( Position p : positions ) {
			total = total.add(function.apply(p), MathContext.DECIMAL64);
		}
		return total;		
	}
	
	public BigDecimal gainCount() {
		return count(p -> p.gain().signum() > 0);
	}
	
	public BigDecimal lossCount() {
		return count(p -> p.loss().signum() > 0);
	}
	
	public BigDecimal gainPercent() {
		return gainCount().divide(count(), MathContext.DECIMAL64);
	}
	
	public BigDecimal lossPercent() {
		return lossCount().divide(count(), MathContext.DECIMAL64);
	}
	
	public BigDecimal count(Predicate<Position> predicate) {
		return BigDecimal.valueOf(positions.stream().filter(predicate).count());
	}
	
	public List<Position> sort(Function<Position, BigDecimal> function) {
		List<Position> copy = List.copyOf(positions);
		Comparator<Position> comp = (a,b) -> function.apply(a).compareTo(function.apply(b));
		Collections.sort(copy, comp);
		return copy;
	}
	
	public Duration totalDuration() {
		Duration total = Duration.ZERO;
		for ( Position p : positions ) {
			total = total.plus(p.duration());
		}
		return total;
	}
	
	public long totalDays() {
		int total = 0;
		for ( Position p : positions ) {
			total += p.days();
		}
		return total;
	}

	@Override
	public String toString() {
		return "TradingBook [symbol=" + symbol + ", traderName=" + traderName + ", count()=" + count()
				+ ", totalPriceChange()=" + totalPriceChange() + "]";
	}
	
	
}
