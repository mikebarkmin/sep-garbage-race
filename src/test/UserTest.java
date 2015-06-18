package test;

import entities.xml.Game;
import entities.xml.User;

public class UserTest {
	public UserTest(String username, int credits, Game game) {
		for(User u: game.getUsers()){
			if(u.getUsername().equals(username)){
				if(u.getCredits()==credits)
					System.out.println("Der Test ist bestanden. Istwert: " + credits + " Sollwert: "+u.getCredits());
				else
					System.out.println("Der Test ist nicht bestanden.");
			}
		}
	}
}
