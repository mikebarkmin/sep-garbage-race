package entities.xml;


public class Entry {

	public Entry(){
		setTime("");
		setUsername("");
	}
	private String time;
	private String username;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
