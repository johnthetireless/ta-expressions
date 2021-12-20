package ta.expressions.demo;

import ta.expressions.app.TechnicalAnalysisTerm;
import ta.expressions.app.TechnicalAnalysisVariable;
import ta.expressions.common.change.Change;
import ta.expressions.common.change.Gain;
import ta.expressions.common.change.Loss;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class Terms {

	public static void main(String[] args) {

		System.out.println("Variables...........");
		System.out.println();
		for ( TechnicalAnalysisVariable v : TechnicalAnalysisVariable.values() ) {
			NumericExpression e = v.expression();
			System.out.println(e);
		}
		System.out.println();
		
		System.out.println("Basic Terms.........");
		System.out.println();
		for ( TechnicalAnalysisTerm t : TechnicalAnalysisTerm.values() ) {
			AnalyticFunction e = t.expression();
			System.out.println(e.equation());
		}
		System.out.println();

		System.out.println("Gain/Loss/Change....");
		System.out.println();
		System.out.println(new Gain(ClosePrice.INSTANCE).equation());
		System.out.println(new Loss(ClosePrice.INSTANCE).equation());
		System.out.println(new Change(ClosePrice.INSTANCE).equation());
	}

}
