package ta.expressions.demo.strategy;

import java.util.function.Function;

public class ComparableField<T> {

	private final String label;
	private final Function<T, ? extends Comparable<?>> function;
	
	public ComparableField(String label, Function<T, ? extends Comparable<?>> function) {
		this.label = label;
		this.function = function;
	}

	public String label() {
		return label;
	}

	public Function<T, ? extends Comparable<?>> function() {
		return function;
	}
	
	public String value(T t) {
		return function.apply(t).toString();
	}
	
	
	
}
