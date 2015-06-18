package test;

import helper.Animator;

import java.awt.event.KeyEvent;

public class KeyTest {
	private boolean test = false;
	public KeyTest() {
		
	}
	public void test(KeyEvent e,Animator animatorCanvas) {
		if(e.getKeyCode() == KeyEvent.VK_UP && test == false) {
			if(animatorCanvas.getPlayer().getSpeed() > 0) {
				System.out.println("Der Spieler beschleunigt. Der Test wurde bestanden.");
				test = true;
			}
			else {
				System.out.println("Der Spieler hat eine Geschwindigkeit von 0. Der Test wurde nicht bestanden.");
			}
		}
	}
}
