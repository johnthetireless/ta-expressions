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

	T get(int index) {
		return elements[index % length()];
	}
	
	void set(int index, T element) {
		elements[index % length()] = element;
	}

	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
	
	public static void main(String[] args) {
		CircularArray<String> array = new CircularArray<>(5);
		array.set(0, "a");
		array.set(1, "b");
		array.set(2, "c");
		array.set(3, "d");
		array.set(4, "e");
		System.out.println(array);
		
		array.set(5, "f");
		System.out.println(array);
		
	
	}
	
	
}
