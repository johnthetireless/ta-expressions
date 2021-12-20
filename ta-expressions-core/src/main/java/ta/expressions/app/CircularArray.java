package ta.expressions.app;

import java.util.Arrays;

/**
 * A circular array can hold up to N elements.  N is known as the capacity of the array.
 * All elements are null initially.
 * 
 * A circular array has no bounds.  
 * The get() and set() methods apply a modulo operation
 * <p>
 * index % capacity
 * <p>
 * This ensures all get/set operations with index >= 0 are successful.
 * <p>
 * Objects of this class can provide the basis for a FIFO (first in, first out) container.
 * The client that implements such a FIFO is responsible for making sure elements are accessed in a FIFO way.
 *
 * @param <T> the type of element held in the array
 */
public class CircularArray<T> {

	private final T[] elements;
	
	@SuppressWarnings("unchecked")
	public CircularArray(int capacity) {
		this((T[]) new Object[capacity]);
	}
	
	private CircularArray(T[] elements) {
		this.elements = elements;
	}
	
	public int length() {
		return elements.length;
	}

	public T get(int index) {
		return elements[index % length()];
	}
	
	public void set(int index, T element) {
		elements[index % length()] = element;
	}

	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
		
}
