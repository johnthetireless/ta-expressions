package ta.expressions.common.stats;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.ArithmeticExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

//TODO: this depends on ClosePrice specifically; change that or move to an indicator package
//TODO: create set of "close price indicators"  this, StddDev, ROC...
public class Slope extends ArithmeticExpression {

	public static final String KEYWORD = "Slope";
	
	
	public static Slope fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new Slope(ClosePrice.INSTANCE, ps.intValue(0));
	}

	
	private final NumericExpression e;
	private final int n;
	private final BigDecimal bigN;
	
	public Slope(NumericExpression e, int n) {
		super(functionRepresentation(KEYWORD, e, n));
		this.e = e;
		this.n = n;
		this.bigN = BigDecimal.valueOf(n);
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		
		MathContext mc = context.getMathContext();
		
        final int startIndex = index - n + 1;

		BigDecimal sumX = BigDecimal.ZERO;
		BigDecimal sumY = BigDecimal.ZERO;
		
        for (int i = startIndex; i <= index; i++) {
        	BigDecimal v = e.getValue(context, i);
        	if ( v == null ) {
        		return null;
        	}
            sumX = sumX.add(BigDecimal.valueOf(i), mc);
            sumY = sumY.add(v, mc);
        }
        
        BigDecimal xBar = sumX.divide(bigN, mc);
        BigDecimal yBar = sumY.divide(bigN, mc);
        
        /*
         * Second pass: compute slope and intercept
         */
        
        BigDecimal xxBar = BigDecimal.ZERO;
        BigDecimal xyBar = BigDecimal.ZERO;

        for (int i = startIndex; i <= index; i++) {
        	BigDecimal v = e.getValue(context, i);
        	BigDecimal dx = BigDecimal.valueOf(i).subtract(xBar, mc);
        	BigDecimal dy = v.subtract(yBar, mc);
        	xxBar = xxBar.add(dx.pow(2));
        	xyBar = xyBar.add(dx.multiply(dy, mc));
        }

		return xyBar.divide(xxBar, mc);
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of(e);
	}
}
