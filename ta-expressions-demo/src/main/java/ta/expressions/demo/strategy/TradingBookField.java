package ta.expressions.demo.strategy;

import java.util.function.Function;

import ta.expressions.strategy.TradingBook;

public class TradingBookField extends ComparableField<TradingBook> {

	public static final TradingBookField SYMBOL = new TradingBookField("Symbol", TradingBook::symbol);
	public static final TradingBookField TRADER = new TradingBookField("Trader", TradingBook::traderName);
	public static final TradingBookField TOTAL_PRICE_CHANGE = new TradingBookField("Total Price Change", TradingBook::totalPriceChange);
	public static final TradingBookField TOTAL_GAIN = new TradingBookField("Total Gain", TradingBook::totalGain);
	public static final TradingBookField TOTAL_LOSS = new TradingBookField("Total Loss", TradingBook::totalLoss);
	public static final TradingBookField NUMBER_OF_POSITIONS = new TradingBookField("# Positions", TradingBook::count);
	public static final TradingBookField NUMBER_OF_GAINS = new TradingBookField("# Gains", TradingBook::gainCount);
	public static final TradingBookField NUMBER_OF_LOSSES = new TradingBookField("# Losses", TradingBook::lossCount);
	
	
	public TradingBookField(String label, Function<TradingBook, ? extends Comparable<?>> function) { 
		super(label, function);
	}

}
