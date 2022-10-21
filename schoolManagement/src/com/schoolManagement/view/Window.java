package com.schoolManagement.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import com.schoolManagement.model.SessionImplementation;

public class Window {

	public JFrame frame;
	
	public Window(SessionImplementation session) {

		initialize(session);
	}
	
	private void initialize(SessionImplementation session) 
	{
		frame = new JFrame();
		frame.setBounds(50, 50, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// UE part
		JFormattedTextField idUE = new JFormattedTextField();
		JFormattedTextField code = new JFormattedTextField();
		JFormattedTextField intitule = new JFormattedTextField();
		idUE.setText("ID");
		idUE.setBounds(6, 5, 79,26);
		frame.getContentPane().add(idUE);
		code.setText("Code");
		code.setBounds(86, 5, 79, 26);
		frame.getContentPane().add(code);
		intitule.setText("Intitule");
		intitule.setBounds(166, 5, 100, 26);
		frame.getContentPane().add(intitule);
		JButton createUEButton = new JButton("create UE");
		createUEButton.setBounds(270, 5, 110, 26);
		frame.getContentPane().add(createUEButton);
		JButton deleteUEButton = new JButton("delete UE");
		deleteUEButton.setBounds(270, 35, 110, 26);
		frame.getContentPane().add(deleteUEButton);
		createUEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idUE.getText();String codeUE = code.getText(); 	String intituleUE = intitule.getText();
				try {
					session.createUE(Integer.parseInt(id), codeUE, intituleUE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		deleteUEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idUE.getText();
				session.deleteUE(Integer.parseInt(id));
			}
		});
	}
}
