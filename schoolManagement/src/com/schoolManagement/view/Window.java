package com.schoolManagement.view;

import java.awt.Color;
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
import com.schoolManagement.model.Session;
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
	private String code_ue_selected = "-1";
	private String num_classe_selected = "-1";
	private ArrayList<Integer> nums_creneaux_selected;
	
	public Window(SessionImplementation si) {
		this.sessionImplementation = si;
		this.nums_creneaux_selected = new ArrayList<Integer>();
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
	    bClasse.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	module = "Classe";
		    	showClasse();
		    }
		});
	    
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
	    
	    createSession = new JButton("Add UE to Session");  
	    createSession.setBounds(750,310,155,30);
	    frame.add(createSession);
	    
	    createButton = new JButton("Create");  
	    createButton.setBounds(750,100,95,30);
	    frame.add(createButton);
	    
	    deleteButton = new JButton("Delete");  
	    deleteButton.setBounds(750,150,95,30);
	    frame.add(deleteButton);
	    
	    Object[][] data = {{"", "", ""}};
	    String header[] = new String[] {"ID","Code","Intitulé"};
	    
	    JPanel panel = new JPanel();
	    dtm = new DefaultTableModel(data, header);

	    table = new JTable(dtm);
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
 				if (createSession.getText().contains("Create")) {
 					labelSelectedUE.setText("Aucun UE sélectionné");
			    	labelSelectedUE.setForeground(Color.BLACK);
			    	labelSelectedClasse.setText("Aucune classe sélectionnée");
 					labelSelectedClasse.setForeground(Color.BLACK);
 					labelSelectedCreneau.setText("Aucun créneau sélectionné");
 					labelSelectedCreneau.setForeground(Color.BLACK);
 					createSession();
 				}
 				
 				else if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
	 	        	if (module == "UE") {
				    	code_ue_selected = table.getValueAt(table.getSelectedRow(), 1).toString();
				    	labelSelectedUE.setText("UE sélectionné");
				    	labelSelectedUE.setForeground(Color.GREEN);
				    }
	 				if (module == "Classe") {
	 					num_classe_selected = table.getValueAt(table.getSelectedRow(), 1).toString();
	 					labelSelectedClasse.setText("Classe sélectionnée");
	 					labelSelectedClasse.setForeground(Color.GREEN);
	 				}
	 				if (module == "Créneau") {
	 					nums_creneaux_selected.add(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1).toString()));
	 					labelSelectedCreneau.setText("Créneau sélectionné");
	 					labelSelectedCreneau.setForeground(Color.GREEN);
	 				}
	 				
	 				if (!nums_creneaux_selected.isEmpty() && num_classe_selected != "-1" && code_ue_selected != "-1") {
	 					createSession.setText("Create session");
	 				}
	 				
	 	        }	
 			}
 		});
	 	
 		dtm.addTableModelListener(new TableModelListener(){
 		    @Override
 		    public void tableChanged(TableModelEvent tableModelEvent) {
 		    	
 	        }      
 		});
 		
 		showUE();
	}
	
	public void showClasse() {
		label.setText("Classe management");
		
		String header[] = new String[] {"Id classe","Section","Promotion"};
	    dtm.setColumnIdentifiers(header);
	}
	
	public void createClasse() {
		int row = table.getSelectedRow();
	    String id_classe = dtm.getValueAt(row, 0).toString();
		String section = dtm.getValueAt(row, 1).toString();	
		//String promotion = tableUE.getModel().getValueAt(row, 2).toString();
		// tableUE.getModel().getValueAt(row, 2).toString() return null, je sais pas ou est exactement le probleme, 
		String promotion = "2022";
		try {
			System.out.println(Integer.parseInt(id_classe));
			System.out.println(section);
			System.out.println(promotion);

			sessionImplementation.createClasse(Integer.parseInt(id_classe), section, Integer.parseInt(promotion));

			dtm.addRow(new Object[] { "", "", "" });
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void deleteClasse() {
		int row = table.getSelectedRow();
		String classe_id = table.getModel().getValueAt(row, 0).toString();
		sessionImplementation.deleteClasse(Integer.parseInt(classe_id));
	}
	
	public void showUE() {
		dtm.setRowCount(0);
		label.setText("UE management");
		createButton.setText("Create");
		
		String header[] = new String[] {"ID","Code","Intitulé"};
	    dtm.setColumnIdentifiers(header);
	    dtm.addRow(new Object[] {"", "", ""});
	}
	
	public void createUE() {
		int row = table.getSelectedRow();
		String id = dtm.getValueAt(row, 0).toString();
		String codeUE = dtm.getValueAt(row, 1).toString();	
		String intituleUE = dtm.getValueAt(row, 2).toString();
		try {
			sessionImplementation.createUE(Integer.parseInt(id), codeUE, intituleUE);
			dtm.addRow(new Object[] {"", "", "" });
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void deleteUE() {
		int row = table.getSelectedRow();
		String id = table.getModel().getValueAt(row, 0).toString();
		sessionImplementation.deleteUE(Integer.parseInt(id));
	}
	
	public void showSession() {
		dtm.setRowCount(0);
		label.setText("Session management");
		createButton.setText("Update");
		createSession.setVisible(false);
		
		String header[] = new String[] {"Classe","UE","Créneau"};
	    dtm.setColumnIdentifiers(header);
	    for (Session session : sessionImplementation.listSession()) {
	    	System.out.println("session"+":"+session.getUe().getId());
	    	for (Creneau creneau : session.getCreneaux()) {
	    		dtm.addRow(new Object[] { false, session.getClasse().getClasseid(), session.getUe().getId(), creneau.getIdCreneau()});
	    	}
	    }
	}
	
	public void createSession() {
		System.out.println("create session");
		try {
			num_classe_selected = "0";
			sessionImplementation.createSession(Integer.parseInt(num_classe_selected), Integer.parseInt(code_ue_selected), nums_creneaux_selected);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}