package ta.expressions.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.adx.ADX;
import ta.expressions.indicators.variables.Variable;

public class Subexpressions {

	public static void main(String[] args) {

		ADX adx = new ADX(14);
		
		Set<NumericExpression> subs = adx.subexpressions();
		System.out.println("Subs: " + subs.size());		
		subs.forEach(System.out::println);
		System.out.println();
		
		Set<NumericExpression> constants = subs.stream().filter(NumericExpression::isConstant).collect(Collectors.toSet());
		System.out.println("Constants: " + constants.size());
		constants.forEach(System.out::println);
		System.out.println();
		
		Set<NumericExpression> analyticFunctions = subs.stream().filter(e -> e instanceof AnalyticFunction).collect(Collectors.toSet());
		System.out.println("analyticFunctions: " + analyticFunctions.size());
		analyticFunctions.forEach(System.out::println);
		System.out.println();
		
		Set<NumericExpression> variables = subs.stream().filter(e -> e instanceof Variable).collect(Collectors.toSet());
		System.out.println("variables: " + variables.size());
		variables.forEach(System.out::println);
		System.out.println();
		
		subs.removeAll(constants);
		
		Map<NumericExpression, Set<NumericExpression>> references = new HashMap<>();
		for ( NumericExpression e : subs ) {
			references.put(e, new HashSet<>());
			for ( NumericExpression p : subs ) {
				if ( p.operands().contains(e) ) {
					references.get(e).add(p);
				}
			}
		}
		
		Map<NumericExpression, Set<NumericExpression>> multipleReferences = new HashMap<>();
		for ( Map.Entry<NumericExpression, Set<NumericExpression>> ref : references.entrySet() ) {
			System.out.println(ref.getKey() + " " + ref.getValue().size());
			if ( ref.getValue().size() > 1 ) {
				multipleReferences.put(ref.getKey(), ref.getValue());
			}
		}
		System.out.println();
		
		System.out.println("Multiple references: " + multipleReferences.size());
		multipleReferences.entrySet().forEach(System.out::println);
	}

}
