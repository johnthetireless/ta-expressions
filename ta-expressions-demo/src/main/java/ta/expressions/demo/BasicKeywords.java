package ta.expressions.demo;

import ta.expressions.common.change.Gain;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.RSI;
import ta.expressions.indicators.variables.ClosePrice;

public class BasicKeywords {

	public static void main(String[] args) {

		System.out.println("Standard arithmetic operators...");
		System.out.println("    +, -, *, /");
		System.out.println();
		
		System.out.println("Math functions...");
		for ( String s : NumericExpression.ARITHMETIC_KEYWORDS ) {
			System.out.println(s);
		}
		System.out.println();
		
		System.out.println("Standard relational operators...");
		System.out.println("   >, >=, <, <=, ==, !=");
		System.out.println();

		System.out.println("Logical operators...");
		for ( String s : BooleanExpression.KEYWORDS ) {
			System.out.println(s);
		}
		System.out.println();
		
		System.out.println("Previous value...useful in many situations");
		System.out.println(ClosePrice.INSTANCE.previous(5));
		System.out.println();
		
		System.out.println("IsNull... used internally to define calculations");
		System.out.println(new RSI(14).isNull());
		System.out.println();
		
		System.out.println("Ternary operator... used internally to define calculations");
		System.out.println(new Gain(ClosePrice.INSTANCE, 1).equation());
		System.out.println();
		
	}

}
