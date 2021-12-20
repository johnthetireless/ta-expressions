package ta.expressions.signal;

import java.util.HashSet;
import java.util.List;

import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.core.BooleanExpression;

public class SignalEvaluation {

	private final String symbol;
	private final List<BooleanExpression> expressions;
	
	private final SignalPrinter p;
	private final SignalGenerator sg;
	
	public SignalEvaluation(String symbol, List<BooleanExpression> expressions) {
		super();
		this.symbol = symbol;
		this.expressions = expressions;
		
		p = new SignalPrinter();		
		sg = new SignalGenerator(new HashSet<>(expressions), p);
	}
	
	public void run() {
		List<Aggregate> aggs = CsvReader.readFile(symbol + ".csv");
		for ( int i = 0; i < aggs.size(); i++ ) {
			Aggregate a = aggs.get(i);
			sg.accept(a);
		}		
		System.out.println("Total number of signals: " + p.count());		
		System.out.println("Breakdown............");		
		
		for ( BooleanExpression e : expressions ) {
			System.out.println(e.toString() + " " + p.countFor(e));
		}
		System.out.println("Total number of signals " + p.count());
	}
	
	
}
