package menupresentation;

import helper.Animator;
import helper.BackGroundPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.xml.Game;
import entities.xml.User;

/**
 * 
 * @author mike
 */
public class GameScreen extends JFrame {

	private Animator animatorCanvas;
	// Hier wird die GrÃ¶ÃŸe des Fensters festgelegt. Ich hatte mich mit Andre
	// drauf verstÃ¤ndigt, dass wir 800*600 nehmen.
	final public static int WIDTH = 800;
	final public static int HEIGHT = 600;
	private User user;
	// Gibt das VerhÃ¤ltnis vom Himmel zur Strecke an.
	public static int skyHeight = 340;
	public static int roadHeight = 260;
	// PauseMenü
	private javax.swing.JDialog pauseMenu;
	private JLabel pauseLabel;
	private JButton resumeRace;
	private JButton endRace;
	private boolean pausing = false;
	private JPanel pausePanel;
	//Variabeln für die Erstellung der Strecke
	private JFrame parent;
	private int[] roadMap;
	private int[] landscapeMap;
	private Color[] gameColors;
	private Game game;
	private String levelName;
	private boolean ghostmodus;

	public GameScreen(JFrame parent, User user, Game game,int[] roadMap, int[] landscapeMap,Color[] gameColors,String levelName, boolean ghostmodus) {
		this.user = user;
		this.gameColors = gameColors;
		this.roadMap = roadMap;
		this.landscapeMap = landscapeMap;
		this.parent = parent;
		this.ghostmodus = ghostmodus;
		this.game = game;
		this.levelName = levelName;
		this.startGame();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 50, WIDTH, HEIGHT + 30);
		setResizable(false);
		this.setFocusable(true);
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
	private void generatePauseMenu() {
		pausePanel = new JPanel();
		endRace = new JButton("Rennen beenden");
		resumeRace = new JButton("Rennen fortsetzen");
		setButtonStyle(resumeRace, "Rennen fortsetzen", true);
		setButtonStyle(endRace, "Rennen beenden", true);
		pauseLabel = new JLabel("-Pause-");
		pauseLabel.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
		pauseMenu = new javax.swing.JDialog(this,true);
		// pauseMenu.setUndecorated(true);
		pauseMenu.setAlwaysOnTop(true);
		pauseMenu.setBounds(new java.awt.Rectangle(300, 200, 400, 300));
		pauseMenu.setMinimumSize(new java.awt.Dimension(400,300));
		pauseMenu.setModal(true);
		pauseMenu.setResizable(false);
		
		pausing = true;
		pausePanel.setLayout(new BoxLayout(pausePanel, BoxLayout.PAGE_AXIS));
		pausePanel.setOpaque(false);
		pauseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		resumeRace.setAlignmentX(Component.CENTER_ALIGNMENT);
		endRace.setAlignmentX(Component.CENTER_ALIGNMENT);
		pausePanel.add(Box.createRigidArea(new Dimension(15, 55)));
		pausePanel.add(pauseLabel);
		pausePanel.add(Box.createRigidArea(new Dimension(15, 15)));
		pausePanel.add(resumeRace);
		pauseMenu.setContentPane(new BackGroundPane("images/Hintergrund/Hintergrund3.png"));
		resumeRace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseMenu.setVisible(false);
				animatorCanvas.animator.resume();
				pausing = false;
				animatorCanvas.setPausing(false);
			}
		});
		pausePanel.add(Box.createRigidArea(new Dimension(15, 15)));
		pausePanel.add(endRace);
		endRace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				setVisible(false);
				pauseMenu.dispose();
				dispose();
			}
		});
		pauseMenu.add(pausePanel);
	}

	private void startGame() {
		// Hier wird eine Instanz vom Animator erstellt, der die Grafik
		// zeichnet.
		animatorCanvas = new Animator(this, game, roadMap, landscapeMap, user, gameColors, levelName, ghostmodus);
		this.add(animatorCanvas);

		this.setVisible(true);

		// Hier werden KeyListener initialisiert, um auf Benutzereingabe
		// reagieren zu kÃ¶nnen.
		this.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				
				animatorCanvas.onKeyPressed(e.getKeyCode());
				
				
				if (e.getKeyCode() == KeyEvent.VK_P) {
						
					if (!pausing) {
						// Erstelle Pause Menü
						generatePauseMenu();
						animatorCanvas.setPausing(true);
						pauseMenu.setVisible(true);

					} else {
						animatorCanvas.setPausing(false);
						// animator.animator.resume();
						pausing = false;
						pauseMenu.setVisible(false);
						pauseMenu.dispose();
					}
				}
			}
		});

		this.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				animatorCanvas.onKeyReleased(e.getKeyCode());
			}
		});
	}

}
