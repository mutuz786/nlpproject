package com.moderator.model;

import java.io.IOException;

public class Account {
	private String fileName = "account.txt";
	Filer filer = new Filer(fileName);

	public boolean isNameTaken(String name) throws IOException {
		String accountInfo = filer.read();
		String[] list = accountInfo.split("\n");
		for (String line : list) {
			String[] data = line.split(",");
			if (data[0].equals(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean isAccountAvailable(String name, String password) throws IOException {
		String accountInfo = filer.read();
		String[] list = accountInfo.split("\n");
		for (String line : list) {
			String[] data = line.split(",");
			String name1 = data[0];
			String password1 = data[1].replace("\n", "");
			if (name1.equals(name)) {
				if (password1.equalsIgnoreCase(password))
					return true;
			}
		}
		return false;
	}

	public void addData(String username, String password) throws IOException {
		filer.write(filer.read() + username + "," + password + ",");
	}
}
