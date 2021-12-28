package ta.expressions.demo;

import java.util.List;
import java.util.Set;

import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.core.BooleanExpression;
import ta.expressions.indicators.RSI;
import ta.expressions.indicators.macd.MACD;
import ta.expressions.signal.EntryExitList;
import ta.expressions.signal.SignalGenerator;
import ta.expressions.signal.SingleEntryExitEvaluation;

public class SingleEntryExitDemo {

	public static void main(String[] args) {
		
		MACD macd = new MACD(12,26);
		RSI rsi = new RSI(14);
		
		BooleanExpression bullish = macd.crossedOver(macd.signal(9))
									.and(rsi.lessThan(70));
		
		BooleanExpression bearish = macd.crossedUnder(macd.signal(9));
		
		SingleEntryExitEvaluation eval = new SingleEntryExitEvaluation(bullish, bearish);		
		SignalGenerator sg = new SignalGenerator(Set.of(bullish,bearish), eval);
		
		List<Aggregate> aggs = CsvReader.readFile("IBM.csv");
		aggs.forEach(sg::accept);
		
		EntryExitList report =  eval.createReport();
		
		System.out.println("Total inputs: " + aggs.size());
		System.out.println();
		
		System.out.println("Number of exits: " + report.numberOfItems());
		System.out.println("Total price change: " + report.totalPriceChange());
		System.out.println("Average price change: " + report.averagePriceChange());
		System.out.println();
		
		System.out.println("Total days active: " + report.totalDays());
		
		System.out.println("Number of gains: " + report.numberOfGains());
		System.out.println("Number of losses: " + report.numberOfLosses());
		System.out.println();
		
		System.out.println("Total gains: " + report.totalGain());
		System.out.println("Total losses: " + report.totalLoss());
		System.out.println();
		
		System.out.println("Average gain: " + report.averageGain());
		System.out.println("Average loss: " + report.averageLoss());
		System.out.println();
		
		
	}

}
