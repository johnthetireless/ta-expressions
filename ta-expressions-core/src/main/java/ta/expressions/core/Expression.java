package ta.expressions.core;

/**
 * An expression defines a calculation or data access operation.  
 * A "calculation" is a series of one or more numeric or boolean operations in this definition.
 * <p>
 * Expressions are created without reference to any particular data set. 
 * At some later point in time, an expression is evaluated in the "context" of a particular data set.
 * Any given expression might be evaluated against different data sets.
 * <p>
 * Expressions are immutable.
 * Expressions only produce values; they do not store them in any sort of "cache".
 * <p>
 * Expressions are composed to form new expressions.  
 * We can "add" 2 numeric expressions, for example, creating a third numeric expression.
 * When evaluated, the "sum" expression will calculate the expected value, 
 * adding the evaluated values of the 2 expressions from which it was composed.
 * 
 * 
 * @param <T> the type of value returned by the evaluation of this expression.  
 */
public interface Expression<T> {
	
	String representation();
	
	T evaluate(AnalysisContext context, int index);
	
	T getValue(AnalysisContext context, int index);

}
