package com.trone.test;

import java.util.List;

import com.trone.pojo.WordMap;
import com.trone.service.Analyzer;

public class Main {
	public static void main(String[] args) {
		Analyzer analyzer = new Analyzer();
		List<WordMap> list = analyzer.analyze("test.txt");
		for (WordMap map : list) {
			System.out.println(map);
		}
	}
}
