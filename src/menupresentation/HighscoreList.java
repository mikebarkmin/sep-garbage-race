package menupresentation;

import helper.BackGroundPane;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import entities.xml.Game;
import entities.xml.Level;
import entities.xml.User;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andre
 */
public class HighscoreList extends JFrame {

    
    public HighscoreList(JFrame parent, User user, Game game) {
        initComponents();
        this.user = user;
        this.parent = parent;
        this.game = game;
        levels = game.getLevels();
        myInitComponents();
    }
    
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable jTable1;
    private JTree jTree1;                   
   
    private JFrame parent;
    private User user;
    private Game game;
    private List<Level> levels;
    
    //Hier werden die Komponenten initialisiert                 
    private void initComponents() {

        jScrollPane2 = new JScrollPane();
        jTree1 = new JTree();
        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jPanel1 = new JPanel();
        jLabel3 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jButton4 = new JButton();
        jLabel2 = new JLabel();
        jButton2 = new JButton();
        jButton3 = new JButton();

        jScrollPane2.setViewportView(jTree1);

        jPanel1.setOpaque(false);
        
        jLabel2.setFont(new java.awt.Font("Kristen ITC", 0, 17)); // NOI18N
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SEPGarbageRace");
        setBounds(new java.awt.Rectangle(100, 50, 800, 600));
        setResizable(false);
        setContentPane(new BackGroundPane("images/Hintergrund/Hintergrund3.png"));
        
        jButton1 = setButtonStyle(jButton1, "Zurück", true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        jLabel3.setFont(new java.awt.Font("Kristen ITC", 0, 48)); // NOI18N
        jLabel3.setText("Bestenliste:");

        jTable1.setFont(new java.awt.Font("Kristen ITC", 0, 14)); // NOI18N
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Platz", "Name", "Zeit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setEnabled(false);
        jTable1.setRowHeight(23);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);


        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jButton2 = setButtonStyle(jButton2, "Vor", true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3 = setButtonStyle(jButton3, "Zurück", true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel1)
                        .addGap(0, 263, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    //Hier werden die erste Strecke mit der Bestenliste geladen
    private void myInitComponents(){
        if(levels!=null && levels.size()>0){
        	//Überschrift setzen
            jLabel2.setText(levels.get(0).getName());
            jLabel2.setHorizontalAlignment( SwingConstants.CENTER );
            //Tabelle mit den 10 Einträgen füllen
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(182);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
            for(int i = 0; i<levels.get(0).getHighscore().getEntries().size(); i++){
                jTable1.setValueAt((i+1), i, 0);
                jTable1.setValueAt(levels.get(0).getHighscore().getEntries().get(i).getUsername(), i, 1);
                jTable1.setValueAt(levels.get(0).getHighscore().getEntries().get(i).getTime(), i, 2);
            }
            //Streckenbild laden
            jButton4.setIcon(new ImageIcon(levels.get(0).getModel()));
        }
    }                               

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //Hier wird der Zurück-Button benutzt
        parent.setVisible(true);
        setVisible(false);
        dispose();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Hier wird die naechste Strecke geladen
        if(levels!=null && levels.size()>0){
        	//Über die Level iterieren, bis die aktuelle Strecke gefunden wurde
        	for(int j=0; j<levels.size(); j++){
            	//Strecke gefunden
        		if(levels.get(j).getName().equals(jLabel2.getText())){
            		//Hier wird geprüft, ob die letzte Strecke ausgewählt ist
        			if((j+1)<levels.size()){
            			//Wenn nicht die letzte ausgewählt ist, dann wird die nächste Strecke angezeigt
        	        	//Überschrift setzen
        				jLabel2.setText(levels.get(j+1).getName());
        	            //Tabelle mit den 10 Einträgen füllen
                        for(int i = 0; i<levels.get(j+1).getHighscore().getEntries().size(); i++){
                            jTable1.setValueAt((i+1), i, 0);
                            jTable1.setValueAt(levels.get(j+1).getHighscore().getEntries().get(i).getUsername(), i, 1);
                            jTable1.setValueAt(levels.get(j+1).getHighscore().getEntries().get(i).getTime(), i, 2);
                        }
                        //Streckenbild laden
                        jButton4.setIcon(new ImageIcon(levels.get(j+1).getModel()));
                        break;
                   }
                   else{
                	   //Wenn die letzte Strecke ausgewählt ist, dann wird die erste Strecke angezeigt
                   		//Überschrift setzen
                        jLabel2.setText(levels.get(0).getName());
                        //Tabelle mit den 10 Einträgen füllen
                        for(int i = 0; i<levels.get(0).getHighscore().getEntries().size(); i++){
                            jTable1.setValueAt((i+1), i, 0);
                            jTable1.setValueAt(levels.get(0).getHighscore().getEntries().get(i).getUsername(), i, 1);
                            jTable1.setValueAt(levels.get(0).getHighscore().getEntries().get(i).getTime(), i, 2);
                        }
                        //Streckenbild laden
                        jButton4.setIcon(new ImageIcon(levels.get(0).getModel()));
                        break;
                   }
               } 
            }
        }
    }   
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Hier wird die vorherige Strecke geladen
        if(levels!=null && levels.size()>0){
        	//Über die Level iterieren, bis die aktuelle Strecke gefunden wurde
            for(int j=0; j<levels.size(); j++){
            	//Strecke gefunden
            	if(levels.get(j).getName().equals(jLabel2.getText())){
            		//Hier wird geprüft, ob die erste Strecke ausgewählt ist
            		if((j-1)>=0){
            			//Wenn nicht die erste ausgewählt ist, dann wird die vorherige Strecke angezeigt
                    	//Überschrift setzen
                        jLabel2.setText(levels.get(j-1).getName());
                        //Tabelle mit den 10 Einträgen füllen
                        for(int i = 0; i<levels.get(j-1).getHighscore().getEntries().size(); i++){
                            jTable1.setValueAt((i+1), i, 0);
                            jTable1.setValueAt(levels.get(j-1).getHighscore().getEntries().get(i).getUsername(), i, 1);
                            jTable1.setValueAt(levels.get(j-1).getHighscore().getEntries().get(i).getTime(), i, 2);
                        }
                        //Streckenbild laden
                        jButton4.setIcon(new ImageIcon(levels.get(j-1).getModel()));
                        break;
                   }
                   else{
                	   //Wenn die erste Strecke ausgewählt ist, dann wird die letzte Strecke angezeigt
                   		//Überschrift setzen	
                        jLabel2.setText(levels.get(levels.size()-1).getName());
                        //Tabelle mit den 10 Einträgen füllen
                        for(int i = 0; i<levels.get(levels.size()-1).getHighscore().getEntries().size(); i++){
                            jTable1.setValueAt((i+1), i, 0);
                            jTable1.setValueAt(levels.get(levels.size()-1).getHighscore().getEntries().get(i).getUsername(), i, 1);
                            jTable1.setValueAt(levels.get(levels.size()-1).getHighscore().getEntries().get(i).getTime(), i, 2);
                        }
                        //Streckenbild laden
                        jButton4.setIcon(new ImageIcon(levels.get(levels.size()-1).getModel()));
                        break;
                   }
               } 
            }
        }
    }    
    
    //Hier wird der ButtonStyle gesetzt
    private JButton setButtonStyle(JButton b, String text, boolean enabled){
    	b.setFont(new java.awt.Font("Kristen ITC", 0, 17));
        b.setForeground(new Color(255,76,0));
        b.setText(text);
        b.setBackground(new Color(25,7,0));
        b.setEnabled(enabled);
        return b;
    }
}
