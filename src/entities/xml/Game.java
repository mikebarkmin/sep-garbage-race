/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.xml;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre
 */
public class Game {

    public Game() {
        this.gameName = "";
        this.levels = new ArrayList<Level>();
        this.users = new ArrayList<User>();
        this.trashcars = new ArrayList<Trashcar>();
    }
    
    private String gameName;
    private List<Level> levels;
    private List<User> users;
    private List<Trashcar> trashcars;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Trashcar> getTrashcars() {
        return trashcars;
    }

    public void setTrashcars(List<Trashcar> trashcars) {
        this.trashcars = trashcars;
    }
}
