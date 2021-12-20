package ta.expressions.signal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ta.expressions.core.BooleanExpression;

public class EntryExitFilter implements Consumer<Signal> {

	private final BooleanExpression entry;
	private final BooleanExpression exit;
	private final Consumer<Signal> consumer;
	
	private BooleanExpression last;
	
	private final List<Signal> entries = new ArrayList<>();
	private final List<Signal> exits = new ArrayList<>();

	public EntryExitFilter(BooleanExpression entry, BooleanExpression exit, Consumer<Signal> consumer) {
		this.entry = entry;
		this.exit = exit;
		this.consumer = consumer;
	}

	@Override
	public void accept(Signal signal) {
		
		if ( signal.causedBy(entry) ) {
			if ( last == null || last.equals(exit) ) {
				consumer.accept(signal);
				entries.add(signal);
				last = entry;				
			}
		}

		if ( signal.causedBy(exit) ) {
			if ( last != null && last.equals(entry) ) {
				consumer.accept(signal);
				exits.add(signal);
				last = exit;				
			}
		}
	}

	public List<Signal> entries() {
		return entries;
	}

	public List<Signal> exits() {
		return exits;
	}
	
	public BigDecimal totalEntryPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for ( int i = 0; i < entries.size(); i++ ) {
			total = total.add(entries.get(i).input().close());
		}
		return total;
	}
	
	public BigDecimal totalExitPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for ( int i = 0; i < exits.size(); i++ ) {
			total = total.add(exits.get(i).input().close());
		}
		return total;
	}
	
}
