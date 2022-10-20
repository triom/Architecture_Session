package com.schoolManagement.controller;

import com.schoolManagement.model.SessionImplementation;

public class Controller {

	public static void main(String[] args) throws ClassNotFoundException {
		try {
					SessionImplementation session = new SessionImplementation();
					session.initDatabase();
					session.createUE("1", "216", "Thermodynamics");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}

