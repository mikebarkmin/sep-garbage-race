package menupresentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;


		public class Upgradebars extends JPanel {
	       private int geschwindigkeitHat=0;
	       private int geschwindigkeitBekommt=0;
	       private int handlingHat=0;
	       private int handlingBekommt=0;
	       private int kapazit�tHat=0;
	       private int kapazit�tBekommt=0;
	       private int stabilit�tHat=0;
	       private int stabilit�tBekommt=0;
			
			
			public Upgradebars() {
	            this.setPreferredSize(new Dimension(800, 400));
	            //this.setBorder(BorderFactory.createLoweredBevelBorder()); //der Rahmen des jPanels
	        }
	 
	        public void paintComponent(Graphics g) {
	        	super.paintComponent(g);
	        	//malt die Leisten der einzelnen Upgrades
	            g.setColor(Color.WHITE);
	            g.fillRect(550, 131, 150, 15); //f�r Geschwindigkeit
	            g.fillRect(550, 156, 150, 15); //f�r Handling
	            g.fillRect(550, 181, 150, 15); //f�r Kapazit�t
	            g.fillRect(550, 206, 150, 15); //f�r Stabilit�t
	            
	            //malt die Leisten f�r die n�chsten Upgrades vor dem Kauf
	            g.setColor(Color.RED);
	            g.fillRect(550, 132, 150*geschwindigkeitBekommt/100, 13); //f�r Geschwindigkeit
	            g.fillRect(550, 157, 150*handlingBekommt/100, 13); //f�r Handling
	            g.fillRect(550, 182, 150*kapazit�tBekommt/100, 13); //f�r Kapazit�t
	            g.fillRect(550, 207, 150*stabilit�tBekommt/100, 13); //f�r Stabilit�t
	            
	          //malt die Leisten f�r die vorhandenen Upgrades
	            g.setColor(Color.GREEN);
	            g.fillRect(550, 132, 150*geschwindigkeitHat/100, 13); //f�r Geschwindigkeit
	            g.fillRect(550, 157, 150*handlingHat/100, 13); //f�r Handling
	            g.fillRect(550, 182, 150*kapazit�tHat/100, 13); //f�r Kapazit�t
	            g.fillRect(550, 207, 150*stabilit�tHat/100, 13); //f�r Stabilit�t
	            
	            
	        }
	       
			public int getGeschwindigkeitHat() {
				return geschwindigkeitHat;
			}

			public void setGeschwindigkeitHat(int geschwindigkeitHat) {
				this.geschwindigkeitHat = geschwindigkeitHat;
			}

			public int getGeschwindigkeitBekommt() {
				return geschwindigkeitBekommt;
			}

			public void setGeschwindigkeitBekommt(int geschwindigkeitBekommt) {
				this.geschwindigkeitBekommt = geschwindigkeitBekommt;
			}

			public int getHandlingHat() {
				return handlingHat;
			}

			public void setHandlingHat(int handlingHat) {
				this.handlingHat = handlingHat;
			}

			public int getHandlingBekommt() {
				return handlingBekommt;
			}

			public void setHandlingBekommt(int handlingBekommt) {
				this.handlingBekommt = handlingBekommt;
			}

			public int getKapazit�tHat() {
				return kapazit�tHat;
			}

			public void setKapazit�tHat(int kapazit�tHat) {
				this.kapazit�tHat = kapazit�tHat;
			}

			public int getKapazit�tBekommt() {
				return kapazit�tBekommt;
			}

			public void setKapazit�tBekommt(int kapazit�tBekommt) {
				this.kapazit�tBekommt = kapazit�tBekommt;
			}

			public int getStabilit�tHat() {
				return stabilit�tHat;
			}

			public void setStabilit�tHat(int stabilit�tHat) {
				this.stabilit�tHat = stabilit�tHat;
			}

			public int getStabilit�tBekommt() {
				return stabilit�tBekommt;
			}

			public void setStabilit�tBekommt(int stabilit�tBekommt) {
				this.stabilit�tBekommt = stabilit�tBekommt;
			}
	        
	         
	    }

