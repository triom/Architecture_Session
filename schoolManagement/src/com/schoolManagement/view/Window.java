package com.schoolManagement.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.schoolManagement.model.Classe;
import com.schoolManagement.model.Creneau;
import com.schoolManagement.model.SessionImplementation;
import com.schoolManagement.model.UE;

public class Window {

	public JFrame frame;
	private JButton bUE;
	private JButton bCreneau;
	private JButton bClasse;
	private JButton bSession;
	private JButton createSession;
	private JLabel label, labelSelectedUE, labelSelectedClasse, labelSelectedCreneau;
	private SessionImplementation sessionImplementation;
	private DefaultTableModel dtm;
	private JTable table;
	private JButton createButton;
	private JButton deleteButton;
	private String module;
	private String code_ue_selected;
	private String num_classe_selected;
	private ArrayList<Integer> nums_creneaux_selected;
	
	public Window(SessionImplementation si) {
		this.sessionImplementation = si;
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
		    	module = "UE";
		    	showUE();
		    }
		});
	    
	    module = "UE";
	    
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
		    	module = "Session";
		        showSession();
		    }
		});
	    
	    label = new JLabel();
	    label.setBounds(150,10,200,50);
	    label.setFont(new Font("Verdana", Font.BOLD, 15));
	    frame.add(label);
	    
	    labelSelectedUE = new JLabel("Aucun UE sélectionné");
	    labelSelectedUE.setBounds(710,220,250,50);
	    labelSelectedUE.setFont(new Font("Verdana", Font.BOLD, 15));
	    frame.add(labelSelectedUE);
	    
	    labelSelectedClasse = new JLabel("Aucune classe sélectionnée");
	    labelSelectedClasse.setBounds(710,240,250,50);
	    labelSelectedClasse.setFont(new Font("Verdana", Font.BOLD, 15));
	    frame.add(labelSelectedClasse);
	    
	    labelSelectedCreneau = new JLabel("0 Créneau sélectionné");
	    labelSelectedCreneau.setBounds(710,260,250,50);
	    labelSelectedCreneau.setFont(new Font("Verdana", Font.BOLD, 15));
	    frame.add(labelSelectedCreneau);
	    
	    JLabel explanation = new JLabel("Pour créer une session sélectionner un UE,");
	    explanation.setBounds(670,220,300,300);
	    explanation.setFont(new Font("Verdana", Font.BOLD, 12));
	    frame.add(explanation);
	    JLabel explanation2 = new JLabel("une classe et un ou plusieurs créneaux");
	    explanation2.setBounds(670,240,300,300);
	    explanation2.setFont(new Font("Verdana", Font.BOLD, 12));
	    frame.add(explanation2);
	    
	    createSession = new JButton("Create Session");  
	    createSession.setBounds(750,310,125,30);
	    frame.add(createSession);
	    
	    createButton = new JButton("Create");  
	    createButton.setBounds(750,100,95,30);
	    frame.add(createButton);
	    
	    deleteButton = new JButton("Delete");  
	    deleteButton.setBounds(750,150,95,30);
	    frame.add(deleteButton);
	    
	    Object[][] data = {
            {false, "2", "2", "KL"},
        };
	    String header[] = new String[] {"Select", "ID","Code","Intitulé"};
	    
	    JPanel panel = new JPanel();
	    dtm = new DefaultTableModel(data, header);
	    dtm.addRow(new Object[] {false, "", "", ""});
	    
	    table = new JTable(dtm) {

            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    	return Boolean.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                    	return String.class;
                    default:
                        return String.class;
                }
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane paneUE = new JScrollPane(table);
	    paneUE.setPreferredSize(new Dimension(300,500));
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    panel.add(paneUE);
	    table.setModel(dtm);
	    
	    frame.getContentPane().add(panel);
	    
	    createButton.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				if (module == "UE")
 					createUE();
 			}
 		});
 		deleteButton.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				if (module == "UE")
 					deleteUE();
// 				if (module == "Session")
// 					deleteSession();
 			}
 		});
 		
 		createSession.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				createSession();
 			}
 		});
	 	
 		dtm.addTableModelListener(new TableModelListener(){
 		    @Override
 		    public void tableChanged(TableModelEvent tableModelEvent) {
 		    	if (table.getSelectedRow() != -1) {
	 	        	
	 	            String selection = table.getValueAt(table.getSelectedRow(), 0).toString();
				    
	 	            System.out.println(selection);
				    if (module == "UE") {
				    	if (selection == "true") {
				    		code_ue_selected = table.getValueAt(table.getSelectedRow(), 1).toString();
				    		labelSelectedUE.setText("UE sélectionné");
				    	}
				    	else {
				    		code_ue_selected = "";
				    		labelSelectedUE.setText("Aucun UE sélectionné");
				    	}
				    }
	 				if (module == "Classe") {
	 					if (selection == "true") {
	 						num_classe_selected = table.getValueAt(table.getSelectedRow(), 1).toString();
	 						labelSelectedClasse.setText("Classe sélectionnée");
	 					}
				    	else {
				    		num_classe_selected = "";
				    		labelSelectedClasse.setText("Aucune classe sélectionnée");
				    	}
	 				}
	 				if (module == "Créneau") {
	 					if (selection == "true") {
	 						nums_creneaux_selected.add(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1).toString()));
	 						labelSelectedCreneau.setText("Créneau sélectionné");
	 					}
				    	else {
				    		nums_creneaux_selected.clear();
				    		labelSelectedCreneau.setText("0 Créneau sélectionné");
				    	}
	 				}
	 	        }
 	        }      
 		});
 		
 		showUE();
	}
	
	public void showUE() {
		dtm.setRowCount(0);
		label.setText("UE management");
		createButton.setText("Create");
		
		String header[] = new String[] {"Select", "ID","Code","Intitulé"};
	    dtm.setColumnIdentifiers(header);
	    dtm.addRow(new Object[] {false, "", "", ""});
	}
	
	public void createUE() {
		int row = table.getSelectedRow();
		String id = dtm.getValueAt(row, 1).toString();
		String codeUE = dtm.getValueAt(row, 2).toString();	
		String intituleUE = dtm.getValueAt(row, 3).toString();
		try {
			sessionImplementation.createUE(Integer.parseInt(id), codeUE, intituleUE);
			dtm.addRow(new Object[] { false, "", "", "" });
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void deleteUE() {
		int row = table.getSelectedRow();
		String id = table.getModel().getValueAt(row, 1).toString();
		sessionImplementation.deleteUE(Integer.parseInt(id));
	}
	
	public void showSession() {
		dtm.setRowCount(0);
		label.setText("Session management");
		createButton.setText("Update");
		
		String header[] = new String[] {"Select","Classe","UE","Créneau"};
	    dtm.setColumnIdentifiers(header);
	}
	
	public void createSession() {
		try {
			System.out.println("bestie"+code_ue_selected);
			num_classe_selected = "0";
			sessionImplementation.createSession(Integer.parseInt(num_classe_selected), Integer.parseInt(code_ue_selected), nums_creneaux_selected);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
