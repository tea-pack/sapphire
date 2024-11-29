package com.github.tea_pack.sapphire.parsers;

import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
	private BufferedReader reader;

	public CSVParser(Path filePath) throws Exception {
		this.reader = Files.newBufferedReader(filePath);
	}

	public CSVParser(String source) {
		this.reader = new BufferedReader(new StringReader(source));
	}

	public List<String[]> consume() throws Exception {
		List<String[]> list = new ArrayList<>();
		String[] next;
		while ((next = next()) != null) {
			list.add(next);
		}
		return list;
	}

	public String[] next() throws Exception {
		String line = reader.readLine();
		if (line != null) {
			String[] split = line.split(";");
			if(line.contains("\"")) {
				int first = line.indexOf('"');
				int last = line.lastIndexOf('"');
				String quote = line.substring(first, last + 1);
				String part1 = line.substring(0, first-1).trim();
				String part2 = line.substring(last+2).trim();
				String[] split1 = part1.split(";");
				String[] split2 = part2.split(";");
				split = new String[1 + split1.length + split2.length];
				System.arraycopy(split1, 0, split, 0, split1.length);
				split[split1.length] = quote;
				System.arraycopy(split2, 0, split, split1.length + 1, split2.length);
			} else {
				split = line.split(";");
			}
			return split;
		}
		return null;
	}
}
