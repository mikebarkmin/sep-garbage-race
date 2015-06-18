package entities.xml;

public class User {

	public User(){
		setUsername("");
		setCredits(0);
		setCar(new Car());
	}
	
	private String username;
	private int credits;
	private Car car;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }
}
