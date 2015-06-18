package entities.game;

/**
 *
 * @author mike
 */

//import helper.*;
import helper.Sprite;

import java.awt.Graphics;

import menupresentation.GameScreen;

public class Landscape
{
	private final int WIDTH	 = 4;		//Gibt an wie viele Spalten geladen werden sollen. Für unsere Auflösung sind 19 optimal.
	private final int MAX_TILES = 10;	//Gibt an wie viele verschieden Hintergrundbilder es geben soll.
	private final int TILE_WIDTH = 397;	//Gibt die Breite der Hintergrundbilder an
	private final int TILE_HEIGHT = 340;	//Gibt die Höhe der Hintergrundbilder an
	
	private Sprite tiles[];			//Das ist die Tilemap
	private int offset = 0;			//Auch hier müssen wir darauf acht wie weit wir uns beweget haben, da auch der Hintergrund sich passend zu der Fahrt bewegen soll.
	private int[] tilePos;			//Hier werden die Positionen der Hintergrundbilder gespeichert.
	private int[] landscapeMap;
	
	public Landscape(int[] landscapeMap)
	{
		this.landscapeMap = landscapeMap;
		tiles = new Sprite[MAX_TILES];
		tilePos = new int[WIDTH];
		
		//initialize tileset
		for(int i=0, j=0; i<MAX_TILES; ++i, ++j)
		{
			tiles[i] = new Sprite(1);
			tiles[i].on();
			tiles[i].addFrame(1, "images/Landscape/landscape"+j+".png");
		}
	
		for(int i=0; i<WIDTH; ++i)
			tilePos[i] = i;
	}

	//Funktionsweise wie bei der Strecke
	public void scroll(int delta)
	{
		offset+=delta;

		if(offset < -TILE_WIDTH || offset > TILE_WIDTH)
		{
			
			offset = 0;
			
			
			for(int i=0; i<WIDTH; ++i)
			{
				
				int pos = tilePos[i];
				
				
				if(delta < 0)
				{				
					
					if(pos++ >= WIDTH-1)
						pos = 0;
				}
				
			
				if(delta > 0)
				{
					
					if(pos-- <= 0)
						pos = WIDTH-1;
				}
				
				
				tilePos[i] = pos;
			}
		}
	}
	
	
	public void draw(Graphics g)
	{
		for(int i=0, x=-1; i<this.WIDTH; ++i, ++x)
		{
			int t = landscapeMap[tilePos[i]];
			
			tiles[t].setX(x*TILE_WIDTH+offset);
			tiles[t].setY(GameScreen.skyHeight - TILE_HEIGHT);
			tiles[t].draw(g);
		}
	}
}
