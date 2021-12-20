package ta.expressions.app;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import ta.expressions.core.Aggregate;
import ta.expressions.core.AnalysisContext;
import ta.expressions.core.NumericExpression;

public class Calculation implements AnalysisContext, Function<Aggregate, CalculationResult> {
	
	public static Calculation of(NumericExpression... expressions) {
		return of(Arrays.asList(expressions));
	}

	public static Calculation of(Collection<NumericExpression> expressions) {
		return new Calculation(expressions, MathContext.DECIMAL64, 1024);
	}
	
	

	private final Collection<NumericExpression> expressions;
	private final MathContext mathContext;
	private final FIFOSequence<Aggregate> inputs;
	private final FIFOSequence<HashMap<NumericExpression, Optional<BigDecimal>>> results;
	
	public Calculation(Collection<NumericExpression> expressions, MathContext mathContext, int bufferSize) {
		this.expressions = expressions;
		this.mathContext = mathContext;
		this.inputs = new FIFOSequence<>(bufferSize);
		this.results = new FIFOSequence<>(bufferSize);
	}
	
	public CalculationResult apply(Aggregate a) {
		inputs.add(a);
		results.add(new HashMap<>());
		int index = results.lastIndex();
		Map<NumericExpression, BigDecimal> output = new HashMap<>();
		for ( NumericExpression e : expressions ) {
			BigDecimal value = e.getValue(this, index);
			if ( value != null ) {
				output.put(e, value);				
			}
		}
		return new CalculationResult(index, a.timestamp(), output);
	}


	@Override
	public Aggregate getInput(int index) {
		return inputs.get(index);
	}

	@Override
	public BigDecimal getValue(NumericExpression e, int index) {
		Map<NumericExpression, Optional<BigDecimal>> map = results.get(index);
		if ( map == null ) {
			return null;
		}
		else {
			Optional<BigDecimal> opt = map.get(e);
			if ( opt == null ) {
				BigDecimal value = e.evaluate(this, index);
				opt = value == null ? Optional.empty() : Optional.of(value);
				map.put(e, opt);
			}
			return opt.isPresent() ? opt.get() : null;			
		}
	}

	@Override
	public MathContext getMathContext() {
		return mathContext;
	}

}
