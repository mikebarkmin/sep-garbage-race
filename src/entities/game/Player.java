package entities.game;

import helper.Sprite;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import entities.xml.User;

/**
 * 
 * @author mike
 */

public class Player extends Sprite {
	final public static int WIDTH = 120; // Sprite Breite
	final public static int HEIGTH = 125; // Sprite Höhe

	// Hier können die einzelnen Frames des Müllwagens gesetzt werden. Es gibt
	// immer drei Frames pro Position da so auch die Bewegung der Reifen
	// simuliert werden kann.
	final public static int STEER_NONE = 1;
	final public static int STEER_NONE2 = 2;
	final public static int BRAKE_NONE = 3;

	final public static int STEER_RIGHT = 4;
	final public static int STEER_RIGHT2 = 5;
	final public static int BRAKE_RIGHT = 6;

	final public static int STEER_LEFT = 7;
	final public static int STEER_LEFT2 = 8;
	final public static int BRAKE_LEFT = 9;

	private float maxSpeed = 100.0f; // 100 //Die Maximalgeschwindigkeit des
										// Wagens max 320
	private float accel = 1.0f; // 1.0 //Die Beschleunigung des Wagens
	private float motorbrake = 1.0f; // Motorbremser + Reibung
	private float brake = 4.0f; // Die Bremskraft des Wagens
	private float steerRight = -10.0f;
	private float steerLeft = 10.0f;
	private int capacity = 16; // die Anfangskapazit�t des M�llwagens
	private float collideForce = 20.0f; // wie stark der Wagen bei einer
										// Kollision abgebremst wird. Ist f�r
										// die Stabilit�tsupgrades wichtig

	private int steer;
	private boolean accelerating;
	private boolean braking;
	private float speed;
	private boolean nitro;
	private int nitroFuel;
	private int trashCollected;
	private boolean underground;
	private Image[] volumeImages;
	private int startTrashVolume = 0;

	private User user;

	// Der Standart Konstruktor. Er ermöglicht es die Anzahl der Frames für
	// den Sprite anzugeben. Wenn wir die Variablen oben so lassen, würde ich
	// vorschlagen, dass wir 9 Sprites für den Wagen verwenden.
	public Player(int numFrames, User user) {
		super(numFrames);
		steer = STEER_NONE;
		underground = false;
		speed = 0;
		accelerating = false;
		braking = false;
		this.user = user;
		initVolumeImages();
	}

	public void initVolumeImages() {
		volumeImages = new Image[33];
		for(int i = 0; i < volumeImages.length;i++) {
			volumeImages[i] = Toolkit.getDefaultToolkit().getImage("images/Trashcar/trashvolume" + i +".png");
		}
	}
	// Die Funktion ändert die Lenkrichtung
	public void setSteerDirection(int direction) {
		steer = direction;
	}

	public int getSteerDirection() {
		return steer;
	}

	// Die Funktion aktiviert die Beschleunigung.
	public void accelerate() {
		accelerating = true;
	}

	// Die Funktion aktiviert die Bremsen.
	public void deaccelerate() {
		accelerating = false;
	}

	// Die Funktion überprüft, ob beschleunigt wird.
	public boolean isAccelerating() {
		return accelerating;
	}

	// Die Funktion setzt die Geschwindigkeit.
	public void setSpeed(float speed2) {
		if (this.isBraking() && speed2 < 0) {
			speed = 0;
			return;
		}
		if (getNitroFuel() != 0 && nitro == true) {

			setNitroFuel(getNitroFuel() - 1);
			nitroFuel = getNitroFuel();
		} else {
			setNitro(false);
		}
		// Die IF-Abfragen sorgen dafür, dass die Geschwindigkeit immer unter
		// der Maximalen und über 0 gehalten wird.
		if (speed2 > maxSpeed + 30.0f && nitro == true) {
			return;
		}
		// Diese If-Abfrage verursacht das flackern der
		// Geschwindigkeitsanzeige.
		if (speed2 + 2 * accel > maxSpeed && nitro == false) {
			speed2 -= 2 * accel;

			speed = speed2;
			return;
		}
		if (speed2 > maxSpeed && nitro == false) {
			speed = speed2;
			return;
		}
		if(!isAccelerating() && speed2 < 2) {
			speed = 0;
			return;
		}
		if (speed2 < 0) // Wenn die Bremskraft zu hoch ist, dann wir die
						// Schleife zu fr�h aktiviert
			return;

		speed = speed2;
	}

