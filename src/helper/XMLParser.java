/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import entities.xml.Barrier;
import entities.xml.Car;
import entities.xml.Entry;
import entities.xml.Game;
import entities.xml.Ghostmode;
import entities.xml.Highscore;
import entities.xml.Level;
import entities.xml.Trash;
import entities.xml.Trashcar;
import entities.xml.Upgrade;
import entities.xml.User;

/**
 *
 * @author Andre
 */
public class XMLParser {
    
	//Hier wird die XML-Datei ausgelesen
    public Game readXMLFile(){
    	try {
    		//Hier wird das Dokument eingelesen
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            Document doc = db.parse(new File("SEPGarbageRace.xml"));
            
            //Hier wird das Game-Element als oberster Knoten eingelesen
            Element rootElement = (Element) doc.getDocumentElement();
            Game game = new Game();
            
            //Hier werden die Knoten der XML-Datei ausgelesen
            //Es wird über alle Unterpunkte des Game-Elementes iteriert
            for(int i=0; i<rootElement.getChildNodes().getLength(); i++){
                Node child = rootElement.getChildNodes().item(i);
                //Hier wird der Gamename eingelesen
                if(child.getNodeName().equals("Gamename")){
                    game.setGameName(child.getTextContent());
                }
                //Hier werden die Level ausgelesen
                else if(child.getNodeName().equals("Level")){
                    Level level = new Level();
                    //Es wird über alle Unterpunkte des Level-Elementes iteriert
                    for(int j=0; j<child.getChildNodes().getLength(); j++){
                        Node levelChild = child.getChildNodes().item(j);
                        //Hier wird der Levelname ausgelesen
                        if(levelChild.getNodeName().equals("Levelname")){
                            level.setName(levelChild.getTextContent());
                        }
                        //Hier wird die Leveloberfläche ausgelesen
                        else if(levelChild.getNodeName().equals("Surface")){
                            level.setSurface(levelChild.getTextContent());
                        }
                        //Hier wird die Wegführung ausgelesen
                        else if(levelChild.getNodeName().equals("Direction")){
                            level.setDirection(levelChild.getTextContent());
                        }
                        //Hier wird das Levelmodell ausgelesen
                        else if(levelChild.getNodeName().equals("Model")){
                            level.setModel(levelChild.getTextContent());
                        }
                        //Hier wird die Beschreibung ausgelesen
                        else if(levelChild.getNodeName().equals("Description")){
                            level.setDescription(levelChild.getTextContent());
                        }
                        //Hier werden die Hindernisse ausgelesen
                        else if(levelChild.getNodeName().equals("Barrier")){
                            Barrier barrier = new Barrier();
                            //Es wird über alle Unterpunkte des Hindernis-Elementes iteriert
                            for(int k=0; k<levelChild.getChildNodes().getLength(); k++){
                                Node barrierChild = levelChild.getChildNodes().item(k);
                                //Hier wird der Hindernistyp ausgelesen
                                if(barrierChild.getNodeName().equals("Type")){
                                    barrier.setType(barrierChild.getTextContent());
                                }
                            }
                            //Alle Hindernisse werden in das Levelobjekt gespeichert
                            List<Barrier> l = level.getBarriers();
                            l.add(barrier);
                            level.setBarriers(l);
                        }
                        //Hier werden alle Müll-Power-Ups ausgelesen
                        else if(levelChild.getNodeName().equals("Trash")){
                            Trash trash = new Trash();
                            //Es wird über alle Unterpunkte des Müll-Elementes iteriert
                            for(int k=0; k<levelChild.getChildNodes().getLength(); k++){
                                Node trashChild = levelChild.getChildNodes().item(k);
                                //Hier wird der Mülltyp ausgelesen
                                if(trashChild.getNodeName().equals("Type")){
                                    trash.setType(trashChild.getTextContent());
                                }
                                //Hier wird die Müllmenge ausgelesen
                                else if(trashChild.getNodeName().equals("Amount")){
                                    trash.setAmount(Integer.valueOf(trashChild.getTextContent()));
                                }
                            }
                            //Alle Müll-Elemente werden in das Levelobjekt gespeichert
                            List<Trash> l = level.getTrashs();
                            l.add(trash);
                            level.setTrashs(l);
                        }
                        //Hier wird die Bestenliste ausgelesen
                        else if(levelChild.getNodeName().equals("Highscore")){
                            Highscore highscore = new Highscore();
                            //Es wird über alle Unterpunkte des Bestenlisten-Elementes iteriert
                            for(int k=0; k<levelChild.getChildNodes().getLength(); k++){
                                Node highscoreChild = levelChild.getChildNodes().item(k);
                                //Hier wird die Ordnung ausgelesen
                                if(highscoreChild.getNodeName().equals("Order")){
                                    highscore.setOrder(highscoreChild.getTextContent());
                                } 
                                //Hier wird die Ghostfahrt ausgelesen
                                else if(highscoreChild.getNodeName().equals("Ghostmode")){
                            		Ghostmode ghostmode = new Ghostmode();
                                	for(int m=0; m<highscoreChild.getChildNodes().getLength(); m++){
	                                	Node ghostmodeChild = highscoreChild.getChildNodes().item(m);
	                                    //Hier wird die Distanz ausgelesen
	                                    if(ghostmodeChild.getNodeName().equals("Distance")){
	                                        ghostmode.setDistance(Float.valueOf(ghostmodeChild.getTextContent()));
	                                    }
	                                    //Hier wird die X-Koordinate ausgelesen
	                                    else if(ghostmodeChild.getNodeName().equals("Scroll")){
	                                        ghostmode.setScroll(Integer.valueOf(ghostmodeChild.getTextContent()));
	                                    }
	                                    //Hier wird die Bildnummer des Fahrzeugs ausgelesen
	                                    else if(ghostmodeChild.getNodeName().equals("Framenumber")){
	                                        ghostmode.setFramenumber(Integer.valueOf(ghostmodeChild.getTextContent()));
	                                    }
                                	}
                                	//Alle Einträge werden im Bestenlisten-Element gespeichert
                                    List<Ghostmode> l = highscore.getGhostmode();
                                    l.add(ghostmode);
                                    highscore.setGhostmode(l);
                                }
                                //Hier wird der Eintrag ausgelesen
                                else if(highscoreChild.getNodeName().equals("Entry")){
                                    Entry entry = new Entry();
                                    //Es wird über alle Unterpunkte des Eintrag-Elementes iteriert
                                    for(int m=0; m<highscoreChild.getChildNodes().getLength(); m++){
                                        Node entryChild = highscoreChild.getChildNodes().item(m);
                                        //Hier wird der Benutzername ausgelesen
                                        if(entryChild.getNodeName().equals("Username")){
                                            entry.setUsername(entryChild.getTextContent());
                                        }
                                        //Hier wird die Zeit ausgelesen
                                        else if(entryChild.getNodeName().equals("Time")){
                                            entry.setTime(entryChild.getTextContent());
                                        }
                                    }
                                    //Alle Einträge werden im Bestenlisten-Element gespeichert
                                    List<Entry> l = highscore.getEntries();
                                    l.add(entry);
                                    highscore.setEntries(l);
                                }
                            }
                            //Die Bestenliste wird im Level-Objekt gespeichert
                            level.setHighscore(highscore);
                        }
                    }
                    //Alle Level werden im Game-Element gespeichert
                    List<Level> l = game.getLevels();
                    l.add(level);
                    game.setLevels(l);
                }
                //Hier werden alle Knoten des Benutzers ausgelesen
                else if(child.getNodeName().equals("User")){
                    User user = new User();
                    //Es wird über alle Unterpunkte des Benutzer-Elementes iteriert
                    for(int j=0; j<child.getChildNodes().getLength(); j++){
                        Node userChild = child.getChildNodes().item(j);
                        //Hier wird der Benutzername ausgelesen
                        if(userChild.getNodeName().equals("Username")){
                            user.setUsername(userChild.getTextContent());
                        }
                        //Hier wird die Müllmenge ausgelesen
                        else if(userChild.getNodeName().equals("Credits")){
                            user.setCredits(Integer.valueOf(userChild.getTextContent()));
                        }
                        //Hier wird der Müllwagen ausgelesen
                        else if(userChild.getNodeName().equals("Car")){
                            Car car = new Car();
                            //Es wird über alle Unterpunkte des Benutzer-Elementes iteriert
                            for(int k=0; k<userChild.getChildNodes().getLength(); k++){
                                Node carChild = userChild.getChildNodes().item(k);
                                //Hier wird der Müllwagenname ausgelesen
                                if(carChild.getNodeName().equals("Carname")){
                                    car.setCarname(carChild.getTextContent());
                                }
                                //Hier wird das aktuelle Motor-Upgrade ausgelesen
                                else if(carChild.getNodeName().equals("Engine-Upgrade")){
                                    car.setEngineupgrade(Integer.valueOf(carChild.getTextContent()));
                                }
                                //Hier wird das aktuelle Reifen-Upgrade ausgelesen
                                else if(carChild.getNodeName().equals("Tires-Upgrade")){
                                    car.setTiresupgrade(Integer.valueOf(carChild.getTextContent()));
                                }
                                //Hier wird das aktuelle Kapazitaets-Upgrade ausgelesen
                                else if(carChild.getNodeName().equals("Volume-Upgrade")){
                                    car.setVolumeupgrade(Integer.valueOf(carChild.getTextContent()));
                                }
                                //Hier wird das aktuelle Stabilitaets-Upgrade ausgelesen
                                else if(carChild.getNodeName().equals("Stability-Upgrade")){
                                    car.setStabilityupgrade(Integer.valueOf(carChild.getTextContent()));
                                }
                            }
                            //Der Müllwagen wird im Benutzer-Objekt gespeichert
                            user.setCar(car);
                        }
                    }
                    //Alle Benutzer werden im Game-Objekt gespeichert
                    List<User> l = game.getUsers();
                    l.add(user);
                    game.setUsers(l);
                }
               
                //Hier werden alle Knoten des Trashcars ausgelesen
                else if(child.getNodeName().equals("Trashcar")){
                    Trashcar trashcar = new Trashcar();
                    //Es wird über alle Unterpunkte des Müllwagen-Elementes iteriert
                    for(int j=0; j<child.getChildNodes().getLength(); j++){
                        Node trashcarChild = child.getChildNodes().item(j);
                        //Hier wird der Müllwagenname ausgelesen
                        if(trashcarChild.getNodeName().equals("Carname")){
                            trashcar.setCarname(trashcarChild.getTextContent());
                        }
                        //Hier wird die Bremskraft ausgelesen
                        else if(trashcarChild.getNodeName().equals("Brakepower")){
                            trashcar.setBrakepower(Integer.valueOf(trashcarChild.getTextContent()));
                        }
                        //Hier wird die Beschleunigung ausgelesen
                        else if(trashcarChild.getNodeName().equals("Speedup")){
                            trashcar.setSpeedup(Integer.valueOf(trashcarChild.getTextContent()));
                        }
                        //Hier wird das Ladevolumen ausgelesen
                        else if(trashcarChild.getNodeName().equals("Shippingvolume")){
                            trashcar.setShippingvolume(Integer.valueOf(trashcarChild.getTextContent()));
                        }
                        //Hier wird die Maximalgeschwindigkeit ausgelesen
                        else if(trashcarChild.getNodeName().equals("Maxspeed")){
                            trashcar.setMaxspeed(Integer.valueOf(trashcarChild.getTextContent()));
                        }
                        //Hier wird der Kurvenradius ausgelesen
                        else if(trashcarChild.getNodeName().equals("Curveradius")){
                            trashcar.setCurveradius(Integer.valueOf(trashcarChild.getTextContent()));
                        }
                        //Hier werden die Upgrades ausgelesen
                        else if(trashcarChild.getNodeName().equals("Upgrade")){
                            Upgrade upgrade = new Upgrade();
                            //Es wird über alle Unterpunkte des Upgrade-Elementes iteriert
                            for(int k=0; k<trashcarChild.getChildNodes().getLength(); k++){
                                Node upgradeChild = trashcarChild.getChildNodes().item(k);
                                //Hier wird der Upgradetyp ausgelesen
                                if(upgradeChild.getNodeName().equals("Type")){
                                    upgrade.setType(upgradeChild.getTextContent());
                                }
                                //Hier wird der Preis ausgelesen
                                else if(upgradeChild.getNodeName().equals("Price")){
                                    upgrade.setPrice(Integer.valueOf(upgradeChild.getTextContent()));
                                }
                                //Hier wird der Rang ausgelesen
                                else if(upgradeChild.getNodeName().equals("Rank")){
                                    upgrade.setRank(Integer.valueOf(upgradeChild.getTextContent()));
                                }
                            }
                            //Alle Upgrades werden im Müllwagen-Objekt gespeichert
                            List<Upgrade> l = trashcar.getUpgrades();
                            l.add(upgrade);
                            trashcar.setUpgrades(l);
                        }
                    }
                    //Alle Müllwagen werden im Game-Objekt gespeichert
                    List<Trashcar> l = game.getTrashcars();
                    l.add(trashcar);
                    game.setTrashcars(l);
                }
            }
            return game;
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  
            return null;
        }
    }

