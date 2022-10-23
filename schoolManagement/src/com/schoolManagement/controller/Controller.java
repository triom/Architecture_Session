package com.schoolManagement.controller;

import com.schoolManagement.model.SessionImplementation;
import com.schoolManagement.view.Window;

public class Controller {

	public static void main(String[] args) throws ClassNotFoundException {
		try {
					SessionImplementation session = new SessionImplementation();
					session.initDatabase();
					Window window = new Window(session);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}