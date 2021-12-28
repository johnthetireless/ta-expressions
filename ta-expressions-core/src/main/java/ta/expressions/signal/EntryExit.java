package ta.expressions.signal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class EntryExit {
	
	private final Signal entrySignal;
	private final Signal exitSignal;
	
	public EntryExit(Signal entrySignal, Signal exitSignal) {
		this.entrySignal = entrySignal;
		this.exitSignal = exitSignal;
	}

	public BigDecimal priceChange() {
		return exitPrice().subtract(entryPrice(), MathContext.DECIMAL64);
	}
	
	public boolean isGain() {
		return priceChange().signum() > 0;
	}
	
	public boolean isLoss() {
		return priceChange().signum() < 0;
	}
	
	public BigDecimal entryPrice() {
		return entrySignal.input().close();
	}
	
	public BigDecimal exitPrice() {
		return exitSignal.input().close();
	}
	
	public long days() {
		return duration().toDays();
	}
	
	public Duration duration() {
		return Duration.between(entryInstant(), exitInstant());
	}
	
	public LocalDate entryDate() {
		return LocalDate.ofInstant(entryInstant(), ZoneId.systemDefault());
	}

	public LocalDate exitDate() {
		return LocalDate.ofInstant(exitInstant(), ZoneId.systemDefault());
	}

	public Instant entryInstant() {
		return Instant.ofEpochSecond(entryTimestamp());
	}
	
	public Instant exitInstant() {
		return Instant.ofEpochSecond(exitTimestamp());
	}

	public long entryTimestamp() {
		return entrySignal.timestamp();
	}
	
	public long exitTimestamp() {
		return exitSignal.timestamp();
	}
	
}
