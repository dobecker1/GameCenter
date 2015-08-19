package Center;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="center")
public class Center {
	
	private ArrayList<PlaysWhat> playsWhat;
	private LinkedList<Player> players;
	private LinkedList<Game> games;
	private LinkedList<GameEvent> spieleBuffer;
	private LinkedList<GameEvent> history;

	public Center() {
		this.playsWhat = new ArrayList<PlaysWhat>();
		this.players = new LinkedList<Player>();
		this.games = new LinkedList<Game>();
		this.spieleBuffer = new LinkedList<GameEvent>();
		this.history = new LinkedList<GameEvent>();
	}
	
	public void addPlayer( String vorname,String name, String nickname) {
		players.add(new Player(vorname, name, nickname));
	}	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void addGame(String name, String type, int maxAnzPlayer) {
		games.add(new Game(name,type,maxAnzPlayer));
	}
	public void addGame(Game game) {
		games.add(game);
	}
		
	public void addGameEvent(Game game, ArrayList<Player> players) {
		spieleBuffer.add(new GameEvent(game, players));
		for(int i = 0; i < players.size(); i++) {
		    if(!existPlaysWhat(players.get(i), game)) {
		    	playsWhat.add(new PlaysWhat(players.get(i), game));
		    }
		    else { for(int j = 0; j < playsWhat.size(); j++) {
				       if(playsWhat.get(j).getPlayer().getNickname().equals(players.get(i).getNickname())
				    		   && playsWhat.get(j).getGame().getName().equals(game.getName())) {
				    	   playsWhat.get(j).erhoeheCount();
				    	   playsWhat.get(j).getGame().erhoeheIsPlayed();
				       }
				 }
		    }
		}
	}
	
	public void addHistoryEvent(GameEvent ge) {
		history.add(ge);
	}
	
	public void addPlaysWhat(Player player, Game game) {
		playsWhat.add(new PlaysWhat(player, game));
	}
	public void addPlaysWhat(PlaysWhat pw) {
		playsWhat.add(pw);
	}
	
	
	
	public void removePlayer(String nickname) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getNickname().equals(nickname) ) {
				players.remove(i);
			}
		}
	}
	
	public void removeGame(String name) {
		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getName().equals(name) ) {
				games.remove(i);
			}
		}
	}

	public boolean existPlaysWhat(Player player, Game game) {
		for(int i = 0; i <playsWhat.size(); i++) {
			if(playsWhat.get(i).getPlayer().getNickname().equals(player.getNickname()) &&
					playsWhat.get(i).getGame().getName().equals(game.getName())) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	public boolean existPlayer(String nickname) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getNickname().equals(nickname)) {
				return true;
			}
		}
		return false;
	}
	
	public PlaysWhat getPlaysWhat(Player player, Game game) {
		for(int i = 0; i <playsWhat.size(); i++) {
			if(playsWhat.get(i).getPlayer().getNickname().equals(player.getNickname()) &&
					playsWhat.get(i).getGame().getName().equals(game.getName())) {
				return playsWhat.get(i);
			}
		}
		return null;
	}
	
	public boolean existGame(String name) {
		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void beendeSpiel(GameEvent gameEvent) {
		history.add(gameEvent);
		spieleBuffer.remove(gameEvent);
	}
	
	
	public LinkedList<GameEvent> getHistory() {
		return history;
	}
	
	public int getGesamtAnzSpieler() {
		return this.players.size();
	}
	
	public int getGesamtAnzGames() {
		return this.games.size();
	}
	
	public ArrayList<PlaysWhat> getPlaysWhat() {
		return playsWhat;
	}
	
	public LinkedList<Player> getPlayers() {
		return players;
	}
	
	public LinkedList<Game> getGames() {
		return games;
	}
	
	public LinkedList<GameEvent> getSpieleBuffer() {
		return spieleBuffer;
	}
	
	public void setPlaysWhat(ArrayList<PlaysWhat> playsWhat) {
		this.playsWhat = playsWhat;
	}

	public void setPlayers(LinkedList<Player> players) {
		this.players = players;
	}

	public void setGames(LinkedList<Game> games) {
		this.games = games;
	}

	public void setSpieleBuffer(LinkedList<GameEvent> spieleBuffer) {
		this.spieleBuffer = spieleBuffer;
	}

	public void setHistory(LinkedList<GameEvent> history) {
		this.history = history;
	}

	public GameEvent getCurrentGameEvent() {
		return spieleBuffer.get(0);
	}
	
}
