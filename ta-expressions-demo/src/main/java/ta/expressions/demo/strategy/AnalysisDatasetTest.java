package ta.expressions.demo.strategy;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ta.expressions.core.Aggregate;
import ta.expressions.strategy.AnalysisDataset;

public class AnalysisDatasetTest {

	public static void main(String[] args) {
		
		List<AnalysisDataset> datasets = new ArrayList<>();
		
		Path dir = Paths.get("src/main/resources/candles");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		    	String filename = file.getFileName().toString();
		    	String symbol = filename.substring(0, filename.length() - 4);
				List<Aggregate> aggs = CandleReader.readFile(file);
				AnalysisDataset ds = new AnalysisDataset(symbol, aggs);
				datasets.add(ds);
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    System.err.println(x);
		}

		
		Comparator<AnalysisDataset> comp = (a,b) -> b.changePercent().compareTo(a.changePercent());
		Collections.sort(datasets, comp);
		datasets.forEach(System.out::println);
		
		BigDecimal grandTotalPriceChange = datasets.stream().map(AnalysisDataset::priceChange).reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("Grand total price change: " + grandTotalPriceChange);
		
		List<AnalysisDataset> gainers = datasets.stream().filter(d -> d.priceChange().signum() > 0).collect(Collectors.toList());
		List<AnalysisDataset> losers = datasets.stream().filter(d -> d.priceChange().signum() < 0).collect(Collectors.toList());
		List<AnalysisDataset> middle = datasets.stream().filter(d -> d.changePercent().abs().compareTo(BigDecimal.valueOf(5)) < 0).collect(Collectors.toList());
		
		System.out.println("# gainers: " + gainers.size());
		System.out.println("# losers: " + losers.size());
		System.out.println("# middle: " + middle.size());
		middle.forEach(System.out::println);
	}

}
