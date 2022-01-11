package ta.expressions.strategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A simple container for books.  This is a thrown together design.  It will change.
 *
 */
public class Bookkeeper {

	private final List<TradingBook> books = new ArrayList<>();
	
	public void add(TradingBook book) {
		books.add(book);
	}
	
	public void addAll(List<TradingBook> books) {
		books.addAll(books);
	}
	
	public List<TradingBook> sort(Function<TradingBook, BigDecimal> function) {
		return sort(function, true);
	}
	
	public List<TradingBook> sort(Function<TradingBook, BigDecimal> function, boolean descending) {
		List<TradingBook> copy = new ArrayList<>(activeBooks());
		Comparator<TradingBook> comp = descending 
									? (a,b) -> function.apply(b).compareTo(function.apply(a))
									: (a,b) -> function.apply(a).compareTo(function.apply(b));
				
		Collections.sort(copy, comp);
		return copy;
	}
	
	public List<TradingBook> books() {
		return books;
	}
	
	public List<TradingBook> activeBooks() {
		return books.stream().filter(b -> b.count().signum() > 0).collect(Collectors.toList());
	}
	
	public int activeBookCount() {
		return activeBooks().size();
	}
	
	public BigDecimal total(Function<TradingBook, BigDecimal> function) {
		BigDecimal total = BigDecimal.ZERO;
		for ( TradingBook b : books ) {
			total = total.add(function.apply(b), MathContext.DECIMAL64);
		}
		return total;
	}

}
