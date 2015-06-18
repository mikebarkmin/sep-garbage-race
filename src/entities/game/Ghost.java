package entities.game;

import helper.Sprite;

import java.awt.Graphics;
import java.util.List;

import menupresentation.GameScreen;
import entities.xml.Ghostmode;

public class Ghost extends Sprite {


	private int width;
	private int height;
	private float scale;
	private Road road;
	private String type;
	private float startPosition;
	private int currentFrame = 0;
	private int ghostFrame = 0;
	private List<Ghostmode> ghostmodeList;

	public Ghost(int nFrames, Road road, List<Ghostmode> ghostmodeList) {
		super(nFrames);
		this.setScale(100.0f);
		this.setX(0);
		this.setY(GameScreen.skyHeight);
		this.selFrame(1);
		this.ghostmodeList = ghostmodeList;
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

	public void drawGhost(Graphics g) {
		int h = 0, w = 0;

		if(ghostFrame > ghostmodeList.size()-1) {
			off();
		}
		if(ghostFrame < ghostmodeList.size()-1 && currentFrame != ghostmodeList.get(ghostFrame).getFramenumber()) {
			ghostFrame--;
		}
		else if (isActive() && currentFrame == ghostmodeList.get(ghostFrame).getFramenumber()) {
			float deltaZ = 0.0f;
			int deltaS = 0;
			float ghostDistance = ghostmodeList.get(ghostFrame).getDistance(); // Die
			// Kurve
			// abhängig
			// von
			// der
			// Entfernung
			// vom
			// Ghost
			int delta = road.getRoadMap()[(int) road.getDistance()];
			int ghostOffset = ghostmodeList.get(ghostFrame).getScroll();
			

			//int scaleFactor = 100 + (int) (road.getDistance() - ghostDistance)*5;
			setScale(100 + (int) (road.getDistance() - ghostDistance)*4);
			if(getScale() < 0) {
				setScale(0);
			}
			w = (int) (getW() * getScale() / 100.0f);
			h = (int) (getH() * getScale() / 100.0f);

			int tempPos = getY() - GameScreen.skyHeight;
			deltaZ += (GameScreen.roadHeight - tempPos) * road.CONST_Z;
			int scaleValue = GameScreen.roadHeight - getHeight() - tempPos - 1;
			if (scaleValue > 0 && scaleValue < road.getScale().length)
				deltaS = ( delta) * road.getScale()[scaleValue];
			
			setWidth(w);
			setHeight(h);
			setY(GameScreen.HEIGHT - Player.HEIGTH - 20 + (int) (road.getDistance() - ghostDistance)*5); //Der Wagen gewegt sich nach Oben
			
			setX((int) deltaZ + deltaS + road.getOffset() - (int)(ghostOffset*(getScale()))/100 + w - 15);
			on();
			//System.out.println((int)(ghostOffset*(getScale()))/100 + " OffsetXML: " + ghostOffset);
		}
		if(getY() + getHeight() < GameScreen.skyHeight) {
			setWidth(0);
			setHeight(0);
		}
		if (isActive()) {
			draw(g);
		}
		currentFrame++;
		ghostFrame++;
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
