package com.github.tea_pack.sapphire.parsers;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
	private BufferedReader reader;

	public CSVParser(Path filePath) throws Exception {
		this.reader = Files.newBufferedReader(filePath);
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
		return line == null? null: line.split(";");
	}
}
