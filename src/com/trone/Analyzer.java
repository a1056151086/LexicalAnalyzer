package com.trone;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analyzer {

	/**
	 * 获取文本中所有的词汇
	 * 
	 * @param filePath
	 * @return
	 */
	public List<String> getWords(String filePath) {

		List<String> words = new ArrayList<>();

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(filePath));

			String line;
			while ((line = reader.readLine()) != null) {

				String[] strings = line.split(" ");

				for (String str : strings) {
					words.add(str);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return words;
	}

	public void analyze(String filePath) {
		List<String> words = this.getWords(filePath);

		List<Map<String, Integer>> maps = new ArrayList<>();

		HashMap<String, Integer> map = new HashMap<>();

		map.put("1", 111);

		HashMap<String, Integer> map2 = new HashMap<>();
		map2.put("1", 222);

		maps.add(map);
		maps.add(map2);

		System.out.println(maps);

//		for (String word : words) {
//			
//		}

	}
}
