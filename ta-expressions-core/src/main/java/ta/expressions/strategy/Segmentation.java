package ta.expressions.strategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Segmentation {

	public static BigDecimal computeOutOfSampleSize(int datasetSize, int numberOfSegments, double outOfSamplePercent) {
		MathContext mc = MathContext.DECIMAL64;
		BigDecimal numberOfOS = BigDecimal.valueOf(numberOfSegments - 1)
							.add(BigDecimal.ONE
									.divide(BigDecimal.valueOf(outOfSamplePercent), mc));
		return BigDecimal.valueOf(datasetSize).divide(numberOfOS, mc);
	}
	
	public static List<WalkForwardSegment> createSegments(int datasetSize, int numberOfSegments, double outOfSamplePercent) {
		MathContext mc = MathContext.DECIMAL64;
		BigDecimal ooss = computeOutOfSampleSize(datasetSize, numberOfSegments, outOfSamplePercent);
		BigDecimal ss = ooss.multiply(BigDecimal.ONE.divide(BigDecimal.valueOf(outOfSamplePercent), mc));
		BigDecimal inss = ss.subtract(ooss);
		
		mc = new MathContext(6, RoundingMode.HALF_UP);
		ooss = ooss.round(mc);
		ss = ss.round(mc);
		inss = inss.round(mc);
		
//		System.out.println(ooss.intValue() + " " + ss.intValue() + " " + inss.intValue());
		
		List<WalkForwardSegment> segments = new ArrayList<>();
		for ( int i = 0; i < numberOfSegments; i++ ) {
			int start = i * ooss.intValue();
			int mid = start + inss.intValue() - 1;
			int end = mid + ooss.intValue();
			segments.add(new WalkForwardSegment(start, mid, end));
		}
		return segments;
	}
	
	//TODO: merge this copy&paste code
	public static List<WalkForwardSegment> createAnchoredSegments(int datasetSize, int numberOfSegments, double outOfSamplePercent) {
		MathContext mc = MathContext.DECIMAL64;
		BigDecimal ooss = computeOutOfSampleSize(datasetSize, numberOfSegments, outOfSamplePercent);
		BigDecimal ss = ooss.multiply(BigDecimal.ONE.divide(BigDecimal.valueOf(outOfSamplePercent), mc));
		BigDecimal inss = ss.subtract(ooss);
		
		mc = new MathContext(6, RoundingMode.HALF_UP);
		ooss = ooss.round(mc);
		ss = ss.round(mc);
		inss = inss.round(mc);
		
//		System.out.println(ooss.intValue() + " " + ss.intValue() + " " + inss.intValue());
		
		List<WalkForwardSegment> segments = new ArrayList<>();
		for ( int i = 0; i < numberOfSegments; i++ ) {
			int start = i * ooss.intValue();
			int mid = start + inss.intValue() - 1;
			int end = mid + ooss.intValue();
			segments.add(new WalkForwardSegment(0, mid, end));
		}
		return segments;
	}
}
