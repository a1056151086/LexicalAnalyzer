package com.trone;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		Analyzer analyzer = new Analyzer();

//		List<String> words = analyzer.getWords("test.txt");

//		for (String str : words) {
//			System.out.println(str);
//		}

		analyzer.analyze("test.txt");

	}
}
