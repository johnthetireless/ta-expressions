package ta.expressions.strategy;

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
	
	
}