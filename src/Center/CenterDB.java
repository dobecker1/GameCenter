package Center;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import GUI.MainFrame;



public class CenterDB {
	
	//private LinkedList<Player> players;
	//private Center center;
	private Document dom;
	private Document domRead;
	private Document domHis;
	
	public CenterDB() {
		
	}
	
	private void loadData() {
		/**
		center.addPlayer("Dominik","Becker", "thedome");
		center.addPlayer("Dominik", "Becker", "nickname");
		center.addGame("Wizard", "Kartenspiel", 6);
		center.addGame("Dominion","Kartenspiel", 6);
		**/
		
	}
	
	public void runXmlBuilder() {
		System.out.println("started...");
		createDocument();
		createHisDocument();
		createDOMTree();
		createHistoryTree();
		printToFile();
		printHistorytoFile();
		System.out.println("...Fertig");
	}
	
	private void createHistoryTree() {
		Element root = domHis.createElement("Center");
		domHis.appendChild(root);
		Iterator hIt = MainFrame.MAIN.getCenter().getHistory().iterator();
		Element his = domHis.createElement("History");
		root.appendChild(his);
		while(hIt.hasNext()) {
			GameEvent ge = (GameEvent) hIt.next();
			Element gameEv = createGameEventHisElement(ge);
			his.appendChild(gameEv);
		}
		
	}

	public void runXmlReader() {
		parseXmlFile("center.xml");
		parseDocument();
	}
	
	public void runHistoryReader() {
		parseXmlFile("history.xml");
		parseHistory();
	}

