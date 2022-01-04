package ta.expressions.demo;

import ta.expressions.indicators.TrendIntensityIndex;

public class Indicators {

	public static void main(String[] args) {
		
		System.out.println("Some of the pre-defined TA indicators...");
		System.out.println();
		for ( TechnicalAnalysisIndicator ind : TechnicalAnalysisIndicator.values() ) {
			
			String desc = ind.description();
			System.out.println(desc);
		}
		
		System.out.println(new TrendIntensityIndex(40).equation());

	}

}
