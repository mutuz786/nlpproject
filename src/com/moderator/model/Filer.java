package com.moderator.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Filer {
	private String fileName;
	public Filer(String fileName) {
		this.fileName=fileName;
	}

	public void write(String string) throws IOException {
		try (FileWriter writer = new FileWriter(fileName)) {
			writer.write(string+"\n");
			writer.flush();
		}
	}

	public String read() throws IOException {
		String text = "";
		int ascii;
		FileReader reader = null;

		try {
			reader = new FileReader(fileName);
		} catch (FileNotFoundException fe) {
			System.out.println("File Not Found");
		}

		while ((ascii = reader.read()) != -1) {
			text = text + ((char) ascii);
		}
		return text;
	}

}
