package com.moderator.model;

import java.io.IOException;

public class ScamRemover {
	String scamFile = "scamwebsite.txt";

	public String remove(String text) throws IOException {
		String[] textArray = text.split("\\s");
		for (int i = 0; i < textArray.length; i++) {
			if (isScam(textArray[i])) {
				textArray[i] = "";
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

	private boolean isScam(String text) throws IOException {
		String[] words = getScammedWebsite();
		for (String word : words) {
			if (text.contains(word))
				return true;
		}
		return false;
	}

	private String[] getScammedWebsite() throws IOException {
		Filer filer = new Filer(scamFile);
		String text = filer.read();
		return text.split("\n");
	}


}
