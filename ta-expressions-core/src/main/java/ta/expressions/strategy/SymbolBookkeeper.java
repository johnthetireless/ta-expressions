package ta.expressions.strategy;

import java.util.List;
import java.util.stream.Collectors;

public class SymbolBookkeeper extends Bookkeeper {

	private final String symbol;
	
	public SymbolBookkeeper(String symbol) {
		this.symbol = symbol;
	}
	
	public String symbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return "SymbolBookkeeper [symbol=" + symbol + ", activeBooks=" + activeBookCount() + "]";
	}
	
	public List<TradingBook> booksForTrader(String traderName) {
		return books().stream().filter(b -> b.traderName().equals(traderName)).collect(Collectors.toList());
	}
	
	
}