	public void drawUpgrade(Graphics g) {
		draw(g);
		Image tires[] = new Image[9];		
		switch (user.getCar().getTiresupgrade()) {
		case 2: case 3: case 4:
			tires[0] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire1234_f1.png");
			tires[1] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire1234_f2.png");
			tires[2] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire1234_f3.png");
			tires[3] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire1234_f1.png");
			tires[4] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire1234_f2.png");
			tires[5] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire1234_f3.png");
			tires[6] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire1234_f1.png");
			tires[7] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire1234_f2.png");
			tires[8] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire1234_f3.png");
			g.drawImage(tires[getFrame()-1], getX(), getY(), null);
			break;
		case 5: case 6: case 7: case 8:
			tires[0] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire5678_f1.png");
			tires[1] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire5678_f2.png");
			tires[2] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire5678_f3.png");
			tires[3] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire5678_f1.png");
			tires[4] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire5678_f2.png");
			tires[5] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire5678_f3.png");
			tires[6] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire5678_f1.png");
			tires[7] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire5678_f2.png");
			tires[8] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire5678_f3.png");
			g.drawImage(tires[getFrame()-1], getX(), getY(), null);
			break;
		case 9: case 10: case 11: case 12:
			tires[0] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire9101112_f1.png");
			tires[1] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire9101112_f2.png");
			tires[2] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire9101112_f3.png");
			tires[3] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire9101112_f1.png");
			tires[4] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire9101112_f2.png");
			tires[5] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire9101112_f3.png");
			tires[6] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire9101112_f1.png");
			tires[7] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire9101112_f2.png");
			tires[8] = Toolkit.getDefaultToolkit().getImage("images/Upgrades/tire9101112_f3.png");
			g.drawImage(tires[getFrame()-1], getX(), getY(), null);
			break;
		}
		switch (user.getCar().getEngineupgrade()) {
		case 9: case 10: case 11: case 12:
			g.drawImage(Toolkit.getDefaultToolkit().getImage("images/Upgrades/enigne9101112.png"), getX(), getY(), null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("images/Upgrades/enigne1234.png"), getX(), getY(), null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("images/Upgrades/enigne5678.png"), getX(), getY(), null);
			break;
		case 2: case 3: case 4:
			g.drawImage(Toolkit.getDefaultToolkit().getImage("images/Upgrades/enigne1234.png"), getX(), getY(), null);
			break;
		case 5: case 6: case 7: case 8:
			g.drawImage(Toolkit.getDefaultToolkit().getImage("images/Upgrades/enigne5678.png"), getX(), getY(), null);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("images/Upgrades/enigne1234.png"), getX(), getY(), null);
			break;
		}
		startTrashVolume = user.getCar().getVolumeupgrade();
		System.out.println(startTrashVolume);
		if(getTrashCollected() + startTrashVolume < volumeImages.length) {
		g.drawImage(volumeImages[getTrashCollected() + 8*2 - startTrashVolume*2],getX(), getY(), null);
		}
		switch (user.getCar().getStabilityupgrade()) {
		
		}
		
	}

	public void setNitro(boolean nitro) {
		this.nitro = nitro;
	}

	public boolean getNitro() {
		return this.nitro;
	}

	public float getSpeed() {
		return speed;
	}

	// Die Funtkion erlaubt es die Bremsen zu aktivieren und zu deaktivieren.
	public void brake(boolean status) {
		braking = status;
	}

	// Die Funktion gibt zurück, ob die Bremsen aktiviert sind
	public boolean isBraking() {
		return braking;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public float getAccel() {
		return accel;
	}

	public void setAccel(float accel) {
		this.accel = accel;
	}

	public float getMotorbrake() {
		return motorbrake;
	}

	public void setMotorbrake(float motorbrake) {
		this.motorbrake = motorbrake;
	}

	public float getBrake() {
		return brake;
	}

	public void setBrake(float brake) {
		this.brake = brake;
	}

	public float getSteerLeft() {
		return steerLeft;
	}

	public void setSteerLeft(float steerLeft) {
		this.steerLeft = steerLeft;
	}

	public float getSteerRight() {
		return steerRight;
	}

	public void setSteerRight(float steerRight) {
		this.steerRight = steerRight;
	}

	public int getNitroFuel() {
		return nitroFuel;
	}

	public void setNitroFuel(int nitroFuel) {
		this.nitroFuel = nitroFuel;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public float getCollideForce() {
		return collideForce;
	}

	public void setCollideForce(float collideForce) {
		this.collideForce = collideForce;
	}

	public int getTrashCollected() {
		return trashCollected;
	}

	public void setTrashCollected(int trashCollected) {
		this.trashCollected = trashCollected;
	}

	public boolean isUnderground() {
		return underground;
	}

	public void setUnderground(boolean underground) {
		this.underground = underground;
	}

}