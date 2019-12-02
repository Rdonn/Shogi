package Game;

import java.io.Serializable;

public class PieceFactory implements Serializable{

	// defining a moveset

	/*
	 * A moveset will be organized as follows: 
	 * [x] will define a direction and a distance
	 * [x][0] will define the direction on the y axis of the board 
	 * [x][1] will define the direction on the x axis of the board 
	 * [x][2] will define the amount of spaces a piece may move in the given direction
	 * 
	 * this will make the process of determining possible moves and the legality of
	 * moves relatively simple
	 * 
	 */

	public static Piece getKing(String direction, String owner) throws Exception {
		Integer[][] moveset = { { -1, -1, 1 }, { -1, 0, 1 }, { -1, 1, 1 }, { 0, 1, 1 }, { 1, 1, 1 }, { 1, 0, 1 },
				{ 1, -1, 1 }, { 0, -1, 1 } };
		return new Piece("King", false, true, direction, moveset, null, owner);

	}

	public static Piece getGoldGeneral(String direction, String owner) throws Exception {
		Integer[][] moveset = { { -1, -1, 1 }, { -1, 0, 1 }, { -1, 1, 1 }, { 0, 1, 1 }, { -1, 0, 1 }, { 0, -1, 1 } };
		return new Piece("Gold General", false, false, direction, moveset, null, owner);
	}

	public static Piece getSilverGeneral(String direction, String owner) throws Exception {
		Integer[][] moveset = { { -1, -1, 1 }, { -1, 0, 1 }, { 1, 1, 1 },{-1,1,1}, { 1, -1, 1 },{0,1,1},{0,-1,1} };

		Integer[][] promotedMoveset = { { -1, -1, 1 }, { -1, 0, 1 }, { -1, 1, 1 }, { 0, 1, 1 }, { -1, 0, 1 },
				{ 0, -1, 1 } };
		return new Piece("Silver General", true, false, direction, moveset, promotedMoveset, owner);
	}

	public static Piece getKnight(String direction, String owner) throws Exception {
		Integer[][] moveset = { { -2, -1, 1 }, { -2, 1, 1 } };

		Integer[][] promotedMoveset = { { -1, -1, 1 }, { -1, 0, 1 }, { -1, 1, 1 }, { 0, 1, 1 }, { -1, 0, 1 },
				{ 0, -1, 1 } };

		return new Piece("Knight", true, false, direction, moveset, promotedMoveset, owner);
	}

	public static Piece getLance(String direction, String owner) throws Exception {
		Integer[][] moveset = { { -1, 0, 9 } };

		Integer[][] promotedMoveset = { { -1, -1, 1 }, { -1, 0, 1 }, { -1, 1, 1 }, { 0, 1, 1 }, { -1, 0, 1 },
				{ 0, -1, 1 } };
		return new Piece("Lance", true, false, direction, moveset, promotedMoveset, owner);
	}

	public static Piece getBishop(String direction, String owner) throws Exception {
		Integer[][] moveset = { { -1, -1, 9 }, { -1, 1, 9 }, { 1, 1, 9 }, { 1, -1, 9 } };
		Integer[][] promotedMoveset = { { -1, -1, 9 }, { -1, 0, 1 }, { -1, 1, 9 }, { 0, 1, 1 }, { 1, 1, 9 },
				{ 1, 0, 1 }, { 1, -1, 9 }, { 0, -1, 1 } };
		
		return new Piece("Bishop", true, false, direction, moveset, promotedMoveset, owner);
	}

	public static Piece getRook(String direction, String owner) throws Exception {
		Integer[][] moveset = {
				{-1,0,9}, 
				{0,1,9}, 
				{1,0,9}, 
				{0,-1,9}
		}; 
		
		Integer[][] promotedMoveset = {
				{-1,-1,1}, 
				{-1,0,9},
				{-1,1,1}, 
				{0,1,9}, 
				{1,1,1}, 
				{1,0,9}, 
				{1,-1,1}, 
				{0,-1,9}, 
		}; 
		return new Piece("Rook", true, false, direction, moveset, promotedMoveset, owner); 
	}
	
	public static Piece getPawn(String direction, String owner) throws Exception {
		Integer[][] moveset = { { -1, 0, 1 } };
		Integer[][] promotedMoveset = { { -1, -1, 1 }, { -1, 0, 1 }, { -1, 1, 1 }, { 0, 1, 1 }, { -1, 0, 1 },
				{ 0, -1, 1 } };
		return new Piece("Pawn", true, false, direction, moveset, promotedMoveset, owner);
	}

}