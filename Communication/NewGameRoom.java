package Communication;

import java.io.Serializable;

public class NewGameRoom implements Serializable{
	String name; 
	public NewGameRoom(String name) {
		// TODO Auto-generated constructor stub
		this.name = name; 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
