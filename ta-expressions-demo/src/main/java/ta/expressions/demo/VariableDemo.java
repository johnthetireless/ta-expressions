package ta.expressions.demo;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import ta.expressions.app.Calculation;
import ta.expressions.app.CalculationResult;
import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

/**
 * A very basic demo that show how to create and evaluate variables and their previous values
 * Also shows how to access and print individual calculation results 
 * 
 * The close price variable will always have a value.
 * Previous values will not exist for the few inputs.
 * 
 */
public class VariableDemo {

	public static void main(String[] args) {
		
		NumericExpression close = ClosePrice.INSTANCE;
		NumericExpression previousClose = close.previous();
		NumericExpression previousClose5 = close.previous(5);
		
		Calculation calculation = Calculation.of(
				close, close.previous(), close.previous(5)
				);
		
		List<Aggregate> inputs = CsvReader.readFile("SPY.csv");
		
		for ( int i = 0; i < 10; i++ ) {
			
			Aggregate currentInput = inputs.get(i);
			
			CalculationResult result = calculation.apply(currentInput);
			
			LocalDate date = localDate(result.timestamp());
			
			BigDecimal closeValue = result.valueOf(close);
			BigDecimal previousValue = result.valueOf(previousClose);
			BigDecimal previousValue5 = result.valueOf(previousClose5);
			
			System.out.println("Index: " + i + " Date: " + date 
							+ " Close: " + closeValue
							+ " Previous Close: " + previousValue
							+ " Previous Close (5 days ago): " + previousValue5
							);
		}	
	}
	
	static LocalDate localDate(long timestamp) {
		Instant instant = Instant.ofEpochSecond(timestamp);
		return LocalDate.ofInstant(instant, ZoneId.systemDefault());
	}

}
