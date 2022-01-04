package ta.expressions.strategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * A simple class for initial strategy testing.  This will be extended.
 *
 */
public class Position {
	
	private final String symbol;
	private final Strategy strategy;
	
	private BigDecimal entryPrice;
	private long entryTimestamp = -1;
	
	private BigDecimal exitPrice;
	private long exitTimestamp = -1;
	
	private BigDecimal priceChange;
	
	public Position(String symbol, Strategy strategy) {
		this.symbol = symbol;
		this.strategy = strategy;
	}

	Position(String symbol, Strategy strategy, BigDecimal entryPrice, long entryTimestamp) {
		this.symbol = symbol;
		this.strategy = strategy;
		this.entryPrice = entryPrice;
		this.entryTimestamp = entryTimestamp;
	}

	Position(String symbol, Strategy strategy, BigDecimal entryPrice, long entryTimestamp, BigDecimal exitPrice,
			long exitTimestamp) {
		this.symbol = symbol;
		this.strategy = strategy;
		this.entryPrice = entryPrice;
		this.entryTimestamp = entryTimestamp;
		this.exitPrice = exitPrice;
		this.exitTimestamp = exitTimestamp;
		this.priceChange = exitPrice.subtract(entryPrice, MathContext.DECIMAL64);
	}
	
	public String symbol() {
		return symbol;
	}

	public Strategy strategy() {
		return strategy;
	}

	public BigDecimal entryPrice() {
		return entryPrice;
	}

	public long entryTimestamp() {
		return entryTimestamp;
	}

	public BigDecimal exitPrice() {
		return exitPrice;
	}

	public long exitTimestamp() {
		return exitTimestamp;
	}

	public boolean isNew() {
		return entryTimestamp < 0;
	}
	
	public boolean isOpen() {
		return entryTimestamp > 0 && exitTimestamp < 0;
	}

	public boolean isClosed() {
		return exitTimestamp > 0;
	}

	public Position open(BigDecimal entryPrice, long entryTimestamp) {
		if ( isNew() ) {
			return new Position(symbol, strategy, entryPrice, entryTimestamp);
		}
		return this;
	}
	
	public Position close(BigDecimal esitPrice, long exitTimestamp) {
		if ( isOpen() ) {
			return new Position(symbol, strategy, entryPrice, entryTimestamp, esitPrice, exitTimestamp);
		}
		return this;
	}
	
	public BigDecimal priceChange() {
//		return exitPrice.subtract(entryPrice, MathContext.DECIMAL64);
		return priceChange;
	}
	
	public BigDecimal changeRatio() {
		return priceChange().divide(entryPrice, MathContext.DECIMAL64);
	}
	
	
	public BigDecimal gain() {
		return priceChange().signum() > 0 ? priceChange() : BigDecimal.ZERO;
	}
	
	public BigDecimal loss() {
		return priceChange().signum() < 0 ? priceChange().abs() : BigDecimal.ZERO;
	}
	
	public Duration duration() {
		Instant start = Instant.ofEpochSecond(entryTimestamp);
		Instant end = Instant.ofEpochSecond(exitTimestamp);
		return Duration.between(start, end);
	}
	
	public long days() {
		return duration().toDays();
	}

	public LocalDate entryDate() {
		return localDate(entryTimestamp);
	}
	
	public LocalDate exitDate() {
		return localDate(exitTimestamp);
	}
	
	private LocalDate localDate(long timestamp) {
		Instant instant = Instant.ofEpochSecond(timestamp);
		return LocalDate.ofInstant(instant, ZoneId.systemDefault());
	}
	
	@Override
	public String toString() {
		return "Position [symbol=" + symbol + ", strategy=" + strategy.name() 
				+ " priceChange=" + priceChange()
				+ ", entryPrice=" + entryPrice + ", entryDate=" + entryDate() 
				+ ", exitPrice=" + exitPrice + ", exitDate=" + exitDate()
				+ ", days=" + days()
				+ "]";
	}
	
}
