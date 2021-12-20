package ta.expressions.signal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import ta.expressions.core.BooleanExpression;

public class SignalPrinter implements Consumer<Signal> {

	private int count = 0;
	
	private final Map<BooleanExpression, Integer> causeCounts = new HashMap<>();

	public void accept(Signal s) {
		LocalDate date = LocalDate.ofInstant(Instant.ofEpochSecond(s.timestamp()), ZoneId.systemDefault());
		System.out.println("Signal received....");
		System.out.println("Index.............." + s.index());
		System.out.println("Date..............." + date);
		System.out.println("Cause.............." + s.cause());
		System.out.println("Price.............." + s.input().close());
		System.out.println("Values............." + s.values());
		System.out.println("...................");
		System.out.println();
		count++;
		incrementCount(s);
	}
	
	public int count() {
		return count;
	}
	
	public int countFor(BooleanExpression e) {
		Integer count = causeCounts.get(e);
		if ( count == null ) {
			count = 0;
		}
		return count;
	}
	
	private void incrementCount(Signal signal) {
		Integer count = countFor(signal.cause());
		causeCounts.put(signal.cause(), count + 1);
	}

	
	
}
