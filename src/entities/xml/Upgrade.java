package entities.xml;

public class Upgrade {

	public Upgrade(){
		setType("");
		setPrice(0);
                setRank(0);
	}
	
	private String type;
	private int price;
	private int rank;

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
