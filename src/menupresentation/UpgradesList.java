package menupresentation;

import helper.BackGroundPane;
import helper.XMLParser;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import entities.xml.Car;
import entities.xml.Game;
import entities.xml.Trashcar;
import entities.xml.Upgrade;
import entities.xml.User;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Andre
 */
public class UpgradesList extends JFrame {

	// Konstruktor der UpgradeList
	public UpgradesList(JFrame parent, User user, Game game) {
		initComponents();
		this.user = user;
		this.parent = parent;
		this.game = game;
		myInitComponents();
	}

	private JButton jButton1_1_1;
	private JButton jButton2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private Upgradebars jPanel1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTable jTable1;
	private JTree jTree1;

	private JFrame parent;
	private Game game;
	private Trashcar trashcar;
	private User user;
	private int selectedRowIndex;

	// Hier werden die Komponenten initialisiert
	private void initComponents() {

		jScrollPane2 = new JScrollPane();
		jTree1 = new JTree();
		jLabel1 = new JLabel();
		jButton1_1_1 = new JButton();
		//jPanel1 = new JPanel();
		jScrollPane1 = new JScrollPane();
		jTable1 = new JTable();
		jLabel4 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel3.setForeground(new Color(139, 69, 19));
		jLabel3.setBackground(new Color(0, 255, 0));
		jLabel5 = new JLabel();
		jPanel1 = new Upgradebars();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		jLabel8 = new JLabel();
		jLabel9 = new JLabel();
		jButton2 = new JButton();

		jScrollPane2.setViewportView(jTree1);

		jPanel1.setOpaque(false);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("SEPGarbageRace");
		setBounds(new java.awt.Rectangle(100, 50, 800, 600));
		setResizable(false);
		setContentPane(new BackGroundPane("images/Hintergrund/Hintergrund3.png"));

		jButton1_1_1 = setButtonStyle(jButton1_1_1, "Zurück", true);
		jButton1_1_1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jTable1.setFont(new java.awt.Font("Kristen ITC", 0, 14)); // NOI18N
		jTable1.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null }, { null, null, null },
				{ null, null, null }, { null, null, null } }, new String[] {
				"Nr", "Bezeichnung", "Level" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.String.class };
			boolean[] canEdit = new boolean[] { false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable1.getColumnModel().getColumn(0).setPreferredWidth(27);
		jTable1.getColumnModel().getColumn(1).setPreferredWidth(180);
		jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
		jTable1.setRowHeight(23);
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.getTableHeader().setReorderingAllowed(false);
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable1);

		jLabel4.setFont(new java.awt.Font("Kristen ITC", 0, 36));
		jLabel4.setText("Upgrades");

		jLabel3.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		jLabel3.setText("Kosten für Upgrade:");
		jLabel3.setForeground(new Color(255, 76, 0));
		jLabel3.setBackground(new Color(25,7,0));
		jLabel3.setOpaque(true);
		
		jLabel5.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		jLabel5.setForeground(new Color(255, 76, 0));
		
		
		jLabel6.setFont(new java.awt.Font("Kristen ITC", 0, 14));
		jLabel6.setText("Stabilität:");

		jLabel7.setFont(new java.awt.Font("Kristen ITC", 0, 14));
		jLabel7.setText("Kapazität:");

		jLabel8.setFont(new java.awt.Font("Kristen ITC", 0, 14));
		jLabel8.setText("Handling:");

		jLabel9.setFont(new java.awt.Font("Kristen ITC", 0, 14));
		jLabel9.setText("Geschwindigkeit:");

		jButton2 = setButtonStyle(jButton2, "Hinzufügen", true);
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGap(30)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addComponent(jLabel2, 100, 102, 150)
							.addPreferredGap(ComponentPlacement.RELATED, 455, Short.MAX_VALUE)
							.addComponent(jLabel4)
							.addGap(43))
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
									.addGap(70)
									.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addGap(80)
									.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addComponent(jLabel3)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(jLabel5, 100, 150, 150)))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
								.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
							.addGap(47)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(15))
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(127)
							.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)))
					.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
							.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
							.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
					.addGap(43,43,43))
		);
		jPanel1.setLayout(jPanel1Layout);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap(
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		jButton1_1_1,
																		GroupLayout.PREFERRED_SIZE,
																		117,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		jPanel1,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(256,
																		256,
																		256)
																.addComponent(
																		jLabel1)
																.addGap(0,
																		263,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(37, 37, 37)
						.addComponent(jLabel1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 45,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(37, 37, 37)
						.addComponent(jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(jButton1_1_1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 33,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		pack();
	}

	// Hier werden die Inhalte fuer die Upgradeseite geladen
	private void myInitComponents() {
		if (game != null) {
			trashcar = null;
			selectedRowIndex = -1;
			if (user != null) {
//				UserTest ut = new UserTest(user.getUsername(),
//						user.getCredits(), game);
				// Benutzermüllmenge wird angezeigt
				jLabel2.setFont(new java.awt.Font("Kristen ITC", 0, 20));
				jLabel2.setText(user.getCredits() + " t Müll");
				// Aktuelle Müllwagen des Benutzers wird gesucht
				for (Trashcar t : game.getTrashcars())
					// Wird mit Müllwagen aus der Müllwagenliste abgeglichen
					if (t.getCarname().equals(user.getCar().getCarname()))
						trashcar = t;
				if (trashcar != null) {
					String upgradeName = "";
					int count = 1, index = 1;
					// Hier werden die Upgrades des Müllwagens für die
					// ProgressBars gezählt
					for (int i = 0; i < trashcar.getUpgrades().size(); i++) {
						// Speichern des 1. Upgrades
						if (upgradeName.equals(""))
							upgradeName = trashcar.getUpgrades().get(i)
									.getType();
						// Zähler hochsetzen, wenn Upgradename sich nicht
						// geändert hat
						else if (upgradeName.equals(trashcar.getUpgrades()
								.get(i).getType()))
							count++;
						// Wenn sich der Upgradename geändert hat
						else {
							// Upgrade wird in Tabelle gespeichert
							jTable1.setValueAt(index, index - 1, 0);
							jTable1.setValueAt(upgradeName, index - 1, 1);
							jTable1.setValueAt("Level "
									+ getCurrentUpgradeLevel(upgradeName) + "/"
									+ count, index - 1, 2);
							// ProgressBar wird gefüllt
							setProgressBar(upgradeName, count);
							// Zähler und Upgradename werden geändert
							count = 1;
							index++;
							upgradeName = trashcar.getUpgrades().get(i)
									.getType();
						}

						// Das letzte Upgrade wird in Tabelle geschrieben, da
						// For-Schleife beendet ist und noch nicht in Tabelle
						// geschrieben hat
						if (i == trashcar.getUpgrades().size() - 1) {
							jTable1.setValueAt(index, index - 1, 0);
							jTable1.setValueAt(trashcar.getUpgrades().get(i)
									.getType(), index - 1, 1);
							jTable1.setValueAt("Level "
									+ getCurrentUpgradeLevel(upgradeName) + "/"
									+ count, index - 1, 2);
							setProgressBar(upgradeName, count);
						}
					}
				}
			}
		}
	}

	// Hier wird das aktuelle Upgradelevel des Müllwagens abgefragt
	private int getCurrentUpgradeLevel(String upgr) {
		if (upgr.equals("Motoren-Upgrade"))
			return user.getCar().getEngineupgrade();
		else if (upgr.equals("Kapazitaets-Upgrade"))
			return user.getCar().getVolumeupgrade();
		else if (upgr.equals("Stabilitaets-Upgrade"))
			return user.getCar().getStabilityupgrade();
		else
			return user.getCar().getTiresupgrade();
	}

	// Hier wird die jeweilige ProgressBar gefüllt
	private void setProgressBar(String type, int count) {
		
		if (type.equals("Motoren-Upgrade")){
			
			jPanel1.setGeschwindigkeitHat((getCurrentUpgradeLevel(type) * 100) / count); //der aktuelle Stand auf der Geschwindigkeitsleiste
		}
		else if (type.equals("Reifen-Upgrade")){
			
			jPanel1.setHandlingHat((getCurrentUpgradeLevel(type)*100) / count); //der aktuelle Stand auf der Handlingleiste
		}
		else if (type.equals("Kapazitaets-Upgrade")){
			
			jPanel1.setKapazitätHat((getCurrentUpgradeLevel(type)*100) / count); //der aktuelle Stand auf der Kapazitätsleiste
		}
		else if (type.equals("Stabilitaets-Upgrade")){
	
			jPanel1.setStabilitätHat((getCurrentUpgradeLevel(type) * 100) / count); //der aktuelle Stand auf der Stabilitätsleiste
		}
		
	 jPanel1.repaint();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// Der Zurück-Button wird benutzt und das MainMenu wird aufgerufen
		parent.setVisible(true);
		setVisible(false);
		dispose();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// Der Hinzufügen-Button wird benutzt
		// Hier wird geprüft, ob der Benutzer genug Müll hat, um das Upgrade zu
		// kaufen
		// Ein Upgrade in der Tabelle selektiert?
		if (selectedRowIndex > -1 && selectedRowIndex < 4) {
			if (user != null) {
				// Aktuelle Upgrade-Level geladen
				int upgradeLevel = getCurrentUpgradeLevel(jTable1.getValueAt(
						selectedRowIndex, 1).toString());
				// Über alle Upgrades aus der Upgrade-Liste iterieren
				for (Upgrade upgrade : trashcar.getUpgrades()) {
					// Es wird das nächst höhere Upgrade gesucht
					if (upgrade.getType().equals(
							jTable1.getValueAt(selectedRowIndex, 1))
							&& (upgrade.getRank() - 1) == upgradeLevel) {
						// Hat der Benutzer genug Müll, um das Upgrade zu kaufen
						if (upgrade.getPrice() <= user.getCredits()) {
							// Über alle User aus der User-Liste iterieren
							for (int i = 0; i < game.getUsers().size(); i++) {
								// Aktuelles Benutzerobjekt laden
								if (game.getUsers().get(i).getUsername()
										.equals(user.getUsername())) {
									// Hinzufügen des aktuellen Upgradestandes
									user.setCredits(user.getCredits()
											- upgrade.getPrice());
									Car curCar = user.getCar();
									if (upgrade.getType().equals(
											"Motoren-Upgrade"))
										curCar.setEngineupgrade(curCar
												.getEngineupgrade() + 1);
									else if (upgrade.getType().equals(
											"Reifen-Upgrade"))
										curCar.setTiresupgrade(curCar
												.getTiresupgrade() + 1);
									else if (upgrade.getType().equals(
											"Kapazitaets-Upgrade"))
										curCar.setVolumeupgrade(curCar
												.getVolumeupgrade() + 1);
									else if (upgrade.getType().equals(
											"Stabilitaets-Upgrade"))
										curCar.setStabilityupgrade(curCar
												.getStabilityupgrade() + 1);
									user.setCar(curCar);

									game.getUsers().set(i, user);
									// Abspeichern des neuen Standes in der
									// XML-Datei
									XMLParser cr = new XMLParser();
									cr.createXMLFile(game);
									break;
								}
							}
						}
					}
				}
				// Neuen Stand in der Oberfläche anzeigen
				myInitComponents();
			}
		}
	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// Auswählen eines Objektes in der Tabelle
		String type = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
		selectedRowIndex = jTable1.getSelectedRow();

		// Hier muss der Code zum Laden des Fortschrittes in der ProgressBar
		// implementiert werden
		jPanel1.setGeschwindigkeitBekommt(0);
		jPanel1.setHandlingBekommt(0);
		jPanel1.setKapazitätBekommt(0);
		jPanel1.setStabilitätBekommt(0);
		
		if(selectedRowIndex == 0 && getCurrentUpgradeLevel(type) < 12) //Wenn das erste Element in der Upgradeliste angeklickt wird und nicht das maximale Upgrade schon vorhanden ist, dann wird der Fortschritt für das nächste Upgradelevel angezeigt
			jPanel1.setGeschwindigkeitBekommt(((getCurrentUpgradeLevel(type)+1) * 100) / 12);
		else if(selectedRowIndex == 1 && getCurrentUpgradeLevel(type) < 18) //Wenn das zweite Element in der Upgradeliste angeklickt wird und nicht das maximale Upgrade schon vorhanden ist, dann wird der Fortschritt für das nächste Upgradelevel angezeigt
			jPanel1.setHandlingBekommt(((getCurrentUpgradeLevel(type)+1) * 100) / 10);
		else if(selectedRowIndex == 2 && getCurrentUpgradeLevel(type) < 8) //Wenn das dritte Element in der Upgradeliste angeklickt wird und nicht das maximale Upgrade schon vorhanden ist, dann wird der Fortschritt für das nächste Upgradelevel angezeigt
			jPanel1.setKapazitätBekommt(((getCurrentUpgradeLevel(type)+1) * 100) / 8);
		else if(selectedRowIndex == 3 && getCurrentUpgradeLevel(type) < 12) //Wenn das vierte Element in der Upgradeliste angeklickt wird und nicht das maximale Upgrade schon vorhanden ist, dann wird der Fortschritt für das nächste Upgradelevel angezeigt
			jPanel1.setStabilitätBekommt(((getCurrentUpgradeLevel(type)+1) * 100) / 10);
		
		jPanel1.repaint();

		
		
		int count = 0;
		for (Upgrade upgrade : trashcar.getUpgrades()) {
			if (upgrade.getType().equals(type)
					&& (upgrade.getRank() - 1) == getCurrentUpgradeLevel(type)) {
				jLabel3.setText("Kosten für Upgrade: " + String.valueOf(upgrade.getPrice() + " t Müll"));
				//jLabel5.setText(String.valueOf(upgrade.getPrice() + " t Müll"));
				//jLabel5.setBackground(new Color(25,7,0));
				//jLabel5.setOpaque(true);
				//jLabel5.setHorizontalAlignment( SwingConstants.CENTER );
				
			}
		}
	}

	// Hier wird der ButtonStyle gesetzt
	private JButton setButtonStyle(JButton b, String text, boolean enabled) {
		b.setFont(new java.awt.Font("Kristen ITC", 0, 17));
		b.setForeground(new Color(255, 76, 0));
		b.setText(text);
		b.setBackground(new Color(25, 7, 0));
		b.setEnabled(enabled);
		return b;
	}
}
