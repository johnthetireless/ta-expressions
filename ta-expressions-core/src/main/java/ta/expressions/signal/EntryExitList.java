package ta.expressions.signal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntryExitList {
	
	private final List<EntryExit> items;
		
	public EntryExitList(List<EntryExit> items) {
		this.items = items;
	}

	public BigDecimal totalPriceChange() {
		return total(items, EntryExit::priceChange);
	}
	
	public BigDecimal averagePriceChange() {
		return average(totalPriceChange(), numberOfItems());
	}
	
	public BigDecimal averageGain() {
		return average(totalGain(), numberOfGains());
	}
	
	public BigDecimal averageLoss() {
		return average(totalLoss(), numberOfLosses());
	}
	
	private BigDecimal average(BigDecimal value, long count) {
		if ( count == 0 ) {
			return BigDecimal.ZERO;
		}
		return value.divide(BigDecimal.valueOf(count), MathContext.DECIMAL64);
	}
	
	public Duration totalDuration() {
		Duration total = Duration.ZERO;
		for ( EntryExit e : items ) {
			total = total.plus(e.duration());
		}
		return total;
	}
	
	public long totalDays() {
		return totalDuration().toDays();
	}
	
	public long numberOfLosses() {
		return items.stream().filter(e -> e.priceChange().signum() > 0).count();
	}
	
	public long numberOfGains() {
		return items.stream().filter(e -> e.priceChange().signum() < 0).count();
	}
	
	public long numberOfItems() {
		return items.size();
	}
	
	public List<EntryExit> gainingItems() {
		return items.stream().filter(EntryExit::isGain).collect(Collectors.toList());
	}
	
	public List<EntryExit> losingItems() {
		return items.stream().filter(EntryExit::isLoss).collect(Collectors.toList());
	}
	
	public BigDecimal totalGain() {
		return total(gainingItems(), EntryExit::priceChange);
	}
	
	public BigDecimal totalLoss() {
		return total(losingItems(), EntryExit::priceChange);
	}
	
	public List<EntryExit> items() {
		return items;
	}

	private BigDecimal total(List<EntryExit> list, Function<EntryExit, BigDecimal> function) {
		BigDecimal total = BigDecimal.ZERO;
		for ( EntryExit e : list ) {
			total = total.add(function.apply(e), MathContext.DECIMAL64);
		}
		return total;

	}
}
