package Center;


public class PlaysWhat {
	
	private Player player;
	private Game game;
	private int count;
	private int wins;
	
	public PlaysWhat(Player player, Game game) {
		this.player = player;
		this.game = game;
		this.count = 1;
		this.wins = 0;
		
	}
	
	public void erhoeheCount() {
		this.count = getCount()+1;
	}
	
	
	
	public void setCount(int newCount) {
		this.count = newCount;
	}
	
	public int getCount() {
		return count;
	}
	
	public void playerWins(Player player) {
		this.wins = getWins()+1;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void setWins(int newWins) {
		this.wins = newWins;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Game getGame() {
		return game;
	}
	

}
