package ta.expressions.app;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ta.expressions.core.Aggregate;

public class CsvReader {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static List<Aggregate> readFile(String filename) {
		List<Aggregate> aggs = new ArrayList<>();
		
		InputStream stream = CsvReader.class.getClassLoader().getResourceAsStream(filename); 
		Scanner scanner = new Scanner(stream, Charset.forName("UTF-8"));
		String line = scanner.nextLine();  
		line = scanner.nextLine();			//skip header line
		while ( scanner.hasNext() ) {
			aggs.add(parse(line));
			line = scanner.nextLine();
		}
		scanner.close(); 
		return aggs;
	}
	
	private static Aggregate parse(String line) {
		String[] parts = line.split(",");
		int volumeIndex = 5;
		if ( parts.length > 6 ) { // ignore adjusted close price
			volumeIndex++;
		}
		long timestamp = parseTimestamp(parts[0]);
		BigDecimal open = new BigDecimal(parts[1]);
		BigDecimal high = new BigDecimal(parts[2]);
		BigDecimal low = new BigDecimal(parts[3]);
		BigDecimal close = new BigDecimal(parts[4]);
		BigDecimal volume = new BigDecimal(parts[volumeIndex]);
		return new BasicAggregate(open, high, low, close, volume, timestamp);
	}
	
	private static long parseTimestamp(String s) {
		ZonedDateTime date = ZonedDateTime.of(LocalDate.parse(s, DATE_FORMAT).atStartOfDay(), ZoneId.systemDefault());
//		System.out.println(date);
		return date.toEpochSecond();
	}
	
}
