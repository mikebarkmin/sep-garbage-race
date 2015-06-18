package entities.xml;

import java.util.ArrayList;
import java.util.List;

public class Highscore {

	public Highscore(){
		setOrder("");
		setGhostmode(new ArrayList<Ghostmode>());
		setEntries(new ArrayList<Entry>());
	}
	
	private String order;
	private List<Ghostmode> ghostmode;
	private List<Entry> entries;
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public List<Ghostmode> getGhostmode() {
		return ghostmode;
	}
	public void setGhostmode(List<Ghostmode> ghostmode) {
		this.ghostmode = ghostmode;
	}
	public List<Entry> getEntries() {
		return entries;
	}
	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
}
