package com.schoolManagement.controller;

import com.schoolManagement.model.SessionImplementation;

public class Controller {

	public static void main(String[] args) throws ClassNotFoundException {
		try {
					SessionImplementation session = new SessionImplementation();
					session.initDatabase();
					session.createUE(4, "163", "Théorie des ensembles");
					//session.deleteUE(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}