package entities.game;

import helper.Sprite;

import java.awt.Graphics;

import menupresentation.GameScreen;

public class Surrounding extends Sprite {

	private int width;
	private int height;
	private float scale;
	private Road road;
	private int randomFrame = 1;
	private int position;
	private double random = Math.random();
	private String type;
	private float startPosition;

	public Surrounding(int nFrames, Road road, int position, String type) {
		super(nFrames);
		this.setScale(0.0f);
		this.setX(0);
		this.type = type; //Gibt an um was für ein Objekt es sich handelt. trash, big, small, underground, setting
		this.setY(GameScreen.skyHeight);
		this.selFrame(1);
		this.position = position; // Die Position gibt an ob das Hinderniss neben der Strecke
							// links (0) auf der Strecke (1) oder rechts neben
							// der Strecke (2) steht.
		this.off();
		this.road = road;
	}

	public void draw(Graphics g) {
		g.drawImage(sprites[getFrame()], getX(), getY(), width, height, null);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void drawSurrounding(Graphics g, float speed) {
		int h = 0, w = 0;

		if (isActive()) {
			float deltaZ = 0.0f;
			int deltaS = 0;
			int delta = road.getRoadMap()[(int) road.getDistance()];

			setScale(getScale() + speed / 100); //setScale darf maximal 1 werden damit die Objekte nicht zu groß werden.
			// Vielleicht müssen wir die über in Abhängigkeit des Weges berechnen und nicht direkt über die Geschwindigkeit
			w = (int) (getW() * getScale() / 100.0f);
			h = (int) (getH() * getScale() / 100.0f);

			int tempPos = getY() - GameScreen.skyHeight;
			deltaZ += (GameScreen.roadHeight - tempPos) * road.CONST_Z;
			int scaleValue = GameScreen.roadHeight - tempPos - 1;
			if (scaleValue > 0 && scaleValue < road.getScale().length)
				deltaS = delta * road.getScale()[scaleValue];

			setWidth(w);
			setHeight(h);
			setY(getY() + (int) speed / 25);
			if (position == 0) {
				setX((int) deltaZ + deltaS + road.getOffset() - w - w);
			} else if (position == 2) {
				setX(GameScreen.WIDTH - (int) deltaZ + deltaS
						+ road.getOffset() + w);
			} else {
				setX((int) deltaZ + (deltaS + (int)(950*w/getW()* random)) + road.getOffset()
						);
			}
			on();
		}

		if (getX() <= 0 - getW() || getY() > GameScreen.HEIGHT) {
			setScale(0.0f);
			setY(GameScreen.skyHeight);
			randomFrame = Math.round((float) Math.random() * frames());
			off();
		}

		if (isActive()) {
			int tmp = getY();
			setY(tmp - h);
			draw(g);
			setY(tmp);
		}
	}

	public float getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(float startPosition) {
		this.startPosition = startPosition;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
