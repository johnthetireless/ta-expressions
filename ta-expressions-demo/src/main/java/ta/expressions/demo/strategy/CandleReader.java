package ta.expressions.demo.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ta.expressions.app.BasicAggregate;
import ta.expressions.core.Aggregate;

/**
 * Reads aggregates from a file with timestamps
 * TODO: merge with CsvReader
 *
 */
public class CandleReader {

	public static List<Aggregate> readFile(Path file) {
		List<Aggregate> list = new ArrayList<>();
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        list.add(createAggregate(line));
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		return list;
	}
	
	private static Aggregate createAggregate(String line) {
		String[] parts = line.split(",");
		long timestamp = Long.valueOf(parts[0]);
		BigDecimal open = new BigDecimal(parts[1]);
		BigDecimal high = new BigDecimal(parts[2]);
		BigDecimal low = new BigDecimal(parts[3]);
		BigDecimal close = new BigDecimal(parts[4]);
		BigDecimal volume = new BigDecimal(parts[5]);
		return new BasicAggregate(open, high, low, close, volume, timestamp);
	}
}
