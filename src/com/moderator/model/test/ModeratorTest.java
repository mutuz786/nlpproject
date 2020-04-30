package com.moderator.model.test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.moderator.model.*;

public class ModeratorTest {
	static String comment;
	static String name;
	static String text;
	static Moderator mod = null;

	public static void main(String[] args) throws IOException {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		Frame loginWindow = new Frame("Login");
		loginWindow.setLayout(null);
		Frame mainWindow = new Frame("Main");
		mainWindow.setSize(dimension);
		mainWindow.setLayout(null);
		mainWindow.setVisible(false);
		Label username = new Label("Name:", Label.CENTER);
		Label password = new Label("Password:", Label.CENTER);
		Label info=new Label("",Label.CENTER);
		TextField name = new TextField(20);
		TextField pass = new TextField(20);
		pass.setEchoChar('*');
		Button login = new Button("submit");
		Button register = new Button("Register");
		Button close = new Button("cancel");
		loginWindow.add(username);
		loginWindow.add(name);
		loginWindow.add(password);
		loginWindow.add(info);
		loginWindow.add(pass);
		loginWindow.add(login);
		loginWindow.add(close);
		loginWindow.add(register);
		username.setBounds(70, 90, 90, 20);
		password.setBounds(70, 130, 90, 20);
		info.setBounds(100, 310, 200, 20);
		name.setBounds(170, 90, 110, 20);
		pass.setBounds(170, 130, 110, 20);
		login.setBounds(40, 260, 100, 40);
		register.setBounds(150, 260, 100, 40);
		close.setBounds(260, 260, 100, 40);
		loginWindow.setVisible(true);
		loginWindow.setSize(400, 400);
		TextField commentArea = new TextField();
		commentArea.setBounds(20, dimension.height-215, dimension.width / 2 - 10, 100);
		List field = new List();
		field.setBounds(dimension.width / 2 + 20, 50, dimension.width / 2 - 40, dimension.height - 100);

		Button close1 = new Button("Close");
		close1.setBounds(20, dimension.height-110, 230, 50);
		Button commentPost = new Button("Comment");
		commentPost.setBounds(230, dimension.height-110, 250, 50);
		mainWindow.add(commentPost);
		mainWindow.add(close1);
		mainWindow.add(commentArea);
		mainWindow.add(field);

		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = name.getText();
				String upass = pass.getText();
				if (uname != "" || upass != "") {
					Account account = new Account();
					try {
						if (account.isAccountAvailable(uname, upass)) {
							mod = new Moderator(uname, upass);
							mainWindow.setVisible(true);
							refreshComment(field);
						} else {
							info.setText("Invalid Credentials");
						}
					} catch (IOException e1) {
					}
				}
			}
		});
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = name.getText();
				String upass = pass.getText();
				if (uname != "\\s" || upass != "\\s") {
					Account account = new Account();
					try {
						if (account.isNameTaken(uname)) {
							info.setText("Username already taken");
						} else {
							account.addData(uname, upass);
						}
					} catch (IOException e1) {
					}
				}
			}
		});
		close1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		commentPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comment = commentArea.getText();
					mod.publishComment(comment);
					commentArea.setText("");
					field.removeAll();
					refreshComment(field);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	private static void refreshComment(List field) throws IOException {
		String text = mod.getComment();
		String[] textArray = text.split("\n");
		for (String text1 : textArray) {
			field.add(text1);
		}
	}

}