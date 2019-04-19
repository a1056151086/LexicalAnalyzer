package com.trone.pojo;

public class WordMap {

	// 种别码
	private int syn;

	// 词汇
	private String word;

	public WordMap(int syn, String word) {
		super();
		this.syn = syn;
		this.word = word;
	}

	public int getSyn() {
		return syn;
	}

	public void setSyn(int syn) {
		this.syn = syn;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "<" + syn + ", " + word + ">";
	}

}
