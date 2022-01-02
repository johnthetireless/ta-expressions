package ta.expressions.demo;

public class Indicators {

	public static void main(String[] args) {
		
		System.out.println("Some of the pre-defined TA indicators...");
		System.out.println();
		for ( TechnicalAnalysisIndicator ind : TechnicalAnalysisIndicator.values() ) {
			
			String desc = ind.description();
			System.out.println(desc);
		}
		

	}

}
