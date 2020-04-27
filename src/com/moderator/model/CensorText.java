package com.moderator.model;

import java.io.IOException;

public class CensorText {
	String censorFile = "censoredwords.txt";

	String validate(String text) throws IOException {
		String[] textArray = text.split("\\s");
		for (int i = 0; i < textArray.length; i++) {
			if (isCensored(textArray[i])) {
				textArray[i] = censored(textArray[i]);
			}
		}
		text = "";
		for (int i = 0; i < textArray.length; i++) {
			text += textArray[i];
			if (i != textArray.length - 1)
				text += " ";
		}
		return text;
	}

	private String censored(String text) {
		char[] vowel = { 'a', 'e', 'i', 'o', 'u' };
		for (int i = 0; i < vowel.length; i++) {
			text = text.replace(vowel[i], '*');
		}
		return text;
	}

	private boolean isCensored(String text) throws IOException {
		String[] words = getCensoredWords();
		for (String word : words) {
			if (text.contains(word))
				return true;
		}
		return false;
	}

	private String[] getCensoredWords() throws IOException {
		Filer filer = new Filer(censorFile);
		String text = filer.read();
		return text.split("\n");
	}

}
