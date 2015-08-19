package Center;
import java.util.ArrayList;


public class Player {
	
	private String name;
	private String vorname;
	private String nickname;
	
	public Player (String vorname, String name, String nickname) {
		this.vorname = vorname;
		this.name = name;
		this.nickname = nickname;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String newName) {
		this.nickname = newName;
	}
	
	public String toString() {
		return "Name: "+ name + "\nVorname: " + vorname + "\nProfilname: " + nickname+"\n";
	}
}
