package entities.xml;

import java.util.ArrayList;
import java.util.List;

public class Trashcar {

	public Trashcar(){
		setBrakepower(0);
		setSpeedup(0);
		setShippingvolume(0);
		setMaxspeed(0);
		setCurveradius(0);
		setUpgrades(new ArrayList<Upgrade>());
	}
	
    private String carname;
    private int brakepower;
	private int speedup;
	private int shippingvolume;
	private int maxspeed;
	private int curveradius;
	private List<Upgrade> upgrades;
	
        public String getCarname() {
            return carname;
        }
        public void setCarname(String carname) {
            this.carname = carname;
        }
	public int getBrakepower() {
		return brakepower;
	}
	public void setBrakepower(int brakepower) {
		this.brakepower = brakepower;
	}
	public int getSpeedpower() {
		return speedup;
	}
	public void setSpeedup(int speedup) {
		this.speedup = speedup;
	}
	public int getShippingvolume() {
		return shippingvolume;
	}
	public void setShippingvolume(int shippingvolume) {
		this.shippingvolume = shippingvolume;
	}
	public int getMaxspeed() {
		return maxspeed;
	}
	public void setMaxspeed(int maxspeed) {
		this.maxspeed = maxspeed;
	}
	public int getCurveradius() {
		return curveradius;
	}
	public void setCurveradius(int curveradius) {
		this.curveradius = curveradius;
	}
	public List<Upgrade> getUpgrades() {
		return upgrades;
	}
	public void setUpgrades(List<Upgrade> upgrades) {
		this.upgrades = upgrades;
	}
}
