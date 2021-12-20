package ta.expressions.app;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ta.expressions.core.Aggregate;
import ta.expressions.core.AnalysisContext;
import ta.expressions.core.Expression;
import ta.expressions.core.NumericExpression;

public class Evaluation<T> implements AnalysisContext {

	private final List<Expression<T>> expressions;
	private final FIFOSequence<Aggregate> inputs;
	private final FIFOSequence<HashMap<String, Optional<BigDecimal>>> results;
	private final MathContext mathContext;

	@SafeVarargs
	public static <T> Evaluation<T> of(Expression<T>... expressions) {
		return new Evaluation<T>(Arrays.asList(expressions));
	}

	public static <T> Evaluation<T> of(List<Expression<T>> expressions) {
		return new Evaluation<T>(expressions);
	}

	public Evaluation(List<Expression<T>> expressions) {
		this(expressions, MathContext.DECIMAL64, 1024);
	}

	public Evaluation(List<Expression<T>> expressions, MathContext mathContext, int bufferSize) {
		this.expressions = expressions;
		this.inputs = new FIFOSequence<>(bufferSize);
		this.results = new FIFOSequence<>(bufferSize);
		this.mathContext = mathContext;
	}


	public List<Expression<T>> expressions() {
		return expressions;
	}

	public Map<String, T> apply(Aggregate a) {
		inputs.add(a);
		results.add(new HashMap<>());
		int index = results.lastIndex();
		Map<String, T> output = new HashMap<>();
		for ( Expression<T> e : expressions ) {
			String key = e.toString();
			T value = e.getValue(this, index);
			if ( value != null ) {
				output.put(key, value);				
			}
		}
//		System.out.println("results map size = " + results.get(index).size() + " " + results.get(index));
		return output;
	}


	@Override
	public Aggregate getInput(int index) {
		return inputs.get(index);
	} 

	
	@Override
	public MathContext getMathContext() {
		return mathContext;
	}

	@Override
	public BigDecimal getValue(NumericExpression expression, int index) {
		Map<String, Optional<BigDecimal>> map = results.get(index);
		if ( map == null ) {
			return null;
		}
		else {
			String key = expression.toString();
			Optional<BigDecimal> opt = map.get(key);
			if ( opt == null ) {
				BigDecimal value = expression.evaluate(this, index);
				opt = value == null ? Optional.empty() : Optional.of(value);
				map.put(key, opt);
			}
			return opt.isPresent() ? opt.get() : null;			
		}
	}

	public Map<String, BigDecimal> getResults(int index) {
		Map<String, Optional<BigDecimal>> map = results.get(index);
		if ( map == null ) {
			return null;
		}
		else {
			Map<String, BigDecimal> results = new HashMap<>();
			for ( Map.Entry<String, Optional<BigDecimal>> e : map.entrySet() ) {
				Optional<BigDecimal> opt = e.getValue();
				if ( opt.isPresent() ) {
					results.put(e.getKey(), opt.get());
				}
			}
			return results;			
		}
	}
	
	public int lastIndex() {
		return results.lastIndex();
	}
}
