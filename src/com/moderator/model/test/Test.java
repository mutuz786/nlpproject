package com.moderator.model.test;

import java.io.IOException;

import com.moderator.model.Filer;

public class Test {
	public static void main(String[] args) throws IOException {
		String text="this is my new webpage www.bit.ly/jhdgjf/aggvdhgad";
		String[] textArray = text.split("\\s");
		for (int i = 0; i < textArray.length; i++) {
			if (isScam(textArray[i])) {
				textArray[i] = "[deleted webpage]";
			}
		}
		text = "";
		for (int i = 0; i < textArray.length; i++) {
			text += textArray[i];
			if (i != textArray.length - 1)
				text += " ";
		}
		System.out.println(text);
	}
	private static boolean isScam(String text) throws IOException {
		String[] words = getScammedWebsite();
		for (String word : words) {
			if (text.contains(word))
				return true;
		}
		return false;
	}

	private static String[] getScammedWebsite() throws IOException {
		Filer filer = new Filer("scamwebsite.txt");
		String text = filer.read();
		return text.split(",");
	}

}
