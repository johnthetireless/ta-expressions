package ta.expressions.app;

public class FIFOSequence<T> {

	private final CircularArray<T> array;
	private int firstIndex = 0;
	private int lastIndex = -1;
	
	public FIFOSequence(int capacity) {
		this.array = new CircularArray<>(capacity);
	}
	
	public void add(T element) {
		array.set(lastIndex + 1, element);
		lastIndex++;
		if ( lastIndex >= capacity() ) {
			firstIndex++;
		}
	}
	
	public T get(int index) {
		if ( index >= firstIndex() && index <= lastIndex() ) {
			return array.get(index);			
		}
		else {
			return null;
		}
	}
	
	public int capacity() {
		return array.length();
	}
	
	public int firstIndex() {
		return firstIndex;
	}
	
	public int lastIndex() {
		return lastIndex;
	}
	

}
