package ta.expressions.strategy;

import java.util.function.Function;

/**
 * A few fields that can be used to create a sort of custom report.
 * This idea will either be generalized or dropped.
 * My best way to analyze to results of strategy execution is probably a a spreadsheet.
 * Simple CSV output with 5-6 values and the user can calculate all the gross/net/etc.
 * Its amazing how many ways people will add, subtract, multiply and divide values involving money.
 *
 */
public enum PositionField implements Function<Position, String> {
	
	ENTRY_DATE("Entry Date", Position::entryDate),
	EXIT_DATE("Exit Date", Position::exitDate),
	DURATION("Entry Date", Position::duration),
	ENTRY_PRICE("Entry Price", Position::entryPrice),
	EXIT_PRICE("Exit Price", Position::exitPrice),
	PRICE_CHANGE("Price Change", Position::priceChange),
	GAIN("Gain", Position::gain),
	LOSS("Loss", Position::loss);

	@Override
	public String apply(Position p) {
		return label + "=" + function.apply(p);
	}

	private PositionField(String label, Function<Position, Object> function) {
		this.label = label;
		this.function = function;
	}
	private String label;
	private Function<Position, Object> function;
	

}
