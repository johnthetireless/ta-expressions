package ta.expressions.core;

import java.util.Objects;

/**
 * Abstract base class for all expressions.
 * <p>
 * <ul>
 * <li> Provides the representation of an expression 
 * <li> implements toString() to return the string representation; objects print themselves naturally
 * <li> implements equals() and hashCode() based on the string representation; objects may be used in maps and sets
 * </ul>
 * <p>
 * This class forces subclasses to create their string representation in the constructor.
 * It provides a set of (messy) static helper methods for this.  So far this has been fine.
 * <p>
 * NOTE: At some point the Expression interface may be transformed into a class.  
 * The getValue() and evaluate() methods are public, as required by Java 8 interfaces.
 * This is probably OK, but it exposes clients to an index into a data structure 
 * that is not guaranteed to (still) hold a value for that index.
 * This index is better hidden and used by protected methods.
 * AbstractExpression and Expression will be merged at that time.
 * The public API will be unaffected.
 *
 * @param <T> either BigDecimal or Boolean
 */
abstract class AbstractExpression<T> implements Expression<T> {

	private final String representation;
	
	public AbstractExpression(String representation) {
		this.representation = Objects.requireNonNull(representation);
	}

	@Override
	public String representation() {
		return representation;
	}

	@Override
	public String toString() {
		return representation();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((representation == null) ? 0 : representation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		AbstractExpression<T> other = (AbstractExpression<T>) obj;
		if (representation == null) {
			if (other.representation != null)
				return false;
		} else if (!representation.equals(other.representation))
			return false;
		return true;
	}

	protected static <T> String operationRepresentation(String symbol, Expression<T> e1, Expression<T> e2) {
		return "(" + e1.representation() + " " + symbol + " " + e2.representation() + ")";
	}

	protected static <T> String functionRepresentation(String symbol, Expression<T> e) {
		return symbol + "(" + e.representation() + ")";
	}

	protected static <T> String functionRepresentation(String symbol, Expression<T> e1, Expression<T> e2) {
		return symbol + "(" + e1.representation() + "," + e2.representation() + ")";
	}

	protected static <T> String functionRepresentation(String symbol, Expression<T> e1, Expression<T> e2, double k) {
		return symbol + "(" + e1.representation() + "," + e2.representation() + "," + k + ")";
	}

	protected static <T> String functionRepresentation(String symbol, Expression<T> e1, Expression<T> e2, int n) {
		return symbol + "(" + e1.representation() + "," + e2.representation() + "," + n + ")";
	}

	protected static <T> String functionRepresentation(String symbol, Expression<T> e, int n) {
		return symbol + "(" + e.representation() + "," + n + ")";
	}

	protected static <T> String functionRepresentation(String symbol, Expression<T> e, int n1, int n2) {
		return symbol + "(" + e.representation() + "," + n1 + "," + n2 + ")";
	}

	//TODO: this is way past silly; consolidate or do something else
	protected static <T> String functionRepresentation(String symbol, Expression<T> e, int n, double k) {
		return symbol + "(" + e.representation() + "," + n + "," + k + ")";
	}

	protected static <T> String functionRepresentation(String symbol, int n) {
		return symbol + "(" + n + ")";
	}

	protected static <T> String functionRepresentation(String symbol, double k) {
		return symbol + "(" + k + ")";
	}

	protected static <T> String functionRepresentation(String symbol, int n1, int n2) {
		return symbol + "(" + n1 + "," + n2 + ")";
	}

	protected static <T> String functionRepresentation(String symbol, int n1, int n2, int n3) {
		return symbol + "(" + n1 + "," + n2 + "," + n3 + ")";
	}

	protected static <T> String functionRepresentation(String symbol, int n, double k1, double k2) {
		return symbol + "(" + n + "," + k1 + "," + k2 + ")";
	}

	protected static <T> String functionRepresentation(String symbol, int n1, int n2, double k1) {
		return symbol + "(" + n1 + "," + n2 + "," + k1 + ")";
	}

	protected static <T> String functionRepresentation(String symbol, int n, Number k) {
		return symbol + "(" + n + "," + k + ")";
	}

	protected static <T> String functionRepresentation(String symbol, int n1, int n2, Number k) {
		return symbol + "(" + n1 + "," + n2 + "," + k + ")";
	}

}
