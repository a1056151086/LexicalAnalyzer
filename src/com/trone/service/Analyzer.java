package com.trone.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.trone.pojo.WordMap;

public class Analyzer {

	/**
	 * 获取文本中所有的词汇
	 * 
	 * @param filePath
	 * @return
	 */
	private List<String> getWords(String filePath) {

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

	public List<WordMap> analyze(String filePath) {
		// 所有的词汇
		List<String> words = this.getWords(filePath);

		// 保存识别结果的键值对
		List<WordMap> wordMaps = new ArrayList<>();

		for (String word : words) {
			// 词汇的第一个字符
			char head = word.charAt(0);

			int syn = -1;

			if (Character.isLetter(head)) {
				// 识别是否合法的标识符称或关键字
				syn = analyzeWord();
			} else if (Character.isDigit(head)) {
				// 识别是否合法的数字串
				syn = analyzeNumber();
			} else {
				// 识别是否合法的符号
				syn = analyzeToken();
			}

			wordMaps.add(new WordMap(syn, word));
		}

		return wordMaps;
	}

	private int analyzeToken() {
		return -1;
	}

	private int analyzeNumber() {
		return -1;
	}

	private int analyzeWord() {
		return -1;
	}
}
