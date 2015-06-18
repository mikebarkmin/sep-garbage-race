package entities.xml;

import java.util.ArrayList;
import java.util.List;

public class Level {

	public Level(){
		setSurface("");
		setDirection("");
		setName("");
		setModel("");
		setDescription("");
		setBarriers(new ArrayList<Barrier>());
		setTrashs(new ArrayList<Trash>());
		setHighscore(new Highscore());
	}
	
	private String surface;
	private String direction;
	private String name;
	private String model;
	private String description;
	private List<Barrier> barriers;
	private List<Trash> trashs;
	private Highscore highscore;
	
	public String getSurface() {
		return surface;
	}
	public void setSurface(String surface) {
		this.surface = surface;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Barrier> getBarriers() {
		return barriers;
	}
	public void setBarriers(List<Barrier> barriers) {
		this.barriers = barriers;
	}
	public List<Trash> getTrashs() {
		return trashs;
	}
	public void setTrashs(List<Trash> trashs) {
		this.trashs = trashs;
	}
	public Highscore getHighscore() {
		return highscore;
	}
	public void setHighscore(Highscore highscore) {
		this.highscore = highscore;
	}

}
