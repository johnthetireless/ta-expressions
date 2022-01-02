package ta.expressions.demo.strategy;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ta.expressions.app.Evaluation;
import ta.expressions.core.Aggregate;
import ta.expressions.core.BooleanExpression;

public class Filter {

	private final Set<BooleanExpression> expressions;

	public Filter(Set<BooleanExpression> expressions) {
		this.expressions = expressions;
	}
	
	public List<Match> apply(Path dir) {
		List<Match> matches = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		    	matches.addAll(process(file));
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    System.err.println(x);
		}
		return matches;
	}
	
	public static class Match {
		private final String symbol;
		private final BooleanExpression expression;
		
		Match(String symbol, BooleanExpression expression) {
			this.symbol = symbol;
			this.expression = expression;
		}

		public String symbol() {
			return symbol;
		}

		public BooleanExpression expression() {
			return expression;
		}

		@Override
		public String toString() {
			return "Match [symbol=" + symbol + ", expression=" + expression + "]";
		}
		
	}
	
	private List<Match> process(Path file) {
		
		Evaluation<Boolean> eval = Evaluation.of(List.copyOf(expressions));
		List<Match> matches = new ArrayList<>();

		String filename = file.getFileName().toString();
    	String symbol = filename.substring(0, filename.length() - 4);

		List<Aggregate> aggs = CandleReader.readFile(file);
		for ( int i = 0; i < aggs.size(); i++ ) {
			Map<String, Boolean> map = eval.apply(aggs.get(i));
			if ( i == aggs.size() - 1 ) {
				for ( BooleanExpression e : expressions ) {
					Boolean b = map.get(e.toString());
					if ( b != null && b ) {
						
						matches.add(new Match(symbol, e));
					}
				}
			}
		}
		return matches;
	}


}
