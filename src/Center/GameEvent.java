package Center;
import java.util.ArrayList;


public class GameEvent {

	private Game game;
	private ArrayList<Player> players;
	private ArrayList<Integer> points;
	private Player winner;
	public GameEvent(Game game, ArrayList<Player> players) {
		//TODO Fehelermeldung: Spieleranzahl darf max. Anzahl nicht überschreiten.
		
		this.game = game;
		this.players = players;
		this.winner = null;
		this.points = new ArrayList<Integer>();
		for(int i = 0; i < players.size(); i++) {
		points.add(0);
		}
		game.erhoeheIsPlayed();
	}
	
		
	public ArrayList<Player> getCurrentPlayers() {
		return players;
	}
	
	public Game getCurrentGame() {
		return game;
	}
	
	public int getPoints(Player player) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getNickname().equals(player.getNickname())) {
				return points.get(i);
			}
		}
	     return 0;
	}
	
	public ArrayList<Integer> getPointsList() {
		return points;
	}
	
	public void setPoints(ArrayList<Integer> pointList) {
		this.points = pointList;
	}
	public void setWinner(Player player) {
		this.winner = player;
	}
	
		
	public void setPoints(int value, Player player) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getNickname().equals(player.getNickname())) {
				points.add(i, value);
			}
		}
	} 
	
	public String printPlayers() {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < players.size(); i++) {
			sb.append(players.get(i).getNickname()).append("(")
			.append(points.get(i)).append(")").append("\n");
		}
		return sb.toString();
	}
	
	public Player getWinner() {
		return winner;
	}
	
}
