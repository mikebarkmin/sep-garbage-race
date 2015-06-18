package test;


import entities.xml.User;
import helper.Timer;

public class Playing {
	public void PlayingPlayer(User Vettel, String name,Timer t){
	
	if(Vettel.getUsername().equals(name)){
		if(t.getHS()>0 || t.getMin()>0 || t.getSek()>0 ){
			
			System.out.println(name +" spielt gerade  . Test bestanden ");
		}
		
		  System.out.println(name +" spielt nicht gerade  . Test nicht bestanden ");
	}
}
}
