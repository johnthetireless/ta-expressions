package ta.expressions.app;

import ta.expressions.common.change.Gain;
import ta.expressions.common.change.Loss;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class Terms {

	public static void main(String[] args) {

		int total = 0;
		
		System.out.println("Variables");
		for ( TechnicalAnalysisVariable v : TechnicalAnalysisVariable.values() ) {
			NumericExpression e = v.expression();
			System.out.println(e + " -------- " + e.getClass().getName());
			total++;
		}
		System.out.println();
		
		System.out.println("Terms");
		for ( TechnicalAnalysisTerm t : TechnicalAnalysisTerm.values() ) {
			AnalyticFunction e = t.expression();
			System.out.println(e.equation());
			total++;			
		}
		System.out.println();
		System.out.println();
		System.out.println(new Gain(ClosePrice.INSTANCE));
		System.out.println(new Loss(ClosePrice.INSTANCE));
		total += 2;
		
		
		System.out.println("Total: " + total);
	}

}
