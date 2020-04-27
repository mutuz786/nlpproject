package com.moderator.model;

import java.io.IOException;

public class Moderator {
	private String username;
	private String password;
	private final String commentFile = "comment.txt";

	public Moderator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void publishComment(String comment) throws IOException {
		if (comment != null || comment != "" || comment != "\\s") {
			String text = getComment();
			CensorText censor = new CensorText();
			comment = censor.validate(comment);
			ScamRemover scam = new ScamRemover();
			comment = scam.remove(comment);
			String newEntry = username + ":" + comment;
			Filer filer = new Filer(commentFile);
			filer.write(text + newEntry);
		}

	}

	public String getComment() throws IOException {
		Filer filer = new Filer(commentFile);
		return filer.read();
	}
}