    //Hier wird die XML-Datei abgespeichert
    public void createXMLFile(Game game){
        try {
        	//Hier wird ein neues Dokument geöffnet
        	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// game elements
			Document doc = docBuilder.newDocument();
			Element gameElement = doc.createElement("Game");
			doc.appendChild(gameElement);
	 
            // gamename element
			Element gamename = doc.createElement("Gamename");
	        gamename.appendChild(doc.createTextNode(game.getGameName()));
			gameElement.appendChild(gamename);
                
			//Hier wird über alle Level-Elemente iteriert
	        for(Level l : game.getLevels()){
	            // level elements
	            Element level = doc.createElement("Level");
	            gameElement.appendChild(level);
	
	            // levelname element
	            Element levelname = doc.createElement("Levelname");
	            levelname.appendChild(doc.createTextNode(l.getName()));
	            level.appendChild(levelname);
	            
	            // s element
	            Element surface = doc.createElement("Surface");
	            surface.appendChild(doc.createTextNode(l.getSurface()));
	            level.appendChild(surface);
	            
	            // direction element
	            Element direction = doc.createElement("Direction");
	            direction.appendChild(doc.createTextNode(l.getDirection()));
	            level.appendChild(direction);
	            
	            // model element
	            Element model = doc.createElement("Model");
	            model.appendChild(doc.createTextNode(l.getModel()));
	            level.appendChild(model);
	            
	            // model element
	            Element description = doc.createElement("Description");
	            description.appendChild(doc.createTextNode(l.getDescription()));
	            level.appendChild(description);
	            
	            // barrier elements
	            for(Barrier b : l.getBarriers()){
	                // barrier element
	                Element barrier = doc.createElement("Barrier");
	                level.appendChild(barrier);
	                
	                // type element
	                Element type = doc.createElement("Type");
	                type.appendChild(doc.createTextNode(b.getType()));
	                barrier.appendChild(type);
	            }
	            
	            // trash elements
	            for(Trash b : l.getTrashs()){
	                // trash element
	                Element trash = doc.createElement("Trash");
	                level.appendChild(trash);
	                
	                // type element
	                Element type = doc.createElement("Type");
	                type.appendChild(doc.createTextNode(b.getType()));
	                trash.appendChild(type);
	                
	                // amount element
	                Element amount = doc.createElement("Amount");
	                amount.appendChild(doc.createTextNode(String.valueOf(b.getAmount())));
	                trash.appendChild(amount);
	            }
	            
	            // highscore element
	            Element highscore = doc.createElement("Highscore");
	            level.appendChild(highscore);
	            
	            // order element
	            Element order = doc.createElement("Order");
	            order.appendChild(doc.createTextNode(l.getHighscore().getOrder()));
	            highscore.appendChild(order);
	            
	            //ghostmode elements
	            for(Ghostmode g : l.getHighscore().getGhostmode()){
	                // ghostmode element
	                Element ghostmode = doc.createElement("Ghostmode");
	                highscore.appendChild(ghostmode);
	                
	                // distanz element
	                Element distance = doc.createElement("Distance");
	                distance.appendChild(doc.createTextNode(Float.toString(g.getDistance())));
	                ghostmode.appendChild(distance);
	                
	                // x element
	                Element scroll = doc.createElement("Scroll");
	                scroll.appendChild(doc.createTextNode(String.valueOf(g.getScroll())));
	                ghostmode.appendChild(scroll);
	                
	                // framenumber element
	                Element framenumber = doc.createElement("Framenumber");
	                framenumber.appendChild(doc.createTextNode(String.valueOf(g.getFramenumber())));
	                ghostmode.appendChild(framenumber);
	            }
	            
	            //entry elements
	            for(Entry e : l.getHighscore().getEntries()){
	                // entry element
	                Element entry = doc.createElement("Entry");
	                highscore.appendChild(entry);
	                
	                // username element
	                Element username = doc.createElement("Username");
	                username.appendChild(doc.createTextNode(e.getUsername()));
	                entry.appendChild(username);
	                
	                // time element
	                Element time = doc.createElement("Time");
	                time.appendChild(doc.createTextNode(e.getTime()));
	                entry.appendChild(time);
	            }
	        }
        
	        for(User u : game.getUsers()){
	            // user elements
	            Element user = doc.createElement("User");
	            gameElement.appendChild(user);
	
	            // username element
	            Element username = doc.createElement("Username");
	            username.appendChild(doc.createTextNode(u.getUsername()));
	            user.appendChild(username);
	            
	            // credits element
	            Element credits = doc.createElement("Credits");
	            credits.appendChild(doc.createTextNode(String.valueOf(u.getCredits())));
	            user.appendChild(credits);
	            
	            // car element
	            Element car = doc.createElement("Car");
	            user.appendChild(car);
	            
	            // carname element
	            Element carname = doc.createElement("Carname");
	            carname.appendChild(doc.createTextNode(u.getCar().getCarname()));
	            car.appendChild(carname);
	            
	            // engine-upgrade element
	            Element engineupgrade = doc.createElement("Engine-Upgrade");
	            engineupgrade.appendChild(doc.createTextNode(String.valueOf(u.getCar().getEngineupgrade())));
	            car.appendChild(engineupgrade);
	            
	            // tires-upgrade element
	            Element tiresupgrade = doc.createElement("Tires-Upgrade");
	            tiresupgrade.appendChild(doc.createTextNode(String.valueOf(u.getCar().getTiresupgrade())));
	            car.appendChild(tiresupgrade);
	            
	            // volume-upgrade element
	            Element volumeupgrade = doc.createElement("Volume-Upgrade");
	            volumeupgrade.appendChild(doc.createTextNode(String.valueOf(u.getCar().getVolumeupgrade())));
	            car.appendChild(volumeupgrade);
	            
	            // stability-upgrade element
	            Element stabilityupgrade = doc.createElement("Stability-Upgrade");
	            stabilityupgrade.appendChild(doc.createTextNode(String.valueOf(u.getCar().getStabilityupgrade())));
	            car.appendChild(stabilityupgrade);
	        }

	        for(Trashcar t : game.getTrashcars()){
	            // trashcar elements
	            Element trashcar = doc.createElement("Trashcar");
	            gameElement.appendChild(trashcar);
	
	            // carname element
	            Element carname = doc.createElement("Carname");
	            carname.appendChild(doc.createTextNode(t.getCarname()));
	            trashcar.appendChild(carname);
	            
	            // brakepower element
	            Element brakepower = doc.createElement("Brakepower");
	            brakepower.appendChild(doc.createTextNode(String.valueOf(t.getBrakepower())));
	            trashcar.appendChild(brakepower);
	            
	            // speedup element
	            Element speedup = doc.createElement("Speedup");
	            speedup.appendChild(doc.createTextNode(String.valueOf(t.getSpeedpower())));
	            trashcar.appendChild(speedup);
	            
	            // shippingvolume element
	            Element shippingvolume = doc.createElement("Shippingvolume");
	            shippingvolume.appendChild(doc.createTextNode(String.valueOf(t.getShippingvolume())));
	            trashcar.appendChild(shippingvolume);
	            
	            // maxspeed element
	            Element maxspeed = doc.createElement("Maxspeed");
	            maxspeed.appendChild(doc.createTextNode(String.valueOf(t.getMaxspeed())));
	            trashcar.appendChild(maxspeed);
	            
	            // curveradius element
	            Element curveradius = doc.createElement("Curveradius");
	            curveradius.appendChild(doc.createTextNode(String.valueOf(t.getCurveradius())));
	            trashcar.appendChild(curveradius);
	            
	            for(Upgrade u:t.getUpgrades()){
	                // upgrade element
	                Element upgrade = doc.createElement("Upgrade");
	                trashcar.appendChild(upgrade);
	                
	                // type element
	                Element type = doc.createElement("Type");
	                type.appendChild(doc.createTextNode(u.getType()));
	                upgrade.appendChild(type);
	                
	                // price element
	                Element price = doc.createElement("Price");
	                price.appendChild(doc.createTextNode(String.valueOf(u.getPrice())));
	                upgrade.appendChild(price);
	                
	                // rank element
	                Element rank = doc.createElement("Rank");
	                rank.appendChild(doc.createTextNode(String.valueOf(u.getRank())));
	                upgrade.appendChild(rank);
	            }
	        }
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("SEPGarbageRace.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
	
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	
    }
}