	private void createDocument() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			dom = db.newDocument();
		} catch(ParserConfigurationException pce) {
			
		}
	}
	
	private void createHisDocument() {
		DocumentBuilderFactory dbuilderFa = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dbuilder = dbuilderFa.newDocumentBuilder();
			
			domHis = dbuilder.newDocument();
		} catch(ParserConfigurationException pce) {
			
		}
	}
	private void createDOMTree() {
		Element rootE = dom.createElement("Center");
		dom.appendChild(rootE);
		
		Iterator pIt = MainFrame.MAIN.getCenter().getPlayers().iterator();
		Element players = dom.createElement("Players");
		rootE.appendChild(players);
		while(pIt.hasNext()) {
			Player p = (Player) pIt.next();
			Element playerE = createPlayerElement(p);
			players.appendChild(playerE);
		}
		Iterator gIt = MainFrame.MAIN.getCenter().getGames().iterator();
		Element games = dom.createElement("Games");
		rootE.appendChild(games);
		while(gIt.hasNext()) {
			Game g = (Game) gIt.next();
			Element gameE = createGameElement(g);
			games.appendChild(gameE);
		}
		Iterator pwIt = MainFrame.MAIN.getCenter().getPlaysWhat().iterator();
		Element playsW = dom.createElement("PlaysWhats");
		rootE.appendChild(playsW);
		while(pwIt.hasNext()) {
			PlaysWhat pw = (PlaysWhat) pwIt.next();
			Element playsWE = createPlaysWhatElement(pw);
			playsW.appendChild(playsWE);
		}
		/**
		Iterator hIt = MainFrame.MAIN.getCenter().getHistory().iterator();
		Element his = dom.createElement("History");
		rootE.appendChild(his);
		while(hIt.hasNext()) {
			GameEvent ge = (GameEvent) hIt.next();
			Element gameEv = createGameEventElement(ge);
			his.appendChild(gameEv);
		}
		**/
	}
	
	private Element createPlayerElement(Player p) {
		Element playerE = dom.createElement("Player");
		Element vorname = dom.createElement("vorname");
		Text vornametxt = dom.createTextNode(p.getVorname());
		vorname.appendChild(vornametxt);
		playerE.appendChild(vorname);
		
		Element name = dom.createElement("name");
		Text nametxt = dom.createTextNode(p.getName());
		name.appendChild(nametxt);
		playerE.appendChild(name);
		
		Element nickname = dom.createElement("nickname");
		Text nicknametxt = dom.createTextNode(p.getNickname());
		nickname.appendChild(nicknametxt);
		playerE.appendChild(nickname);
		
		return playerE;
		
	}
	
	private Element createPlayerHisElement(Player p) {
		Element playerE = domHis.createElement("Player");
		Element vorname = domHis.createElement("vorname");
		Text vornametxt = domHis.createTextNode(p.getVorname());
		vorname.appendChild(vornametxt);
		playerE.appendChild(vorname);
		
		Element name = domHis.createElement("name");
		Text nametxt = domHis.createTextNode(p.getName());
		name.appendChild(nametxt);
		playerE.appendChild(name);
		
		Element nickname = domHis.createElement("nickname");
		Text nicknametxt = domHis.createTextNode(p.getNickname());
		nickname.appendChild(nicknametxt);
		playerE.appendChild(nickname);
		
		return playerE;
		
	}
	
	private Element createGameElement(Game g) {
		Element gameE = dom.createElement("Game");
		Element name = dom.createElement("name");
		Text nametxt = dom.createTextNode(g.getName());
		name.appendChild(nametxt);
		gameE.appendChild(name);
		
		Element type = dom.createElement("type");
		Text typetxt = dom.createTextNode(g.getType());
		type.appendChild(typetxt);
		gameE.appendChild(type);
		
		Element anz = dom.createElement("maxAnzPlayer");
		Text anztext = dom.createTextNode(new Integer(g.getMaxAnzPlayers()).toString());
		anz.appendChild(anztext);
		gameE.appendChild(anz);
		
		Element isP = dom.createElement("isPlayed");
		Text anzText = dom.createTextNode(new Integer(g.getGameIsPlayed()).toString());
		isP.appendChild(anzText);
		gameE.appendChild(isP);
		
		return gameE;
	}
	
	private Element createGameHisElement(Game g) {
		Element gameE = domHis.createElement("Game");
		Element name = domHis.createElement("name");
		Text nametxt = domHis.createTextNode(g.getName());
		name.appendChild(nametxt);
		gameE.appendChild(name);
		
		Element type = domHis.createElement("type");
		Text typetxt = domHis.createTextNode(g.getType());
		type.appendChild(typetxt);
		gameE.appendChild(type);
		
		Element anz = domHis.createElement("maxAnzPlayer");
		Text anztext = domHis.createTextNode(new Integer(g.getMaxAnzPlayers()).toString());
		anz.appendChild(anztext);
		gameE.appendChild(anz);
		
		Element isP = domHis.createElement("isPlayed");
		Text anzText = domHis.createTextNode(new Integer(g.getGameIsPlayed()).toString());
		isP.appendChild(anzText);
		gameE.appendChild(isP);
		
		return gameE;
	}
	
	private Element createPlaysWhatElement(PlaysWhat pw) {
		Element playsW = dom.createElement("PlaysWhat");
		Element player = createPlayerElement(pw.getPlayer());
		playsW.appendChild(player);
		Element game = createGameElement(pw.getGame());
		playsW.appendChild(game);
		Element count = dom.createElement("count");
		Text counttxt = dom.createTextNode(new Integer(pw.getCount()).toString());
		count.appendChild(counttxt);
		playsW.appendChild(count);
		Element wins = dom.createElement("wins");
		Text wintxt = dom.createTextNode(new Integer(pw.getWins()).toString());
		wins.appendChild(wintxt);
		playsW.appendChild(wins);
		
		return playsW;
		
		
	}
	
	private Element createGameEventHisElement(GameEvent ge) {
		Element gameEv = domHis.createElement("GameEvent");
		Element game = domHis.createElement("Game");
		Element gameE = createGameHisElement(ge.getCurrentGame());
		Iterator pIt = ge.getCurrentPlayers().iterator();
		Element players = domHis.createElement("Players");
		gameEv.appendChild(players);
		while(pIt.hasNext()) {
			Player p = (Player) pIt.next();
			Element playerE = createPlayerHisElement(p);
			Element points = domHis.createElement("points");
			Text pointsTxt = domHis.createTextNode(new Integer(ge.getPoints(p)).toString());
			points.appendChild(pointsTxt);
			playerE.appendChild(points);
			players.appendChild(playerE);
		}
		game.appendChild(gameE);
		gameEv.appendChild(game);
		Element winner = domHis.createElement("Winner");
		Element winnerP = createPlayerHisElement(ge.getWinner());
		winner.appendChild(winnerP);
		gameEv.appendChild(winner);
		
		return gameEv;
	}
	
	private void printToFile() {
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(dom);
			StreamResult result = new StreamResult("center.xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
			
			
			
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printHistorytoFile() {
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(domHis);
			StreamResult result = new StreamResult("history.xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
			
			
			
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseXmlFile(String file) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			domRead = db.parse(file);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseDocument() {
		domRead.getDocumentElement().normalize();
		parsePlayers();
		parseGames();
		parsePlaysWhats();
	}
	
	private void parsePlayers() {
		NodeList nl = domRead.getElementsByTagName("Players");
		Element players = (Element) nl.item(0);
		NodeList plList = players.getElementsByTagName("Player");
		if(plList != null && plList.getLength() > 0) {
			for(int i = 0; i < plList.getLength(); i++) {
				Element elPlayer = (Element)plList.item(i);
				MainFrame.MAIN.getCenter().addPlayer(getPlayer(elPlayer));
				
			}
		}
	}
	
	private void parseGames() {
		NodeList nl = domRead.getElementsByTagName("Games");
		Element games = (Element) nl.item(0);
		NodeList gnl = games.getElementsByTagName("Game");
		if(gnl != null && gnl.getLength() > 0) {
			for(int i = 0; i < gnl.getLength(); i++) {
				Element elGame = (Element)gnl.item(i);
				MainFrame.MAIN.getCenter().addGame(getGame(elGame));
			}
		}
	}
	
	private void parsePlaysWhats() {
		NodeList nl = domRead.getElementsByTagName("PlaysWhats");
		Element playsWhats = (Element) nl.item(0);
		NodeList pwNl = playsWhats.getElementsByTagName("PlaysWhat");
		if(pwNl != null && pwNl.getLength() > 0) {
			for(int i = 0; i < pwNl.getLength(); i++) {
				Element elPW = (Element)pwNl.item(i);
				MainFrame.MAIN.getCenter().addPlaysWhat(getPlaysWhat(elPW));
			}
		}
	}
	
	private void parseHistory() {
		NodeList nl = domRead.getElementsByTagName("History");
		Element gameEvents = (Element) nl.item(0);
		NodeList geNl = gameEvents.getElementsByTagName("GameEvent");
		if(geNl != null && geNl.getLength() > 0) {
			for(int i = 0; i < geNl.getLength(); i++) {
				Element elGe = (Element)geNl.item(i);
				MainFrame.MAIN.getCenter().addHistoryEvent(getGameEvent(elGe));
			}
		}
	}
	
	private Game getGame(Element elGame) {
		String name = getTextValue(elGame, "name");
		String type = getTextValue(elGame, "type");
		int maxAnzPlayer = getIntValue(elGame, "maxAnzPlayer");
		int isPlayed = getIntValue(elGame, "isPlayed");
		Game game = new Game(name, type, maxAnzPlayer);
		game.setIsPlayed(isPlayed);
		return game;
		
	}

	private Player getPlayer(Element el) {
		String vorname = getTextValue(el, "vorname");
		String name = getTextValue(el, "name");
		String nickname = getTextValue(el, "nickname");
		Player player = new Player(vorname, name, nickname);
		return player;
		
	}
	
	private GameEvent getGameEvent(Element el) {
		Game game = getGame((Element)el.getElementsByTagName("Game").item(0));
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Integer> points = new ArrayList<Integer>();
		NodeList nl = el.getElementsByTagName("Players");
		Element playerList = (Element) nl.item(0);
		for(int i = 0; i < nl.getLength(); i++) {
			players.add(getPlayer((Element)playerList.getElementsByTagName("Player").item(i)));
			points.add(getIntValue((Element)playerList.getElementsByTagName("Player").item(0), "points"));
		}
		Player winner = getPlayer((Element)el.getElementsByTagName("Winner").item(0));
		GameEvent ge = new GameEvent(game, players);
		ge.setWinner(winner);
		ge.setPoints(points);
		return ge;
	}
	
	private PlaysWhat getPlaysWhat(Element el) {
		Player p = getPlayer((Element)el.getElementsByTagName("Player").item(0));
		Game g = getGame((Element)el.getElementsByTagName("Game").item(0));
		int count = getIntValue(el, "count");
		int wins = getIntValue(el, "wins");
		PlaysWhat pw = new PlaysWhat(p,g);
		pw.setCount(count);
		pw.setWins(wins);
		return pw;
	}
	
	private String getTextValue(Element ele, String tag) {
		return ele.getElementsByTagName(tag).item(0).getTextContent();
		
	}
	
	private int getIntValue(Element ele, String tag) {
		return Integer.parseInt(getTextValue(ele, tag));
	}
	
}

