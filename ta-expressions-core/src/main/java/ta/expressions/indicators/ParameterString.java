package ta.expressions.indicators;

public class ParameterString {

	private final String[] parts;

	public ParameterString() {
		this("");
	}
	
	public ParameterString(String input) {
		this.parts = input.split(",");
	}
	
	public int intValue(int index) {
		return Integer.valueOf(parts[index]);
	}
	
	public double doubleValue(int index) {
		return Double.valueOf(parts[index]);
	}
	
	public int length() {
		return parts.length;
	}

}
