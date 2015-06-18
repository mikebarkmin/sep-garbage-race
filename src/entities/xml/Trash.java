package entities.xml;

public class Trash extends Barrier{

	public Trash(){
		super();
		setAmount(0);
	}
	
	private int amount;
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
