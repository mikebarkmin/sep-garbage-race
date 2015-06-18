package menupresentation;

import helper.BackGroundPane;
import helper.XMLParser;

import java.awt.Color;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import entities.xml.Car;
import entities.xml.Game;
import entities.xml.User;


/**
 *
 * @author Andre
 */
public class MainMenu extends JFrame {

	//Konstruktor
    public MainMenu() {
        initDialog1Components();
        initDialog2Components();
        initFrameComponents();
        pack();
        myInitComponents();
    }
    
//----------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------MainMenu-Komponenten---------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------
    
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton11;
    
  //Hier werden die Komponenten des MainMenu-Fensters initialisiert
    private void initFrameComponents(){
    	//MainMenu-Komponenten
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        
        //MainMenu-Komponenten
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SEPGarbageRace");
        setBounds(new java.awt.Rectangle(100, 50, 800, 600));
        setResizable(false);
        setVisible(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        setContentPane(new BackGroundPane("images/Hintergrund/Hintergrund2.png"));

        jButton1 = setButtonStyle(jButton1, "Rennen starten", false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2 = setButtonStyle(jButton2, "Upgrades", false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3 = setButtonStyle(jButton3, "Bestenliste", false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4 = setButtonStyle(jButton4, "Spiel beenden", false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton11 = setButtonStyle(jButton11, "Benutzer wechseln", false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(152,152,152)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        //Nach dem Starten wird der Log-In-Dialog aufgerufen
        jDialog1.setVisible(true);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {    
        setVisible(false);
        TrackList trackList = new TrackList(this, user, game);
        trackList.setVisible(true);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //Hier wird die Upgradeseite aufgerufen
        setVisible(false);
        UpgradesList upgradesList = new UpgradesList(this, user, game);
        upgradesList.setVisible(true);
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //Hier wird die Highscoreseite aufgerufen
        setVisible(false);
        HighscoreList highscoreList = new HighscoreList(this, user, game);
        highscoreList.setVisible(true);
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //Hier wird das System beendet
        setVisible(false);
        dispose();
        System.exit(0);
    }   
    
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        //Hier wird der Benutzer gewechselt
    	jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
        jButton11.setEnabled(true);
        myInitComponents();
    	jDialog1.setVisible(true);
    }  
    
    
//----------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------Log-In-Dialog-Komponenten----------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JComboBox jComboBox1;
    private JDialog jDialog1;
    private JLabel jLabel2;
    
    //Hier werden die Komponenten des Log-In-Dialogs initialisiert
    private void initDialog1Components(){
    	//Log-In-Dialog Komponenten
        jDialog1 = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        
        //Log-In-Dialog
        jDialog1.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
        jDialog1.setAlwaysOnTop(true);
        jDialog1.setBounds(new java.awt.Rectangle(300, 200, 400, 300));
        jDialog1.setMinimumSize(new java.awt.Dimension(400, 300));
        jDialog1.setModal(true);
        jDialog1.setResizable(false);
        jDialog1.setContentPane(new BackGroundPane("images/Hintergrund/Hintergrund3.png"));

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 0, 17));
        jLabel2.setText("Benutzer:");

        jComboBox1.setFont(new java.awt.Font("Kristen ITC", 0, 17));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        jButton5 = setButtonStyle(jButton5, "Neuen Benutzer erstellen", true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6 = setButtonStyle(jButton6, "Beenden", true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7 = setButtonStyle(jButton7, "Einloggen", true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8 = setButtonStyle(jButton8, "Benutzer entfernen", true);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(65,65,65)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
    }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //Hier gelangt man zum Benutzerregister-Dialog
        jDialog1.setVisible(false);
        jDialog2.setVisible(true);
    }  
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //Hier wird das System beendet
        jDialog1.setVisible(false);
        jDialog1.dispose();
        setVisible(false);
        dispose();
        System.exit(0);
    }                                        

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //Hier wird ueberprueft, ob ein Benutzer ausgewaehlt wurde
        if(!jComboBox1.getSelectedItem().toString().equals("")){
        	for(User u:game.getUsers()){
        		if(u.getUsername().equals(jComboBox1.getSelectedItem().toString())){
        			user = u;
        			break;
        		}
        	}
            jDialog1.setVisible(false);
            jDialog1.dispose();
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            jButton4.setEnabled(true);
            jButton11.setEnabled(true);
//            setVisible(true);
        }
    }                                        

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //Hier wird ein Benutzer geloescht
    	//Wenn ein Benutzer ausgewählt wurde
    	if(!jComboBox1.getSelectedItem().toString().equals("")){
            String remUser = jComboBox1.getSelectedItem().toString();
            List<User> users = game.getUsers(); 
            //Alle Objekte der User-Liste durchsuchen
            for(int i=0; i<users.size(); i++){
            	//Wenn User gefunden, dann wird dieser geloescht und die XML-Datei aktualisiert
            	if(users.get(i).getUsername().equals(remUser)){
            		if(JOptionPane.showOptionDialog(jDialog1, "Möchten Sie den Benutzer \n"+remUser+"\nwirklich löschen? ", "Benutzer löschen", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, "No")==0){
            			users.remove(i);
                		game.setUsers(users);
                		XMLParser xmlParser = new XMLParser();
                		xmlParser.createXMLFile(game);
                		myInitComponents();
                		break;
            		}
            	}
            }
        }
    } 
    
    
//----------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------Benutzer-Hinzufuegen-Komponenten-------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------
    private JButton jButton9;
    private JButton jButton10;
    private JDialog jDialog2;
    private JLabel jLabel3;
    private JTextField jTextField1;   
    
    //Hier werden die Komponenten des Benutzer-Hinzufügen-Dialogs initialisiert
    private void initDialog2Components(){
    	//Benutzer-Hinzufügen-Dialog Komponenten
        jDialog2 = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        
      //Benutzer-Hinzufügen-Dialog
        jDialog2.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
        jDialog2.setAlwaysOnTop(true);
        jDialog2.setBounds(new java.awt.Rectangle(300, 200, 400, 300));
        jDialog2.setMinimumSize(new java.awt.Dimension(400, 300));
        jDialog2.setModal(true);
        jDialog2.setResizable(false);
        jDialog2.setContentPane(new BackGroundPane("images/Hintergrund/Hintergrund3.png"));

        jTextField1.setFont(new java.awt.Font("Kristen ITC", 0, 17));
        
        jLabel3.setFont(new java.awt.Font("Kristen ITC", 0, 17));
        jLabel3.setText("Geben Sie ihren Namen ein:");

        jButton9 = setButtonStyle(jButton9, "Zurück", true);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10 = setButtonStyle(jButton10, "OK", true);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
    }
    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //Hier gelangt man zurueck zum Log-In-Dialog vom Benutzerregister-Dialog
        jDialog2.setVisible(false);
        jDialog2.dispose();
        jDialog1.setVisible(true);
    }                                        

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) { 
        //Hier wird ueberprueft, ob ein Benutzer angelegt wurde
        if(!jTextField1.getText().equals("")){
            boolean check = false;
            for(User u:game.getUsers()){
                if(jTextField1.getText().equals(u.getUsername()))
                    check = true;
            }
            
            //Wenn der neue Benutzer noch nicht in der Benutzerliste ist, dann
            //wird ein neues Objekt vom Typ User erstellt
            if(!check){
            	List<User> users = game.getUsers();
                User newUser = new User();
                newUser.setUsername(jTextField1.getText());
                newUser.setCredits(0);
                
                //Der Benutzer bekommt den Standardmüllwagen zugeteilt
                Car firstCar = new Car();
                firstCar.setCarname("Car 1");
                firstCar.setEngineupgrade(1);
                firstCar.setStabilityupgrade(1);
                firstCar.setTiresupgrade(1);
                firstCar.setVolumeupgrade(1);
                
                newUser.setCar(firstCar);
                
                users.add(newUser);
                game.setUsers(users);
                user = newUser;
                
                //Hier wird alles in der XML-Datei gespeichert
                XMLParser cr = new XMLParser();
                cr.createXMLFile(game);
                
                jDialog1.setVisible(false);
                jDialog1.dispose();
                jDialog2.setVisible(false);
                jDialog2.dispose();
                jButton1.setEnabled(true);
                jButton2.setEnabled(true);
                jButton3.setEnabled(true);
                jButton4.setEnabled(true);
                jButton11.setEnabled(true);
            }
            //Falls es den Benutzer schon gibt, dann wird das Textfeld geleert
            else{
                jTextField1.setText("");
            }
        }
    }         

    
//----------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------Globale-Komponenten------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------
    private User user;
    private Game game;
    
    //Hier wird die Methode zum einlesen der XML-Datei aufgerufen
    private void myInitComponents(){
        user = null;
        //XML-Datei einlesen
        XMLParser xmlParser = new XMLParser();
        game = xmlParser.readXMLFile();
        //Wenn XML-Datei leer, Programm abbruch
        if(game == null){
            System.exit(0);
        }
        else{
        	//Hier wird die Benutzer-ComboBox des Log-In-Dialogs gefüllt
            String [] s = new String[game.getUsers().size()];
            for(int i=0; i<game.getUsers().size();i++){
                s[i] = game.getUsers().get(i).getUsername();
            }
            jComboBox1.setModel(new DefaultComboBoxModel(s));
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
    
    //Programmstart
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}
