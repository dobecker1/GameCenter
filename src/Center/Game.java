package Center;
import java.util.ArrayList;


public class Game {
	
	private String name;
	private String type;
	private int maxAnzPlayer;
	private int isPlayed;
	
	public Game(String name, String type, int maxAnzPlayer) {
		this.name = name;
		this.maxAnzPlayer = maxAnzPlayer;
		this.type = type;
		this.isPlayed = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void erhoeheIsPlayed() {
		this.isPlayed = isPlayed + 1;
	}
	
	public void setIsPlayed(int value) {
		this.isPlayed = value;
	}
 	
	public int getGameIsPlayed() {
		return isPlayed;
	}
	
    public int getMaxAnzPlayers() {
    	return maxAnzPlayer;
    }
	public String toString() {
		return "Name: " + name + "\nSpieltyp: " + type + "\nMaximale Anz Spieler: "
	+ maxAnzPlayer + "\n";
	}
}
