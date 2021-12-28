package ta.expressions.signal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ta.expressions.core.BooleanExpression;

public class SingleEntryExitEvaluation implements Consumer<Signal> {

	private final BooleanExpression entry;
	private final BooleanExpression exit;
	
	private BooleanExpression last;
	
	private final List<Signal> entries = new ArrayList<>();
	private final List<Signal> exits = new ArrayList<>();

	public SingleEntryExitEvaluation(BooleanExpression entry, BooleanExpression exit) {
		this.entry = entry;
		this.exit = exit;
	}
	
	public EntryExitList createReport() {
		List<EntryExit> items = new ArrayList<>();
		for ( int i = 0; i < exits.size(); i++ ) {
			Signal en = entries.get(i);
			Signal ex = exits.get(i);
			items.add(new EntryExit(en, ex));
		}
		EntryExitList list = new EntryExitList(items);
		return list;
	}
	
	@Override
	public void accept(Signal signal) {
		
		if ( signal.causedBy(entry) ) {
			if ( last == null || last.equals(exit) ) {
				entries.add(signal);
				last = entry;				
			}
		}

		if ( signal.causedBy(exit) ) {
			if ( last != null && last.equals(entry) ) {
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
	
	
}
