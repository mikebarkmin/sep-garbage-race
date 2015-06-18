package helper;

/**
 *
 * @author mike
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menupresentation.GameScreen;
import menupresentation.HighscoreList;
import menupresentation.MainMenu;
import entities.game.Ghost;
import entities.game.Landscape;
import entities.game.Player;
import entities.game.Road;
import entities.xml.Entry;
import entities.xml.Game;
import entities.xml.Ghostmode;
import entities.xml.Level;
import entities.xml.User;

public class Animator extends Canvas implements Runnable {

	public Thread animator;
	private Game game;
	private JFrame parent;
	private boolean playing; // Die Variable gibt an, ob der Spieler spielt oder
								// fertig gefahren ist.
	private boolean pausing;
	private Player player;
	private Ghost ghost;
	private boolean ghostmodus = true;
	private Landscape landscape;
	private User user;
	private Road road; // Hier wird die Strecke hineingeladen.
	private Color borderStart; // Gibt die Startfarbe der Streckenmarkierung an.
	private Color divisionStart; // Gibt die Startfarbe der Streckentrennlinie
									// an.
	private int trashCollected; // Gibt an wie viel Mülleingesammelt wurde.
	public Timer timer = new Timer(); // Gibt die Fahrzeit an.
	private int[] roadMap;
	private int[] landscapeMap;
	private boolean testImage = false;
	private Color[] gameColors;
	private String levelName;

	// Beenden Men�
	private javax.swing.JDialog finishMenu;
	public JLabel finishLabel;
	private JLabel highscoreLabel;
	private JPanel finishPanel;
	private JButton viewHighscores;
	private JButton tryagain;
	private HighscoreList highscoreView;
	private GameScreen gameView;

	private float playerMaxSpeed;
	private float playerMaxAccel;
	private int nitroFuel = 0;

	private List<Ghostmode> ghostmodeList = new ArrayList<Ghostmode>();
	private List<Ghostmode> ghostmodeListLoad;
	private int countFrame = 0;

	private boolean showFps = false;
	private long firstFrame;
	private int frames;
	private long currentFrame;
	private int fps;

	public Animator(JFrame parent, Game game, int[] roadMap,
			int[] landscapeMap, User user, Color[] gameColors, String levelName, boolean ghostmodus) {
		this.user = user;
		this.roadMap = roadMap;
		this.parent = parent;
		this.landscapeMap = landscapeMap;
		this.ghostmodus = ghostmodus;
		this.gameColors = gameColors;
		this.game = game;
		this.levelName = levelName;
		animator = null;
		playing = false;
		pausing = false;
		start();
	}

	// Mit dieser Funktion wir der Animator gestartet.
	private void start() {
		// Das Spiel soll nur gestartet werden, wenn es noch keinen Animator
		// gibt und wenn gerade nicht gespielt wird.
		if (animator == null && !playing) {
			// Zuerst wird ein neuer Thread für den Animator erstellt und
			// danach wird dieser gestartet.
			animator = new Thread(this);
			animator.start();
			// Der Spieler spielt
			playing = true;
			// Die Stoppuhr wird gestartet.
			timer.start();
		}
	}

	// Mit dieser Funktion wir der Animator gestoppt.
	private void stop() {
		// Der Spieler spielt nicht
		playing = false;
		// Die Stoppuhr wird angehalten.
		timer.setRunning(false);
	}

	// Jetzt wird der Animator initialisiert.
	private void init() {
		// Der Wagen wir geladen. In der Klasse Player habe ich erklärt warum
		// gerade 9 Frames.
		// Im Moment nur mit einer Dummygrafik, da meine künstlerischen
		// Fähigkeiten nicht so gut sind.
		player = new Player(9,user);
		player.addFrame(1, "images/garbagetruck1.png");
		player.addFrame(2, "images/garbagetruck2.png");
		player.addFrame(3, "images/garbagetruck3.png");
		player.addFrame(4, "images/garbagetruck1.png");
		player.addFrame(5, "images/garbagetruck2.png");
		player.addFrame(6, "images/garbagetruck3.png");
		player.addFrame(7, "images/garbagetruck1.png");
		player.addFrame(8, "images/garbagetruck2.png");
		player.addFrame(9, "images/garbagetruck3.png");

		
		// Der Wagen wird dann auf den Bildschirm platziert.
		// Horizontal in der Mitte
		player.setX(GameScreen.WIDTH / 2 - Player.WIDTH / 2);
		// Vertikal ca. 20 Pixel �ber dem Bildschirmrand.
		player.setY(GameScreen.HEIGHT - Player.HEIGTH - 20);
		// On ist eine Funktion aus der Klasse Sprite, die den Sprite player
		// aktiviert.
		player.on();

		// Jetzt initialisieren wir den Hintergrund oder die Landschaft
		landscape = new Landscape(landscapeMap);
		// Danach inistalisieren wir die Strecke
		road = new Road(roadMap, gameColors, levelName);
		// Hier definieren wir die Anfangsfarbe für die Streckenmarkierung
		borderStart = gameColors[3];
		// Hier definieren wir die Anfangsfarbe für die Streckentrennlinien
		divisionStart = gameColors[5];
		// Hier definieren wir nach wie vielen Schritten sich die Farbe der
		// Streckenmarkierung ändern soll.
		road.setBorderChange(30);
		// Hier setzten wir jetzt unsere Anfangsfarbe
		road.setBorderColor(borderStart);
		// Hier setzten wir unsere Anfangstrennlinienfarbe
		road.setDivisionColor(divisionStart);
		road.initSurrounding();
		if (ghostmodus) {
			for (int i = 0; i < game.getLevels().size(); i++) {
				if (game.getLevels().get(i).getName() == levelName) {
					ghostmodeListLoad = game.getLevels().get(i).getHighscore()
							.getGhostmode();
				}
			}
			ghost = new Ghost(1,road,ghostmodeListLoad);
			ghost.addFrame(1, "images/garbagetruckghost.png");

			ghost.setX(GameScreen.WIDTH / 2 - Player.WIDTH / 2);
			// Vertikal ca. 20 Pixel �ber dem Bildschirmrand.
			ghost.setY(GameScreen.HEIGHT - Player.HEIGTH - 20);
			// On ist eine Funktion aus der Klasse Sprite, die den Sprite player
			// aktiviert.
			ghost.on();
		}
		// Beieinflussung der Strecke aufs Fahrverhalten
		List<Level> levelList = game.getLevels();
		for (int i = 0; i < levelList.size(); i++) {
			Level levelTmp = levelList.get(i);
			if (levelTmp.getName() == levelName) {

				// F�r das Sandlevel
				if (levelTmp.getSurface().equalsIgnoreCase("Sand")) {
					// Wie die Maximalgeschwindigkeit durch die Sandstrecke
					// beeinflusst wird. Die Maximalgeschwindigkeit ohne
					// Upgrades ist auf
					// 100.0f gesetzt
					player.setMaxSpeed(player.getMaxSpeed());
					// Wie die Beschleunigung durch die Sandstrecke beeinflusst
					// wird. Die Beschleunigung ist ohne Upgrades auf 1.0f
					// gesetzt
					player.setAccel(player.getAccel() - 0.2f); // Die
																// Beschleunigung
																// ist
																// niedriger, da
																// die Reifen im
																// Sand
																// durchdrehen
																// k�nnen
					// Wie die Lenkung nach links beeinflusst wird. SteerLeft
					// ist ohne Upgrades auf int +10.0f gesetzt
					player.setSteerLeft(player.getSteerLeft() - 4f);// Die
																	// Lenkung
																	// ist
																	// erschwert
					// Wie die Lenkung nach links beeinflusst wird. SteerLeft
					// ist ohne Upgrades auf -10.0f gesetzt
					player.setSteerRight(player.getSteerRight() + 4f); // Die
																		// Lenkung
																		// ist
																		// erschwert
					// Wie die Bremskraft beim dr�cken der Bremstaste durch die
					// Sandstrecke beeinflusst wird. Die Bremskraft ist ohne
					// Upgrades auf 4.0f
					// gesetzt
					player.setBrake(player.getBrake() - 1.0f); // Die
																// Bremswirkung
																// ist auch
																// verringert
					// Wie die Motorbremskraft durch die Sandstrecke beeinflusst
					// wird. Die Motorbremskraft ist ohne Upgrades auf 1.0f
					// gesetzt
					player.setMotorbrake(player.getMotorbrake());

				}

				// F�r das Asphaltlevel
				if (levelTmp.getSurface().equalsIgnoreCase("Asphalt")) {
					// Wie die Maximalgeschwindigkeit durch die Asphaltstrecke
					// beeinflusst wird. Die Maximalgeschwindigkeit ohne
					// Upgrades ist auf
					// 100.0f gesetzt
					player.setMaxSpeed(player.getMaxSpeed() - 10f); // Die
																	// Maximalgeschwindigkeit
																	// ist
																	// niedriger,
																	// weil man
																	// in einer
																	// Stadt
																	// f�hrt und
																	// nachts
																	// vorsichtig
																	// fahren
																	// muss ;)
					// Wie die Beschleunigung durch die Asphaltstrecke
					// beeinflusst
					// wird. Die Beschleunigung ist ohne Upgrades auf 1.0f
					// gesetzt
					player.setAccel(player.getAccel() - 0.1f); // Die
																// Beschleunigung
																// ist auch
																// niedriger
																// wegen des
																// Stadtverkehrs
																// und der
																// Dunkelheit.
					// Wie die Lenkung nach links beeinflusst wird. SteerLeft
					// ist ohne Upgrades auf int +10.0f gesetzt
					player.setSteerLeft(player.getSteerLeft());
					// Wie die Lenkung nach links beeinflusst wird. SteerLeft
					// ist ohne Upgrades auf -10.0f gesetzt
					player.setSteerRight((player.getSteerRight()));
					// Wie die Bremskraft beim dr�cken der Bremstaste durch die
					// Asphaltstrecke beeinflusst wird. Die Bremskraft ist ohne
					// Upgrades auf 4.0f
					// gesetzt
					player.setBrake(player.getBrake());
					// Wie die Motorbremskraft durch die Asphaltstrecke
					// beeinflusst
					// wird. Die Motorbremskraft ist ohne Upgrades auf 1.0f
					// gesetzt
					player.setMotorbrake(player.getMotorbrake());

				}

				// F�r das Landstrasselevel
				if (levelTmp.getSurface().equalsIgnoreCase("Landstrasse")) {
					// Wie die Maximalgeschwindigkeit durch die
					// Landstrassestrecke
					// beeinflusst wird. Die Maximalgeschwindigkeit ohne
					// Upgrades ist auf
					// 100.0f gesetzt
					player.setMaxSpeed(player.getMaxSpeed());
					// Wie die Beschleunigung durch die Landstrassestrecke
					// beeinflusst
					// wird. Die Beschleunigung ist ohne Upgrades auf 1.0f
					// gesetzt
					player.setAccel(player.getAccel());
					// Wie die Lenkung nach links beeinflusst wird. SteerLeft
					// ist ohne Upgrades auf int +10.0f gesetzt
					player.setSteerLeft(player.getSteerLeft());
					// Wie die Lenkung nach links beeinflusst wird. SteerLeft
					// ist ohne Upgrades auf -10.0f gesetzt
					player.setSteerRight((player.getSteerRight()));
					// Wie die Bremskraft beim dr�cken der Bremstaste durch die
					// Landstrassestrecke beeinflusst wird. Die Bremskraft ist
					// ohne Upgrades auf 4.0f
					// gesetzt
					player.setBrake(player.getBrake());
					// Wie die Motorbremskraft durch die Landstrassestrecke
					// beeinflusst
					// wird. Die Motorbremskraft ist ohne Upgrades auf 1.0f
					// gesetzt
					player.setMotorbrake(player.getMotorbrake());

				}
			}
		}
		// Uprades
		// Motorenupgrade
		player.setMaxSpeed(player.getMaxSpeed() + 4.2f
				* user.getCar().getEngineupgrade()); // 4.2f damit man mit
														// Upgrade 12 auf ca
														// 150km/h kommt
		if (user.getCar().getEngineupgrade() % 2 == 0) { // Die Beschleunigung
															// wird nur bei
															// jedem 2ten
															// Motorenupgrade
															// verbessert.
			player.setAccel(player.getAccel() + 0.0416666667f
					* user.getCar().getEngineupgrade()); // 0.0416666667f damit
															// man mit Upgrade
															// 12 auf eine
															// Beschleunigung
															// mit ca 1.5f kommt
		}

		// Reifenupgrade
		player.setSteerLeft(player.getSteerLeft() + 0.5f
				* user.getCar().getTiresupgrade()); // Man kommt mit dem
													// h�chsten Upgrade 18 auf
													// eine Lenkung von 19
		player.setSteerRight(player.getSteerRight() - 0.5f
				* user.getCar().getTiresupgrade()); // Man kommt mit dem
													// h�chsten Upgrade 18 auf
													// eine Lenkung von 19

		// Volumenupgrade
		player.setCapacity(player.getCapacity() + 2
				* user.getCar().getVolumeupgrade()); // Startkapazit�t ist 15.
														// Es gibt 8 Upgrades,
														// dh man kommt am Ende
														// auf eine Kapazit�t
														// von 32

		// Stabilit�tsupgrade
		player.setCollideForce(player.getCollideForce() - 1.0f
				* user.getCar().getStabilityupgrade()); // Startupgrade: Man
														// wird bei einer
														// Kollision mit einem
														// kleinen Hindernis um
														// 20km/h langsamer.
														// Endupgrade: Man wird
														// um 10km/h langsamer.

		playerMaxSpeed = player.getMaxSpeed();
		playerMaxAccel = player.getAccel();
	}

	// Mit dieser Funktion animieren wir den Hintergrund
	private void updateLandscape() {
		// Der Hintergrund soll nur bewegt werden, wenn der Spieler auch
		// beschleunigt.
		if (!player.isAccelerating()) {
			// return;
		}
		if (player.getSpeed() > 1.0f) {
			int xPos = road.getRoadOffset();
			float delta = player.getSpeed() / (player.getMaxSpeed() / 2); // 0 <
			// delta
			// < 2
			delta *= -1; // -2 < delta < 0
			delta += 4 - 0.8; // 3 < delta < 5
			landscape.scroll((int) (-xPos / delta));
		}
	}

	// Die Funktion animiert die Strecke
	private void updateRoad() {
		int xPos, y;

		y = road.getBorderChange();

		// Wir wollen, dass die Strecke sich nur bewegt, wenn der Spiel
		// beschleunigt oder lenkt.
		if (player.getSpeed() > 1.0f) {
			// Wie schnell die Strecke sich bewegt, hängt natürlich von der
			// momentanen Geschwindigkeit des Spielers ab.
			y -= player.getSpeed() / 10.0f;

			// Jetzt wird berechnet wie sich die Strecke bewegen soll. Dazu
			// müssen wir ein Delta berechnen, welches die Streckenänderung
			// angeben soll.
			float delta = player.getSpeed() / (player.getMaxSpeed() / 2); // 0 <
																			// delta
																			// <
																			// 2
			delta *= -1; // -2 < delta < 0
			delta += 4 - 0.8; // 3 < delta < 5
			xPos = road.getRoadOffset();
			road.setScroll((int) (xPos / delta)); // Wenn die Geschwindigkeit
													// maximal ist, dann hat das
													// Delta eine Wert von 3
			// Das heißt wir teilen durch die kleinst mögliche Zahl für
			// Delta, sodass die Strecke die größte Bewegung erfährt.
			// Angenommen unsere Geschwindigkeit wäre minimal. Dann würden wir
			// ein sehr großes Delta erhalten.
			// player.getSpeed()/(Player.MAX_SPEED)/2 > 1/300
			// delta = 1501/300 ungefähr 5
			// Daraus folgt die Bewegung der Strecke wäre kleiner als im ersten
			// Fall und so sollte es auch sein.
		}

		if (y < 0) {
			borderStart = (road.getBorderColor() == gameColors[3]) ? gameColors[4]
					: gameColors[3];
			divisionStart = (road.getDivisionColor() == gameColors[5]) ? gameColors[2]
					: gameColors[5];
			y = 30;
		}

		road.setBorderColor(borderStart);
		road.setDivisionColor(divisionStart);
		road.setBorderChange(y);

		// Jetzt müssen wir noch die Distanz die der Spieler zurückgelegt hat
		// berechnen und dann der Strecke übermitteln.
		float delta = player.getSpeed() / 480;
		road.setDistance(road.getDistance() + delta);
	}

	private void updateGhost() {
		/*if (ghostmodusFrame < ghostmodeList.size()) {
			ghost.setX(ghostmodeList.get(ghostmodusFrame).getX()); //durch roadScroll ersetzen
		
			float scaleFactor = (-road.getDistance() + ghostmodeList.get(
					ghostmodusFrame).getDistance())/50;
			ghost.setScale(ghost.getScale()*scaleFactor);
			if (ghostmodeList.get(ghostmodusFrame).getDistance() - road.getDistance() > GameScreen.roadHeight) {
				ghost.off();
			} else if (ghost.getScale() < 0) {
				ghost.off();
			} else if (ghost.getScale() > 1) {
				ghost.off();
			} else {
				ghost.on();
			}
		
			//ghost.setScale(1 - (-road.getDistance() + ghostmodeList.get(ghostmodusFrame).getDistance()));
			//ghost.setScale(0.5f);
			ghost.setWidth((int) (ghost.getW() * ghost.getScale()));
			ghost.setHeight((int) (ghost.getH() * ghost.getScale()));
			ghostmodusFrame++;
		} else {
			ghost.off();
		}*/
		// ghost.setScale(ghost.getScale());
		// ghost.setHeight(ghost.getHeight());
		// ghost.setWidth(ghost.getWidth());
	}

	private void updatePlayer() {
		// Geschwindigkeit neu berechnen
		if (player.isAccelerating() && player.getNitro()) {
			player.setSpeed(player.getSpeed() + player.getAccel() * 2);
		} else if (player.isAccelerating()) {
			player.setSpeed(player.getSpeed() + player.getAccel());
		} else {
			player.setSpeed(player.getSpeed() - player.getMotorbrake());
		}

		if (player.isBraking()) {
			player.setSpeed(player.getSpeed() - player.getBrake());
		}

		// Wenn man auf das Gras fährt, wird man langsamer.
		int pos = road.getScroll();
		int half_road = GameScreen.WIDTH / 2;
		int half_player = Player.WIDTH / 2;
		if (pos < (-half_road + half_player) || pos > (half_road - half_player)) {
			// Geschwindikeit des Player, wenn er �ber das Gras f�hrt.
			player.setSpeed(player.getSpeed() - 80.0f);
		}

		// Überprüfen ob der Spieler angehalten hat.
		if (player.getSpeed() < 1.0f) {
			if (player.isBraking()) {
				player.selFrame(Player.BRAKE_NONE);
			} else {
				player.selFrame(Player.STEER_NONE);
			}
			return;
		}

		// Die Lenkrichtung wechseln
		int steerDirection = player.getSteerDirection();
		int frame = player.getFrame();
		switch (steerDirection) {
		// Die nächsten Funktionen beschreiben was passiert, wenn der Spieler
		// lenkt
		// Die Bewegungsrichtung der Strecke wird geändert und es wir ein
		// anderer Frame für den Wagen angezeigt.
		case Player.STEER_LEFT:
			if (player.isBraking()) {
				player.selFrame(Player.BRAKE_LEFT);
			} else {
				if (frame == Player.STEER_LEFT) {
					player.selFrame(Player.STEER_LEFT2);
				} else {
					player.selFrame(Player.STEER_LEFT);
				}
			}
			road.setScroll((int) player.getSteerLeft());
			break;

		case Player.STEER_RIGHT:
			if (player.isBraking()) {
				player.selFrame(Player.BRAKE_RIGHT);
			} else {
				if (frame == Player.STEER_RIGHT) {
					player.selFrame(Player.STEER_RIGHT2);
				} else {
					player.selFrame(Player.STEER_RIGHT);
				}
			}
			road.setScroll((int) player.getSteerRight());
			break;

		case Player.STEER_NONE:
			if (player.isBraking()) {
				player.selFrame(Player.BRAKE_NONE);
			} else {
				if (player.getFrame() == Player.STEER_NONE) {
					player.selFrame(Player.STEER_NONE2);
				} else {
					player.selFrame(Player.STEER_NONE);
				}
			}
			break;
		}

		ghostmodeList.add(new Ghostmode(road.getDistance(),
				road.getScroll(),countFrame));
	}

	// Hier wird das Programm zum Laufen bebracht. Run ist dabei eine Funktion
	// vom Inferface Runnable.
	public void run() {

		// Init ruft die Funktion von oben auf, die alle Objekte lädt.
		init();

		while (playing) {
			updateLandscape();
			updateRoad();
			updatePlayer();
			updateGhost();
			repaint();
			checkFinish();

			// Das wurde ja schon in der Präsenzstunde erklärt.
			// Wir müssen dem Computer etwas Zeit geben, die vorherigen Thread
			// abzuarbeiten bzw. aus dem Speicher zu löschen.
			// Dazu versuchen wir den Thread für ein sehr kurze Weile pausieren
			// zu lassen.
			try {
				Thread.sleep(40);
			} // Damit wir einen genauen Überblick haben was passiert, wird
				// hier ausgegben warum der Thread nicht pausieren konnte.
			catch (InterruptedException ex) {
				System.out.println(ex.toString());
			}
		}
	}

	// Hier wird überprüft, ob der Wagen die Ziellinie überquert hat.
	public boolean checkFinish() {
		if (road.getDistance() + 1 >= road.getROAD_LENGTH()) {
			stop();
			generateFinishMenu();
			finishMenu.setVisible(true);
			playing = false;
		}
		return road.getDistance() + 1 >= road.getROAD_LENGTH();
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
	public void generateFinishMenu() {
		finishPanel = new JPanel();
		tryagain = new JButton("Nochmal fahren");
		viewHighscores = new JButton("Bestenliste anzeigen");
		finishLabel = new JLabel("-Rennen vorbei-");
		highscoreLabel = new JLabel("-Dein Highscore-");
		setButtonStyle(tryagain, "Nochmal fahren", true);
		setButtonStyle(viewHighscores, "Bestenliste anzeigen", true);
		tryagain.setBounds(new java.awt.Rectangle(400, 250, 300, 165));
		finishLabel.setBounds(new java.awt.Rectangle(400, 250, 300, 165));
		finishLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
		highscoreLabel.setBounds(new java.awt.Rectangle(400, 250, 300, 165));
		highscoreLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
		finishMenu = new javax.swing.JDialog();
		// pauseMenu.setUndecorated(true);
		finishMenu.setAlwaysOnTop(true);
		finishMenu.setBounds(new java.awt.Rectangle(300, 200, 400, 300));
		finishMenu.setMinimumSize(new java.awt.Dimension(400, 300));
		finishMenu.setModal(true);
		finishMenu.setResizable(false);
		finishMenu.setContentPane(new BackGroundPane("images/Hintergrund/Hintergrund3.png"));
		finishPanel.setLayout(new BoxLayout(finishPanel, BoxLayout.PAGE_AXIS));
		finishLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		highscoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		viewHighscores.setAlignmentX(Component.CENTER_ALIGNMENT);
		tryagain.setAlignmentX(Component.CENTER_ALIGNMENT);
		finishPanel.add(Box.createRigidArea(new Dimension(15, 55)));
		finishPanel.add(finishLabel);
		finishPanel.add(Box.createRigidArea(new Dimension(15,5)));
		finishPanel.add(highscoreLabel);
		finishPanel.add(Box.createRigidArea(new Dimension(15, 15)));
		finishPanel.add(viewHighscores);
		finishPanel.setOpaque(false);
		viewHighscores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenu mainmenu = new MainMenu();
				highscoreView = new HighscoreList(mainmenu, user, game);
				
				highscoreView.setVisible(true);
				finishMenu.setVisible(false);
				finishMenu.dispose();
				parent.setVisible(false);
				parent.dispose();
			}
		});
		finishPanel.add(Box.createRigidArea(new Dimension(15, 15)));
		finishPanel.add(tryagain);
		tryagain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameView = new GameScreen(parent, user, game, roadMap,
						landscapeMap, gameColors, levelName, ghostmodus);
				gameView.setVisible(true);
				finishMenu.setVisible(false);
				finishMenu.dispose();
				parent.setVisible(false);
				parent.dispose();
			}
		});
		checkHighscore();
		commitCollectedTrash();
		finishMenu.add(finishPanel);
	}

	private void commitCollectedTrash() {
		for (int i = 0; i < game.getUsers().size(); i++) {
			// Aktuelles Benutzerobjekt laden
			if (game.getUsers().get(i).getUsername().equals(user.getUsername())) {

				user.setCredits(user.getCredits() + player.getTrashCollected());
				game.getUsers().set(i, user);
				XMLParser cr = new XMLParser();
				cr.createXMLFile(game);
			}
		}
	}

	// Hier wird �berpfr�ft ob man es in die Highscoreliste geschafft hat, wenn
	// ja wird man eingetragen.
	private void checkHighscore() {
		List<Level> levelList = game.getLevels();
		for (int i = 0; i < levelList.size(); i++) {
			Level levelTmp = levelList.get(i);
			if (levelTmp.getName() == levelName) {
				for (int p = 0; p < levelTmp.getHighscore().getEntries().size(); p++) {
					String timeHighscoreTmp = levelTmp.getHighscore()
							.getEntries().get(p).getTime();
					timeHighscoreTmp = timeHighscoreTmp.replace(":", "");
					int timeHighscore = new Integer(timeHighscoreTmp);
					String timeUserTmpRaw = getTimer().getTime();
					String timeUserTmp = timeUserTmpRaw.replace(":", "");
					int timeUser = new Integer(timeUserTmp);

					if (timeHighscore > timeUser) {
						Entry entryTmp = new Entry();
						entryTmp.setTime(timeUserTmpRaw);
						entryTmp.setUsername(user.getUsername());
						levelTmp.getHighscore().getEntries().add(p, entryTmp);
						if (p == 0) {
							levelTmp.getHighscore().setGhostmode(ghostmodeList);
						}
						break;
					}
				}
				if (levelTmp.getHighscore().getEntries().size() == 11) {
					levelTmp.getHighscore().getEntries().remove(10);
				}
			}
		}
		XMLParser cr = new XMLParser();
		cr.createXMLFile(game);
	}

	// Hier werden die KeyHandler abgearbeitet.
	// Einmal für das drücken
	public void onKeyPressed(int key) {
		// SKeyTest keyTest = new KeyTest(key);
		switch (key) {
		case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
			player.setSteerDirection(Player.STEER_LEFT);
			break;

		case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
			player.setSteerDirection(Player.STEER_RIGHT);
			break;

		case KeyEvent.VK_UP: case KeyEvent.VK_W:
			player.accelerate();
			break;

		case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
			player.brake(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setNitro(true);
			break;
		case KeyEvent.VK_N:
			player.setNitroFuel(190);
			nitroFuel = player.getNitroFuel();
			break;
		case KeyEvent.VK_R:
			road.setScroll(-road.getScroll());
			player.setSpeed(0);
			break;
		case KeyEvent.VK_F:
			if (showFps) {
				showFps = false;
			

			} else {
				showFps = true;
				

			}
		}
	}

	// Und einmal für das Loslassen
	public void onKeyReleased(int key) {
		switch (key) {
		case KeyEvent.VK_SPACE:
			player.setNitro(false);
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_A:
		case KeyEvent.VK_D:
			player.setSteerDirection(Player.STEER_NONE);
			break;

		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			player.deaccelerate();
			break;

		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			player.brake(false);
			break;
		

		}
	}

	// Jetzt müssen wir die Funktion paint von Canvas überschreiben und unsere
	// Funktionen hinzufügen.
	public void hudDraw(Graphics2D g2) {
		// Mit dieser Zeile legen wir die Schriftart und Größe fest.
		Font font = new Font("Tahoma", Font.BOLD, 16);
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		// Danach zeichnen wir Strings auf den Bildschirm
		g2.drawString(player.getTrashCollected() + " t", 50, 38);
		g2.drawString("Zeit: " + getTimer().getTime(), 640, 38);
		g2.drawString(Math.round(getPlayer().getSpeed()) + " km/h", 15, 585);
		frames++;
		currentFrame = System.currentTimeMillis();
		if (currentFrame > firstFrame + 1000) {
			firstFrame = currentFrame;
			fps = frames;
			frames = 0;
		}
		// Wird nur gezeichnet wenn f gedr�ckt wird.
		if (showFps) {

			// Zum Testen werden Rechtecke um die Objekte gezeichnet
			// g2.drawRect(player.getX1(), player.getY1(), player.getW1(),
			// player.getH1());
			// g2.drawRect(road.getSurroundingBarrier().getX(),
			// road.getSurroundingBarrier().getY() - (int)
			// (road.getSurroundingBarrier().getH() *
			// road.getSurroundingBarrier().getScale() / 100.0f), (int)
			// (road.getSurroundingBarrier().getW() *
			// road.getSurroundingBarrier().getScale() / 100.0f), (int)
			// (road.getSurroundingBarrier().getH() / 1.2 *
			// road.getSurroundingBarrier().getScale() / 100.0f));
			g2.drawString(fps + " fps", 400, 300);
			// Collsion Box

			g2.drawString(" Verblieben: " + Math.round(getRoad().getDistance())
					+ " " + getRoad().getROAD_LENGTH(), 260, 580);
		}
		// Jetzt laden wir noch die Ladevolumenanzeige.
		g2.fillRect(582, 562, 192, 24);
		g2.setColor(Color.GREEN);
		nitroFuel = player.getNitroFuel();
		g2.fillRect(583, 563, nitroFuel, 22);
		// g2.setColor(Color.RED);
		// g2.fillRect(583, 563, 20, 22);

		g2.drawImage(
				Toolkit.getDefaultToolkit().getImage("images/trashicon.png"),
				10, 15, this);

		// Wenn nicht mehr gespielt wird, also der Wagen das Ziel erreicht hat.
		if (!isPlaying() && !isPausing()) {
			// Dann soll ein WINNER String in der Mitte des Bildschirms
			// angezeigt werden.
			Color color = new Color(1, 0, 0, 60);
			g2.setPaint(color);
			g2.fillRect(0, 0, 800, 600);
			finishLabel.setText("Super " + user.getUsername() + "!"); 
			highscoreLabel.setText(" Du hast " + getTimer().getTime() + " gebraucht");
		}
	}

	public void paint(Graphics g) {
		// Zuerst machen wir es wie in der Präsenzstunde erklärt
		// Wir legen ein BufferedImage an, dass quasi das Spiel nochmal lädt.
		// So können wir aber sicher sein, dass ein flüssiger Spielablauf
		// gewährleistet ist.
		BufferedImage backbuffer = new BufferedImage(GameScreen.WIDTH,
				GameScreen.HEIGHT, BufferedImage.TYPE_INT_RGB);
		// Das BufferedImage speichern wir jetzt in einer Graphics2D
		Graphics2D g2 = (Graphics2D) backbuffer.getGraphics();

		// Zuerst überschreiben wir die Flächen.
		g2.setColor(gameColors[0]);
		g2.fillRect(0, 0, GameScreen.WIDTH, GameScreen.skyHeight);
		g2.setColor(gameColors[1]);
		g2.fillRect(0, GameScreen.skyHeight, GameScreen.WIDTH,
				GameScreen.roadHeight);

		// Danach zeichnen wir den Hintergrund
		landscape.draw(g2);

		// Danach die Straße
		road.draw(g2);

		road.drawSurrounding(g2, player);

		// Jetzt zeichen wir den Ghost
		if (ghostmodus) {
		ghost.drawGhost(g2);
		}
		// Danach den Wagen
		player.drawUpgrade(g2);

		// Das Hud zeichnen
		hudDraw(g2);

		if (pausing) {
			// Bildschirm abdunkeln
			Color color = new Color(1, 0, 0, 60);
			g2.setPaint(color);
			g2.fillRect(0, 0, 800, 600);
			// Pause auf dem Bildschirm ausgeben
			// g2.setColor(Color.WHITE);
			// g2.drawString("PAUSE", 400 - 40, 300);

			// Prozesse pausieren
			animator.suspend();
			timer.suspend();
		} else {
			// Prozesse wieder aufnehmen
			animator.resume();
			timer.resume();
		}

		/*
		 * if (player.collideSurrounding(road.getSurroundingBarrier()) &&
		 * road.getSurroundingBarrier().isActive()) { //Geschwindigkeit des
		 * Spielers auf 0 setzten. player.setSpeed(0.0f); //Zur�cksetzten in die
		 * Stra�enmitte. road.setScroll(-road.getScroll());
		 * road.getSurroundingBarrier().off(); }
		 */

		// Zuletzt zeichnen wir den Backbuffer
		g.drawImage(backbuffer, 0, 0, this);
	}

	// Jetzt überschreiben wir noch die update Methode aus der Klasse Canvas.
	// Die sorgt dafür, dass wir immer ein neues Bild angezeigt bekommen.
	public void update(Graphics g) {
		countFrame++;
		paint(g);

	}

	public boolean isPausing() {
		return pausing;
	}

	public void setPausing(boolean pausing) {
		this.pausing = pausing;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public int getTrashCollected() {
		return trashCollected;
	}

	public void setTrashCollected(int trashCollected) {
		this.trashCollected = trashCollected;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Thread getAnimator() {
		return animator;
	}

	public Player getPlayer() {
		return player;
	}

	public Road getRoad() {
		return road;
	}

}