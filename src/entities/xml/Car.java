/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.xml;

/**
 *
 * @author Andre
 */
public class Car {
    
    private int engineupgrade;
    private int tiresupgrade;
    private int volumeupgrade;
    private int stabilityupgrade;
    private String carname;
    
    public int getEngineupgrade() {
        return engineupgrade;
    }

    public void setEngineupgrade(int engineupgrade) {
        this.engineupgrade = engineupgrade;
    }

    public int getTiresupgrade() {
        return tiresupgrade;
    }

    public void setTiresupgrade(int tiresupgrade) {
        this.tiresupgrade = tiresupgrade;
    }

    public int getVolumeupgrade() {
        return volumeupgrade;
    }

    public void setVolumeupgrade(int volumeupgrade) {
        this.volumeupgrade = volumeupgrade;
    }

    public int getStabilityupgrade() {
        return stabilityupgrade;
    }

    public void setStabilityupgrade(int stabilityupgrade) {
        this.stabilityupgrade = stabilityupgrade;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }
    
}
