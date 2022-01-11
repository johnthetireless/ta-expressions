package ta.expressions.strategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import ta.expressions.core.Aggregate;

/**
 * Simple class to display start/end date and prices for a list of aggregates.
 * <p>
 * This may be extended.  
 * In particular, there should be a reference to the "aggregation period"
 * Used to create the data set: "daily", "10 minute", "1 minute", etc.
 *
 */
public class AnalysisDataset {
	
	private final String symbol;
	private final List<Aggregate> aggregates;
	
	public AnalysisDataset(String symbol, List<Aggregate> aggregates) {
		this.symbol = symbol;
		this.aggregates = aggregates;
	}

	public String symbol() {
		return symbol;
	}

	public List<Aggregate> aggregates() {
		return aggregates;
	}
	
	public Aggregate first() {
		return aggregates.isEmpty() ? null : aggregates.get(0);
	}

	public Aggregate last() {
		return aggregates.isEmpty() ? null : aggregates.get(size() - 1);
	}
	
	public LocalDate firstDate() {
		if ( aggregates.isEmpty() ) {
			return null;
		}
		return LocalDate.ofInstant(Instant.ofEpochSecond(first().timestamp()), ZoneId.systemDefault());
	}
	
	public LocalDate lastDate() {
		if ( aggregates.isEmpty() ) {
			return null;
		}
		return LocalDate.ofInstant(Instant.ofEpochSecond(last().timestamp()), ZoneId.systemDefault());
	}

	public BigDecimal firstClose() {
		if ( aggregates.isEmpty() ) {
			return null;
		}
		return first().close();
	}

	public BigDecimal lastClose() {
		if ( aggregates.isEmpty() ) {
			return null;
		}
		return last().close();
	}
	
	public long lastTimestamp() {
		if ( aggregates.isEmpty() ) {
			return -1;
		}
		return last().timestamp();
	}
	
	public BigDecimal priceChange() {
		if ( aggregates.isEmpty() ) {
			return null;
		}
		return lastClose().subtract(firstClose(), MathContext.DECIMAL64);
	}
	
	public BigDecimal changePercent() {
		if ( aggregates.isEmpty() ) {
			return null;
		}
		return priceChange().divide(firstClose(), MathContext.DECIMAL64).multiply(BigDecimal.valueOf(100));
	}
	
	

	public int size() {
		return aggregates.size();
	}

	@Override
	public String toString() {
		return "AnalysisDataset [symbol=" + symbol 
				+ ", from: " + firstDate() + " to: " + lastDate()
				+ ", size=" + size() 
				+ ", firstPrice=" + firstClose() 
				+ ", lastPrice=" + lastClose() 
				+ ", priceChange=" + priceChange() 
				+ ", changePercent=" + changePercent() 
				+ "]";
	}

	
}
