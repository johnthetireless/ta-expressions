package ta.expressions.signal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import ta.expressions.app.BasicSignal;
import ta.expressions.app.Evaluation;
import ta.expressions.core.Aggregate;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;

public class SignalGenerator implements Consumer<Aggregate> {

	private final Set<BooleanExpression> expressions;
	private final Consumer<Signal> consumer;
	
	private final Evaluation<Boolean> eval;
	
	
	public SignalGenerator(Set<BooleanExpression> expressions, Consumer<Signal> consumer) {
		super();
		this.expressions = expressions;
		this.consumer = consumer;
		this.eval = Evaluation.of(List.copyOf(expressions));
	}


	@Override
	public void accept(Aggregate input) {
		
		Map<String, Boolean> map = eval.apply(input);
		for ( BooleanExpression rule : expressions ) {
			Boolean b = map.get(rule.representation());
			if ( b != null && b ) {
				Map<String, BigDecimal> results = eval.getResults(eval.lastIndex());
				Set<NumericExpression> terms = rule.terms();
				Map<NumericExpression, BigDecimal> values = new HashMap<>();
				for ( NumericExpression e : terms ) {
					if ( !(e.isConstant()) ) {
						BigDecimal v = results.get(e.representation());
						if ( v == null ) {
							v = eval.getValue(e, eval.lastIndex());
						}
						values.put(e, v);						
					}
				}
			
				Signal signal = new BasicSignal(eval.lastIndex(), rule, values, input);
				consumer.accept(signal);
			}
		}
		
	}

}
