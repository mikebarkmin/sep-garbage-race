package helper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import entities.game.Surrounding;

public class Sprite {
	private int posX, posY;
	private int frame, nFrames;

	private boolean active;
	protected Image sprites[];
	private boolean debug = false;
	private int x1, x2, y1, y2, w1, w2, h1, h2;

	public Sprite(int nFrames) {
		posX = 0;
		posY = 0;
		active = false;
		frame = 1;
		this.nFrames = nFrames;
		sprites = new Image[nFrames + 1];
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public int getW() {
		return sprites[frame].getWidth(null);
	}

	public int getH() {
		return sprites[frame].getHeight(null);
	}

	public int getFrame() {
		return frame;
	}

	public void setX(int value) {
		posX = value;
	}

	public void setY(int value) {
		posY = value;
	}

	public void on() {
		active = true;
	}

	public void off() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public void selFrame(int frameNum) {
		frame = frameNum;
	}

	public int frames() {
		return nFrames;
	}

	public void addFrame(int frameNum, String path) {
		try {
			// URL url = Sprite.class.getResource(path);
			sprites[frameNum] = Toolkit.getDefaultToolkit().getImage(path);
		} catch (Exception ex) {
			System.err.println("Could not load image:" + path + " "
					+ ex.toString());
		}
	}

	public boolean collideSurrounding(Surrounding s) {
		x1 = this.getX();
		y1 = this.getY() + this.getH() / 2;
		w1 = this.getW();
		h1 = this.getH() / 2;

		x2 = s.getX();
		y2 = s.getY() - (int) (s.getH() * s.getScale() / 100.0f);
		w2 = (int) (s.getW() * s.getScale() / 100.0f);
		h2 = (int) (s.getH() / 1.2 * s.getScale() / 100.0f);

		Rectangle rect1 = new Rectangle(x1, y1, w1, h1);

		Rectangle rect2 = new Rectangle(x2, y2, w2, h2);

		if (rect2.intersects(rect1) && s.isActive()) {

			return true;
		}

		else {
			return false;
		}
	}

	public void draw(Graphics g) {

		g.drawImage(sprites[frame], posX, posY, null);

	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
