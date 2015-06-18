package entities.game;

/**
 *
 * @author mike
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import menupresentation.GameScreen;

public class Road {

	// Wie leicht es sich lenken lässt auf der Strecke.
	// kleine Zahlen = schwerfällige Lenkung
	private int steerLeft = 10;
	private int steerRight = -10;
	private int roadLength = 180; // Die Länge der Strecke
	private Color[] gameColors;
	final private int BORDER_BASE = 20;
	private Surrounding surroundingRight;
	private Surrounding surroundingLeft;
	// Die Map der Strecke. Die einzelnen Einträge im Array geben die Stärke
	// der Kurven an, jeder Eintrag entspricht ungefähr 10km.
	// Das heißt wenn wir eine Streckenlänge von 140 haben, dann brauchen wir
	// 140 Einträge. Wenn wir weniger Einträge haben, dann wiederholt sich die
	// Strecke von Anfang an.
	private int[] roadMap;
	// Jetzt wird es haarig, um den gewünschten Pseudo 3d Effekt zu erzielen,
	// brauchen wir ein wenig Mathematik.
	// Lest euch einfach die Seite durch http://www.extentofthejam.com/pseudo/
	// Wenn ihr dann noch Fragen habt, kann ich versuchen euch das zu erklären.
	final public int CONST_S = (GameScreen.WIDTH / 2) / 10
			- (GameScreen.roadHeight / 100);
	final public float CONST_Z = (GameScreen.WIDTH / GameScreen.roadHeight) / 2.0f;

	private int[] scale; // Die Variable speicher ein Raster des aktuellen
							// Fensters, um den Pseudo 3d Effekt zu erzeugen.
	private int borderChange; // Die Variable brauchen wir, um die
								// Streckenmarkierung zu animieren.
	private int offset = 0; // Die Variable bracuhen wir, damit wir wissen wie
							// weit wir schon vorangeschritten sind.
	private Color borderColor; // Die Farbe der momentanen Streckenmarkierung
	private Color divisionColor; // Die Farbe der Streckentrennlinie
	private float distance; // Die Position des Wagens auf der Strecke
	private String levelname = "";

	private ArrayList<Surrounding> trashObjects = new ArrayList<Surrounding>();
	private ArrayList<Surrounding> barrierObjects = new ArrayList<Surrounding>();
	private ArrayList<Surrounding> settingLeft = new ArrayList<Surrounding>();
	private ArrayList<Surrounding> settingRight = new ArrayList<Surrounding>();
	private int undergroundticker = 0;
	private float tmpSteerLeft;
	private float tmpSteerRight;

	public Road(int[] roadMap, Color[] gameColors, String levelname) {
		this.roadMap = roadMap;
		this.gameColors = new Color[6];
		this.gameColors = gameColors;
		this.roadLength = roadMap.length;
		this.levelname = levelname;
		
		scale = new int[GameScreen.roadHeight];

		for (int i = 0; i < GameScreen.roadHeight; ++i) {

			float tmp = i * 1.0f / GameScreen.roadHeight;

			scale[i] = (int) (tmp * tmp * CONST_S);
		}

	}

	// Mit der Funktion setzten wir die Koordinate für den ersten Farbwechsel
	// der Streckenmarkierung
	public void setBorderChange(int borderChange2) {
		borderChange = borderChange2;
	}

	// Mit der Funktion bekommen wir die Koordinate für den ersten Farbwechsel
	// der Streckenmarkierung
	public int getBorderChange() {
		return borderChange;
	}

	// Die Funktion setzt die Farbe der Streckenmarkierung fest
	public void setBorderColor(Color borderColor2) {
		borderColor = borderColor2;
	}

	// Die Funktion gibt die Farbe der Streckenmarkierung zurück
	public Color getBorderColor() {
		return borderColor;
	}

	// Die Funktion setzt die Farbe der Streckentrennlinie
	public void setDivisionColor(Color divisionColor2) {
		divisionColor = divisionColor2;
	}

	// Die Funktion gibt die Farbe der Streckentrennlinie zurück
	public Color getDivisionColor() {
		return divisionColor;
	}

	// Die Funktion gibt den Fortschritt des Wagens an. Es wir der Teil der
	// Strecke ausgegeben auf dem der Wagen sich befindet.
	// Dabei handelt es sich aber nur um die Art des Streckabschnittes.
	public int getRoadOffset() {
		return roadMap[(int) distance];
	}

	// Die Funktion setzt die momentane Distanz des Wagens auf der Strecke
	public void setDistance(float distance2) {

		distance = distance2;
	}

	// Die Funktion gibt die momentane Distanz des Wagens zurück
	public float getDistance() {
		return distance;
	}

	public void setScroll(int delta) {
		offset += delta;

		// Es soll nicht weiter gescrollt werden, als die Hälfte der Strecke
		if (offset < -GameScreen.WIDTH / 2) {
			offset = -GameScreen.WIDTH / 2;
		}

		if (offset > GameScreen.WIDTH / 2) {
			offset = GameScreen.WIDTH / 2;
		}
	}

	public int getScroll() {
		return offset;
	}

	// Die Funktion gibt die Gesamtlänge der Strecke zurück
	public int getROAD_LENGTH() {
		return roadLength;
	}

	// Mit der Funktion werden alle Elemente der Strecke auf den Bildschrim
	// gezeichnet.
	public void draw(Graphics g) {
		float deltaZ = 0.0f;
		int deltaS = 0;
		int delta = getRoadOffset();
		int lineStart = 0;
		int lineEnd = 0;
		int borderWidth = 0;
		int b = 0;
		boolean startCount = false;

		for (int i = GameScreen.HEIGHT - 1, j = 0; i >= GameScreen.skyHeight; --i, ++j) {

			// Die erste Streckenmarkierung wird nach 30 Schritten die Farbe
			// wechseln
			if (j == borderChange) {
				startCount = true;
			}

			if (startCount) {
				b++;
			}

			deltaZ += CONST_Z;
			deltaS = delta * scale[j];
			lineStart = (int) deltaZ + deltaS + offset;
			// startLine = lineStart;
			lineEnd = GameScreen.WIDTH - (int) deltaZ + deltaS + offset;

			g.setColor(gameColors[2]);
			g.drawLine(lineStart, i, lineEnd, i);

			// Danach werden immer nach 30 weiteren Schritte wieder die Farbe
			// gewechselt.
			if (b == 30) {
				b = 0;
				borderColor = (borderColor == gameColors[3] ? gameColors[4]
						: gameColors[3]);
				divisionColor = (divisionColor == gameColors[5] ? gameColors[2]
						: gameColors[5]);
			}

			// Hier wird die Breite der Streckenmarkierung berechnet. Dabei wird
			// Rücksicht auf die Entfernung genommen.
			borderWidth = (GameScreen.WIDTH - 2 * (int) deltaZ) * BORDER_BASE
					/ GameScreen.WIDTH;
			g.setColor(borderColor);
			g.drawLine(lineStart, i, lineStart + borderWidth, i);
			g.drawLine(lineEnd, i, lineEnd + borderWidth, i);
			
			
			// Hier werden die Streckentrennlinien gezeichnet
			lineStart = GameScreen.WIDTH / 2 + deltaS + offset - borderWidth;
			lineEnd = GameScreen.WIDTH / 2 + deltaS + offset + borderWidth;
			g.setColor(divisionColor);
			g.drawLine(lineStart, i, lineEnd, i);

		}
		

	}

	public int getSteerLeft() {
		return steerLeft;
	}

	public void setSteerLeft(int steerLeft) {
		this.steerLeft = steerLeft;
	}

	public int getSteerRight() {
		return steerRight;
	}

	public void setSteerRight(int steerRight) {
		this.steerRight = steerRight;
	}

	// Mike hier ist mein Versuch
	public void initSurrounding() {
		for(int i = 0; i < getROAD_LENGTH()+1; i++) {
		
		surroundingRight = new Surrounding(2, this, 2, "setting");
		surroundingLeft = new Surrounding(2, this, 0, "setting");

		surroundingRight.addFrame(1, "images/"+levelname+"/setting_right1.png");
		surroundingRight.addFrame(2, "images/"+levelname+"/setting_right2.png");
		
		surroundingLeft.addFrame(1, "images/"+levelname+"/setting_left1.png");
		surroundingLeft.addFrame(2, "images/"+levelname+"/setting_left2.png");

		int randomRight = (int) Math.floor(Math.random() * (surroundingRight.frames()));
		int randomLeft = (int) Math.floor(Math.random() * (surroundingLeft.frames()));
		
		surroundingRight.setScale(0.0f);
		surroundingRight.setX(0);
		surroundingRight.setY(GameScreen.skyHeight);
		surroundingRight.selFrame(randomRight + 1);
		surroundingRight.off();
		settingRight.add(surroundingRight);

		surroundingLeft.setScale(0.0f);
		surroundingLeft.setX(0);
		surroundingLeft.setY(GameScreen.skyHeight);
		surroundingLeft.selFrame(randomLeft + 1);
		surroundingLeft.off();
		settingLeft.add(surroundingLeft);
		}

		for (int i = 0; i < getROAD_LENGTH()+1 /*
												 * Erzeugt #L�nge
												 * M�lltonnen
												 */; i++) {
			Surrounding tmpTrash = new Surrounding(1, this, 1, "trash");
			tmpTrash.addFrame(1, "images/"+levelname+"/trash.png");
			tmpTrash.setScale(0.0f);
			tmpTrash.setX(0);
			tmpTrash.setY(GameScreen.skyHeight);
			tmpTrash.selFrame(1);
			tmpTrash.off();
			trashObjects.add(tmpTrash);

		}
		for (int i = 0; i < getROAD_LENGTH()+1 /*
												 * Erzeugt #L�nge
												 * Hindernisse
												 */; i++) {
			String[] type = { "small", "big", "underground" };
			int random = (int) Math.floor(Math.random() * (type.length));
			Surrounding tmpBarrier = new Surrounding(3, this, 1, type[random]);
			tmpBarrier.addFrame(1, "images/"+levelname+"/barrier_small.png");
			tmpBarrier.addFrame(2, "images/"+levelname+"/barrier_big.png");
			tmpBarrier.addFrame(3, "images/"+levelname+"/barrier_underground.png");
			tmpBarrier.setScale(0.0f);
			tmpBarrier.setX(0);
			tmpBarrier.setY(GameScreen.skyHeight);
			tmpBarrier.selFrame(random+1);
			//sSystem.out.println(random+1);
			tmpBarrier.off();
			barrierObjects.add(tmpBarrier);

		}

	}

	public void drawSurrounding(Graphics g2, Player player) {

		for (int i = 0; i < getROAD_LENGTH() ; i++) {
			if (Math.floor(distance) == i * 15) {
				trashObjects.get(i).setStartPosition(distance);
				trashObjects.get(i).on();
			}
			if (Math.floor(distance) == i * 11 + 1) {
				barrierObjects.get(i).setStartPosition(distance);
				barrierObjects.get(i).on();
			}
			if (Math.floor(distance) == i * 7 + 1) {
				settingLeft.get(i).setStartPosition(distance);
				settingLeft.get(i).on();
			}
			if (Math.floor(distance) == i * 5 + 1) {
				settingRight.get(i).setStartPosition(distance);
				settingRight.get(i).on();
			}
			if (player.collideSurrounding(trashObjects.get(i))
					&& trashObjects.get(i).isActive()) {
				if (player.getNitroFuel() < 190*player.getCapacity()/32) {
					player.setNitroFuel(player.getNitroFuel() + 10);
				}
				if (player.getTrashCollected() < player.getCapacity()) {
					player.setSteerLeft(player.getSteerLeft() - 0.125f);
					player.setSteerRight(player.getSteerRight() - 0.125f);
					player.setAccel(player.getAccel() - 0.01f);
					player.setTrashCollected(player.getTrashCollected() + 1);
				}
				trashObjects.get(i).off();
			}
			if(player.collideSurrounding(barrierObjects.get(i))) {
				if(barrierObjects.get(i).getType().equals("small")) {
					player.setSpeed(player.getSpeed() - player.getCollideForce());
				}
				else if(barrierObjects.get(i).getType().equals("big")) {
					player.setSpeed(0);
					setScroll(-getScroll());
				}
				else if(barrierObjects.get(i).getType().equals("underground") && undergroundticker == 0) {
					//underground Kollisionsereignis
					player.setUnderground(true);
					undergroundticker = 0;
					tmpSteerLeft = player.getSteerLeft();
					tmpSteerRight = player.getSteerRight();
					System.out.println(tmpSteerLeft);
					player.setSteerLeft(tmpSteerRight);
					player.setSteerRight(tmpSteerLeft);
				}
				barrierObjects.get(i).off();
			}
			
			settingLeft.get(roadLength-i).drawSurrounding(g2, player.getSpeed());
			settingRight.get(roadLength-i).drawSurrounding(g2, player.getSpeed());
			barrierObjects.get(roadLength-i).drawSurrounding(g2, player.getSpeed());
			trashObjects.get(roadLength-i).drawSurrounding(g2, player.getSpeed());
		}
		if(player.isUnderground() && undergroundticker < 50) {
			undergroundticker++;
			System.out.println(undergroundticker);
		} else if(undergroundticker >= 50) {
			player.setSteerLeft(tmpSteerLeft);
			player.setSteerRight(tmpSteerRight);
			player.setUnderground(false);
			undergroundticker = 0;
			//System.out.println(tmpSteerLeft);
		}

	}

	public int[] getRoadMap() {
		return roadMap;
	}

	public void setRoadMap(int[] roadMap) {
		this.roadMap = roadMap;
	}

	public int[] getScale() {
		return scale;
	}

	public void setScale(int[] scale) {
		this.scale = scale;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
