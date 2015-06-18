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
	       private int kapazitätHat=0;
	       private int kapazitätBekommt=0;
	       private int stabilitätHat=0;
	       private int stabilitätBekommt=0;
			
			
			public Upgradebars() {
	            this.setPreferredSize(new Dimension(800, 400));
	            //this.setBorder(BorderFactory.createLoweredBevelBorder()); //der Rahmen des jPanels
	        }
	 
	        public void paintComponent(Graphics g) {
	        	super.paintComponent(g);
	        	//malt die Leisten der einzelnen Upgrades
	            g.setColor(Color.WHITE);
	            g.fillRect(550, 131, 150, 15); //für Geschwindigkeit
	            g.fillRect(550, 156, 150, 15); //für Handling
	            g.fillRect(550, 181, 150, 15); //für Kapazität
	            g.fillRect(550, 206, 150, 15); //für Stabilität
	            
	            //malt die Leisten für die nächsten Upgrades vor dem Kauf
	            g.setColor(Color.RED);
	            g.fillRect(550, 132, 150*geschwindigkeitBekommt/100, 13); //für Geschwindigkeit
	            g.fillRect(550, 157, 150*handlingBekommt/100, 13); //für Handling
	            g.fillRect(550, 182, 150*kapazitätBekommt/100, 13); //für Kapazität
	            g.fillRect(550, 207, 150*stabilitätBekommt/100, 13); //für Stabilität
	            
	          //malt die Leisten für die vorhandenen Upgrades
	            g.setColor(Color.GREEN);
	            g.fillRect(550, 132, 150*geschwindigkeitHat/100, 13); //für Geschwindigkeit
	            g.fillRect(550, 157, 150*handlingHat/100, 13); //für Handling
	            g.fillRect(550, 182, 150*kapazitätHat/100, 13); //für Kapazität
	            g.fillRect(550, 207, 150*stabilitätHat/100, 13); //für Stabilität
	            
	            
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

			public int getKapazitätHat() {
				return kapazitätHat;
			}

			public void setKapazitätHat(int kapazitätHat) {
				this.kapazitätHat = kapazitätHat;
			}

			public int getKapazitätBekommt() {
				return kapazitätBekommt;
			}

			public void setKapazitätBekommt(int kapazitätBekommt) {
				this.kapazitätBekommt = kapazitätBekommt;
			}

			public int getStabilitätHat() {
				return stabilitätHat;
			}

			public void setStabilitätHat(int stabilitätHat) {
				this.stabilitätHat = stabilitätHat;
			}

			public int getStabilitätBekommt() {
				return stabilitätBekommt;
			}

			public void setStabilitätBekommt(int stabilitätBekommt) {
				this.stabilitätBekommt = stabilitätBekommt;
			}
	        
	         
	    }

