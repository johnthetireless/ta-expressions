package ta.expressions.strategy;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.core.BooleanExpression;
import ta.expressions.signal.EntryExitFilter;
import ta.expressions.signal.Signal;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.signal.SignalPrinter;

public class StrategyEvaluation {
	private final String symbol;
	
	private final SignalPrinter p;
	private final EntryExitFilter filter;
	private final SignalGenerator sg;

	public StrategyEvaluation(String symbol, BooleanExpression entry, BooleanExpression exit) {
		super();
		this.symbol = symbol;

		p = new SignalPrinter();		
		filter = new EntryExitFilter(entry, exit, p);
		sg = new SignalGenerator(Set.of(entry, exit), filter);

	}
	
	
	public void run() {
		List<Aggregate> aggs = CsvReader.readFile(symbol + ".csv");
		for ( int i = 0; i < aggs.size(); i++ ) {
			Aggregate a = aggs.get(i);
			sg.accept(a);
		}		
		
		int numEntries = filter.entries().size();
		int numExits = filter.exits().size();
		
		System.out.println("Total number of signals: " + p.count());		
		System.out.println("Entries " + numEntries);		
		System.out.println("Exits " + numExits);		
		System.out.println("Total entry price " + filter.totalEntryPrice());		
		System.out.println("Total exit price " + filter.totalExitPrice());	
		System.out.println();
		
		if ( numEntries > numExits ) {
			Signal last = filter.entries().get(numEntries - 1); 
			System.out.println("Open entry at " + last.input().close());		
		}
		System.out.println();
		
		BigDecimal totalGainLoss = BigDecimal.ZERO;
		for ( int i = 0; i < numExits; i++ ) {
			long entryTime = filter.entries().get(i).input().timestamp();
			long exitTime = filter.exits().get(i).input().timestamp();
			LocalDate entryDate = LocalDate.ofInstant(Instant.ofEpochSecond(entryTime), ZoneId.systemDefault());
			LocalDate exitDate = LocalDate.ofInstant(Instant.ofEpochSecond(exitTime), ZoneId.systemDefault());
			BigDecimal entryPrice = filter.entries().get(i).input().close();
			BigDecimal exitPrice = filter.exits().get(i).input().close();
			BigDecimal gainLoss = exitPrice.subtract(entryPrice);
			totalGainLoss = totalGainLoss.add(gainLoss);
			
			System.out.println("Enter on " + entryDate + " at " + entryPrice
							+ " exit on " + exitDate + " at " + exitPrice 
							+ " gain/loss " + gainLoss);
						
		}
		System.out.println();
		System.out.println("Total gain/loss " + totalGainLoss);

	}
	
	public List<EntryExit> entryExits() {
		List<EntryExit> list = new ArrayList<>();
		for ( int i = 0; i < filter.exits().size(); i++ ) {
			Signal en = filter.entries().get(i);
			Signal ex = filter.exits().get(i);
			list.add(new EntryExit(en, ex));
		}
		return list;
	}
	
	public BigDecimal totalGainLoss() {
		BigDecimal total = BigDecimal.ZERO;
		for ( EntryExit e : entryExits() ) {
			total = total.add(e.gainLoss());
		}
		return total;
	}
	

	public static class EntryExit {
		private final Signal entrySignal;
		private final Signal exitSignal;
		
		public EntryExit(Signal entrySignal, Signal exitSignal) {
			this.entrySignal = entrySignal;
			this.exitSignal = exitSignal;
		}
		
		public LocalDate entryDate() {
			return localDate(entrySignal.timestamp());
		}
		
		public LocalDate exitDate() {
			return localDate(exitSignal.timestamp());
		}
		
		public Duration duration() {
			Instant start = Instant.ofEpochSecond(entrySignal.timestamp());
			Instant end = Instant.ofEpochSecond(exitSignal.timestamp());
			return Duration.between(start, end);
		}
		
		public long days() {
			return duration().toDays();
		}
		
		public BigDecimal entryPrice() {
			return entrySignal.input().close();
		}
		
		public BigDecimal exitPrice() {
			return exitSignal.input().close();
		}
		
		public BigDecimal gainLoss() {
			return exitPrice().subtract(entryPrice());
		}
		
		public boolean isGain() {
			return entryPrice().compareTo(exitPrice()) < 0;
		}
		
		public boolean isLoss() {
			return entryPrice().compareTo(exitPrice()) > 0;
		}
		
		public boolean isBreakeven() {
			return entryPrice().compareTo(exitPrice()) == 0;
		}
		
		private LocalDate localDate(long timestamp) {
			return LocalDate.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
		}

		@Override
		public String toString() {
			return "EntryExit [entryDate()=" + entryDate() 
								+ ", entryPrice()=" + entryPrice() 
								+ ", exitDate()=" + exitDate() 
								+ ", exitPrice()=" + exitPrice() 
								+ ", gainLoss()=" + gainLoss()
								+ ", days()=" + days()
								+ "]";
		}
		
		
	}
}
