package ta.expressions.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ta.expressions.app.Calculation;
import ta.expressions.app.CalculationResult;
import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.adx.ADX;
import ta.expressions.indicators.adx.ADXR;
import ta.expressions.indicators.adx.MinusDI;
import ta.expressions.indicators.adx.PlusDI;

public class CommandLineCalculator {

	public static void main(String[] args) {
		
		if ( args.length < 2 ) {
			System.out.println("Usage: CommandLineCalculator filename indicator...");
		}
		else {
			String filename = args[0];
//			System.out.println("Filename: " + filename);
			
			List<String> indicatorStrings = new ArrayList<>();
			for ( int i = 1; i < args.length; i++ ) {
				indicatorStrings.add(args[i]);
			}
//			System.out.println("Indicator strings...");
//			indicatorStrings.forEach(System.out::println);
			
			List<NumericExpression> indicatorExpressions = new ArrayList<>();
			
			for ( String s : indicatorStrings ) {
				int leftParen = s.indexOf("(");
				if ( leftParen > 0 ) {
					int rightParen = s.indexOf(")");
					if ( rightParen > 0 ) {
						String keyword = s.substring(0, leftParen);
						String params = s.substring(leftParen + 1, rightParen);
//						System.out.println("keyword: " + keyword);
//						System.out.println("params: " + params);
						
						for ( TechnicalAnalysisIndicator tae : TechnicalAnalysisIndicator.values() ) {
							if ( tae.keyword().equalsIgnoreCase(keyword) ) {
								NumericExpression e = tae.expression(params);
								if ( e != null ) {
									indicatorExpressions.add(e);
								}
							}
						}
					}
				}
			}
			
			Calculation calculation = Calculation.of(
					new HashSet<NumericExpression>(indicatorExpressions)
					);
			
			List<Aggregate> aggs = CsvReader.readFile(filename);
			for ( int i = 0; i < aggs.size(); i++ ) {
				CalculationResult result = calculation.apply(aggs.get(i));
				List<String> valueStrings = new ArrayList<>();
				for ( NumericExpression e : indicatorExpressions ) {
					BigDecimal v = result.valueOf(e);
					if ( v == null ) {
						valueStrings.add("null");
					}
					else {
						
						valueStrings.add(result.valueOf(e).toString());						
					}
				}
				String csvValues = String.join(",", valueStrings);
				System.out.println(String.valueOf(i) + "," + csvValues);					
				
			}	

		}

	}

}
