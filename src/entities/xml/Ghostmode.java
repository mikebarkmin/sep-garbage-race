package entities.xml;

public class Ghostmode {

	private float distance;
	private int scroll;
	private int framenumber;
	
	public Ghostmode(){
		this.distance = 0.0f;
		this.scroll = 0;
		this.setFramenumber(1);
	}
	
	public Ghostmode(float distance, int scroll, int framenumber){
		this.distance = distance;
		this.scroll = scroll;
		this.setFramenumber(framenumber);
	}
	
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public int getScroll() {
		return scroll;
	}

	public void setScroll(int scroll) {
		this.scroll = scroll;
	}

	public int getFramenumber() {
		return framenumber;
	}

	public void setFramenumber(int framenumber) {
		this.framenumber = framenumber;
	}
}
