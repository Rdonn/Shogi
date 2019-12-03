package GameLogic;

import Game.Board;
import Game.Game;
import Game.Piece;

public class GameLogic {
	public static boolean handleMove(Game referenceGame, Game defactoGame, String playerThatJustWent) {
		for (int[] iterable_element : defactoGame.getMoveSet()) {
			System.out.println(String.format("y:%d, x:%d", iterable_element[0], iterable_element[1]));
		}
		
		Piece piece = defactoGame.getBoard().getGameBoard()[defactoGame.getMoveSet()[1][0]][defactoGame.getMoveSet()[1][1]];
		defactoGame.getBoard().printBoard();
		System.out.println(piece.getId());
		for (Integer[] iterable_element : piece.getMoveset()) {
			System.out.println(String.format("y:%d, x:%d, distance:%d", iterable_element[0], iterable_element[1], iterable_element[2]));
		}
		String direction; 
		if (playerThatJustWent.contentEquals(defactoGame.getPlayerOne())) {
			direction = defactoGame.getPlayerOneAlias(); 
		}
		else {
			direction = defactoGame.getPlayerTwoAlias(); 
		}
		
		//first determine if the move was within the legal moves list
		
		//the move was from a to b
		
		boolean legalPosition = false; 
		
		//first get a
		int[] a = defactoGame.getMoveSet()[0]; 
		
		//next get b
		int[] b = defactoGame.getMoveSet()[1]; 
		
		//now get the moveset of the piece
		Integer[][] moveset = piece.getMoveset(); 
		
		//now calculate wether or not it is possible to get to b from a
		
		//establish the possibility
		for (Integer[] integers : moveset) {
			int[] currentPosition = {a[0], a[1]};
			System.out.println(String.format("Current position: {%s, %s}", currentPosition[0], currentPosition[1]));
			int y = integers[0]; 
			int x = integers[1]; 
			int distance = integers[2]; 
			
			//now...
			
			for (int i = 0; i < distance; i++) {
				currentPosition[0] += y; 
				currentPosition[1] += x; 
				System.out.println(String.format("Checking [%s, %s]", currentPosition[0], currentPosition[1]));
				try {
					if (referenceGame.getBoard().getGameBoard()[currentPosition[0]][currentPosition[1]] != null) {
						if(!referenceGame.getBoard().getGameBoard()[currentPosition[0]][currentPosition[1]].getDirection().contentEquals(direction)) {
							//this will hit for an attack
							legalPosition = true; 
						}
						break; 
					}
				} catch (Exception e) {
					System.out.println("Error");
					break; 
				}
				
				if (currentPosition[0] == b[0] && currentPosition[1] == b[1]) {
					System.out.println("Legal position found " + currentPosition[0] + " " +currentPosition[1]);
					legalPosition = true; 
					break; 
				}
			}
			if (legalPosition == true) {
				break; 
			}
			
			
		}
		System.out.println("Legal position: " + legalPosition);
		//did they move on top of one of their own pieces??
		
		boolean avoidedTeam = false; 
		Piece fromPiece = referenceGame.getBoard().getGameBoard()[a[0]][a[1]]; 
		Piece toPiece = referenceGame.getBoard().getGameBoard()[b[0]][b[1]]; 
		
		if (toPiece == null) {
			avoidedTeam = true; 
		}
		else {
			if (toPiece.getDirection().equals(direction)) {
				avoidedTeam = false; 
			}
			//piece got taken out
			defactoGame.getPieces().add(toPiece); 
			avoidedTeam = true; 
		}
		
		//now lets check if they won
		if (legalPosition && avoidedTeam) {
			if (referenceGame.getBoard().getGameBoard()[b[0]][b[1]] != null) {
				if (referenceGame.getBoard().getGameBoard()[b[0]][b[1]].isKing()) {
					//they just captured the king
					System.out.println("Winner detected");
					defactoGame.setWinner(playerThatJustWent);
				}
			}
		}
		
		return legalPosition && avoidedTeam; 
	}
	public static boolean handlePromotion(Game defactoGame, String playerThatJustWent) {
		Board board = defactoGame.getBoard(); 
		int[] location = defactoGame.getPromotionLocation(); 
		
		//get the direction 
		String direction; 
		if (playerThatJustWent.contentEquals(defactoGame.getPlayerOne())) {
			direction = defactoGame.getPlayerOneAlias(); 
		}
		else {
			direction = defactoGame.getPlayerTwoAlias(); 
		}
		
		if (board.getGameBoard()[location[0]][location[1]].isPromotable()) {
			if (direction.contentEquals("up") && location[0] <= 2) {
				if (!board.getGameBoard()[location[0]][location[1]].getisPromoted()) {
					board.getGameBoard()[location[0]][location[1]].promote();
					return true; 
				}
				else {
					return false; 
				}
			}
			else if (direction.contentEquals("down") && location[0] >= 6) {
				if (!board.getGameBoard()[location[0]][location[1]].getisPromoted()) {
					board.getGameBoard()[location[0]][location[1]].promote();
					return true; 
				}
				else {
					return false; 
				}
			}
			else {
				return false; 
			}
			
		}
		else {
			return false; 
		}
			
	}
	public static boolean handleMoveFromJail(Game defactoGame, String playerThatJustWent) {
		
		System.out.println();
		return false; 
	}
	
	
}
