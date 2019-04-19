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
				syn = analyzeWord(word);
			} else if (Character.isDigit(head)) {
				// 识别是否合法的数字串
				syn = analyzeNumber(word);
			} else {
				// 识别是否合法的符号
				syn = analyzeToken(word);
			}

			wordMaps.add(new WordMap(syn, word));
		}

		return wordMaps;
	}

	/**
	 * 识别符号的种别骂
	 * 
	 * @param word
	 * @return 对应符号的种别码，不合法则返回-1
	 */
	private int analyzeToken(String word) {
		int syn = -1;
		switch (word) {
		case "+":
			syn = 13;
			break;
		case "-":
			syn = 14;
			break;
		case "*":
			syn = 15;
			break;
		case "/":
			syn = 16;
			break;
		case ":":
			syn = 17;
			break;
		case ":=":
			syn = 18;
			break;
		case "<":
			syn = 20;
			break;
		case "<>":
			syn = 21;
			break;
		case "<=":
			syn = 22;
			break;
		case ">":
			syn = 23;
			break;
		case ">=":
			syn = 24;
			break;
		case "=":
			syn = 25;
			break;
		case ";":
			syn = 26;
			break;
		case "(":
			syn = 27;
			break;
		case ")":
			syn = 28;
			break;
		case "#":
			syn = 0;
			break;
		default:
			syn = -1;
			break;
		}
		return syn;
	}

	/**
	 * 识别是否合法的数字串
	 * 
	 * @param word
	 * @return 合法返回11，不合法返回-1
	 */
	private int analyzeNumber(String word) {
		int syn = -1;
		char[] singles = word.toCharArray();

		if (singles[0] == '0') {
			return syn;
		}
		for (char single : singles) {
			//出现了非数字，不合法，返回-1
			if (!Character.isDigit(single)) {
				return syn;
			}
		}

		//是合法的数字串
		syn = 11;
		return syn;
	}

	/**
	 * 识别是否合法的标识符或关键字
	 * 
	 * @param word
	 * @return 合法则返回种别码，否则-1
	 */
	private int analyzeWord(String word) {
		int syn = -1;

		switch (word) {
		case "begin":
			syn = 1;
			break;
		case "if":
			syn = 2;
			break;
		case "then":
			syn = 3;
			break;
		case "while":
			syn = 4;
			break;
		case "do":
			syn = 5;
			break;
		case "end":
			syn = 6;
			break;
		default:
			break;
		}

		// 识别成功，是关键字，可以返回种别码
		if (syn != -1) {
			return syn;
		}

		// 不是关键字，判别是否合法的标识符
		char[] singles = word.toCharArray();

		// 第一个字母是数字，不合法，返回-1
		if (Character.isDigit(singles[0])) {
			return syn;
		}

		for (char single : singles) {
			// 如果其中出现了非数字或字母，则不合法，返回-1
			if (!Character.isDigit(single) && !Character.isLetter(single)) {
				return syn;
			}
		}

		// 是合法的标识符，返回10
		syn = 10;
		return syn;
	}
}
