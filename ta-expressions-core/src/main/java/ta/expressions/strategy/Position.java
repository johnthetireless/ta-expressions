package ta.expressions.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ta.expressions.core.Aggregate;

public class Position {
	
	private Aggregate currentInput;
	private Entry currentEntry;
	private List<Exit> exits = new ArrayList<>();
	
	public void acceptInput(Aggregate input) {
		currentInput = input;
		if ( currentEntry != null ) {
			currentEntry.update(input);
		}
	}
	
	void enter() {
		if ( currentEntry == null ) {
			currentEntry = new Entry(currentTimestamp());			
		}
	}
	
	void exit() {
		if ( currentEntry != null ) {
			Exit exit = new Exit(currentEntry, currentTimestamp());
			exits.add(exit);
			currentEntry = null;			
		}
	}
	
	public Optional<Entry> currentEntry()  {
		return Optional.ofNullable(currentEntry);
	}
	
	public Aggregate currentInput() {
		return currentInput;
	}
	
	public BigDecimal currentOpen() {
		return currentInput().open();
	}
	
	public BigDecimal currentHigh() {
		return currentInput().high();
	}
	
	public BigDecimal currentLow() {
		return currentInput().low();
	}
	
	public BigDecimal currentClose() {
		return currentInput().close();
	}
	
	public long currentTimestamp() {
		return currentInput().timestamp();
	}

}
