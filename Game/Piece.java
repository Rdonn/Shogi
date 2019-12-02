package Game;

import java.io.Serializable;
import java.util.Arrays;

public class Piece implements Serializable{

	private final String id;
	private boolean isPromoted = false;
	private boolean promotable;
	private boolean isKing;
	private Integer[][] moveset;
	private Integer[][] promotedMoveset;
	private String direction;
	private String owner; 

	public Piece(String id, boolean promotable, boolean isKing, String direction, Integer[][] moveset,
			Integer[][] promotedMoveset, String owner) throws Exception{
		this.id = id;
		this.promotable = promotable;
		this.isKing = isKing;
		this.moveset = moveset;
		this.promotedMoveset = promotedMoveset; 
		this.owner = owner; 
		
		//need to make sure that the direction is okay
		//this throwable CAN BE DELETED later
		
		if (!direction.equals("up") && !direction.equals("down")) {
			System.out.println(this.direction);
			throw new Exception("Direction must be 'up' or 'down'"); 
		}
		
		this.direction = direction;
	}

	public void promote() {
		if (this.promotable && !this.isPromoted) {
			this.isPromoted = true;
		}

	}

	public void unpromote() {
		if (this.isPromoted) {
			this.isPromoted = false;
		}
	}

	public Integer[][] getMoveset() {
		if (this.isPromoted) {
			return this.checkMoveset(this.promotedMoveset);
		} else {
			return this.checkMoveset(this.moveset);
		}
	}

	//will return a copy of the moveset
	//dependent on direction the player 
	private Integer[][] checkMoveset(Integer[][] moveSet) {
		
		Integer[][] tempMoveset = new Integer[moveSet.length][3]; 
		for(int i = 0; i < moveSet.length; i++) {
			tempMoveset[i] = moveSet[i].clone(); 
		}
		if (this.direction.equals("down")) {
			for (Integer[] integers : tempMoveset) {
				// reflection of the moveset for moving down (y-axis)
				integers[0] = integers[0] * -1;
			}
		}
		return tempMoveset;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getOwner() {
		return owner;
	}

	
	public String getId() {
		return id;
	}
	
	public boolean isPromotable() {
		return promotable;
	}

	public boolean isKing() {
		return isKing;
	}
	public String getDirection() {
		return direction;
	}
	public boolean getisPromoted() {
		// TODO Auto-generated method stub
		return this.isPromoted; 

	}
	public Integer[][] getPromotedMoveset() {
		return promotedMoveset;
	}
	public void printMoveset() {
		// TODO Auto-generated method stub
		if (this.isPromotable()) {
			if (this.isPromoted) {
				for (Integer[] integers : this.checkMoveset(this.promotedMoveset)) {
					System.out.println(Arrays.toString(integers));
				}
				return;
			}
		}
		
		for (Integer[] integers :this.checkMoveset(this.moveset)) {
			System.out.println(Arrays.toString(integers));
		}
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format(
				 "Owner: %s\n"
				+"Piece: %s\n"
				+"Direction: %s\n"
				+"Promoted: %s\n", getOwner(), getId(), getDirection(), getisPromoted()); 
	}

}
