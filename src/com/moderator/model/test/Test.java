package com.moderator.model.test;

import java.io.IOException;

import com.moderator.model.Account;

public class Test {
	public static void main(String [] args) throws IOException {
		Account account=new Account();
		if(account.isAccountAvailable("gaurav1998", "lololol")) {
				System.out.println("yes");
		}
		
		System.out.println(account.isNameAvailable("mutuz786"));
		System.out.println(account.isNameAvailable("jmyfgjey"));
	}
}
