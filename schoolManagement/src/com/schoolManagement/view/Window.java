package com.schoolManagement.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.schoolManagement.model.SessionImplementation;

public class Window {

	public JFrame frame;
	private JButton bUE;
	private JButton bCreneau;
	private JButton bClasse;
	private JButton bSession;
	private JLabel label;
	private SessionImplementation session;
	
	public Window(SessionImplementation session) {
		this.session = session;
		initialize();
	}
	
	private void initialize() 
	{
		this.frame = new JFrame("Gestion des sessions");
		frame.setBounds(50, 50, 1000, 550);
	    frame.setUndecorated(true);
	    frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    bUE = new JButton("UE");  
	    bUE.setBounds(50,100,95,30);
	    frame.add(bUE);
	    bUE.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        manageUE();
		    }
		});
	    
	    bCreneau = new JButton("Créneau");  
	    bCreneau.setBounds(50,150,95,30); 
	    frame.add(bCreneau);
	    
	    bClasse = new JButton("Classe");  
	    bClasse.setBounds(50,200,95,30); 
	    frame.add(bClasse);
	    
	    bSession = new JButton("Session");  
	    bSession.setBounds(50,250,95,30); 
	    frame.add(bSession);
	    bSession.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        manageSession();
		    }
		});
	    
	    label = new JLabel();
	    label.setBounds(150,10,200,50);
	    label.setFont(new Font("Verdana", Font.BOLD, 15));
	    frame.add(label);
	    
	    manageUE();
	}
	
	public void manageUE() {
		label.setText("UE management");
		
		JButton createUEButton = new JButton("Create");  
	    createUEButton.setBounds(750,100,95,30);
	    frame.add(createUEButton);
	    
	    JButton deleteUEButton = new JButton("Delete");  
	    deleteUEButton.setBounds(750,150,95,30);
	    frame.add(deleteUEButton);
  
	    JPanel panel = new JPanel();
	    JTable tableUE = new JTable();
	    DefaultTableModel dtm = new DefaultTableModel(0, 0);
	    String header[] = new String[] {"ID","Code","Intitulé"};
	    dtm.setColumnIdentifiers(header);
	    dtm.addRow(new Object[] { "", "", "" });
	    JScrollPane paneUE = new JScrollPane(tableUE);
	    paneUE.setPreferredSize(new Dimension(300,500));
	    tableUE.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    panel.add(paneUE);
	    tableUE.setModel(dtm);
	    
	    frame.getContentPane().add(panel);
	    
		// UE part
		createUEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableUE.getSelectedRow();
			    String id = tableUE.getModel().getValueAt(row, 0).toString();
				String codeUE = tableUE.getModel().getValueAt(row, 1).toString();	
				String intituleUE = tableUE.getModel().getValueAt(row, 2).toString();
				try {
					session.createUE(Integer.parseInt(id), codeUE, intituleUE);
					dtm.addRow(new Object[] { "", "", "" });
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		deleteUEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableUE.getSelectedRow();
				String id = tableUE.getModel().getValueAt(row, 0).toString();
				session.deleteUE(Integer.parseInt(id));
			}
		});
	}
	
	public void manageSession() {
		label.setText("Session management");

	}
}
