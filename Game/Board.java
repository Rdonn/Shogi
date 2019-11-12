package Game;

public class Board {
	Piece gameBoard[][]; 
	public Board() throws Exception {
		this.gameBoard = new Piece[][] {
			{ PieceFactory.getLance("down", ""), PieceFactory.getKnight("down", ""),
					PieceFactory.getSilverGeneral("down", ""), PieceFactory.getGoldGeneral("down", ""),
					PieceFactory.getKing("down", ""), PieceFactory.getGoldGeneral("down", ""),
					PieceFactory.getSilverGeneral("down", ""), PieceFactory.getKnight("down", ""),
					PieceFactory.getLance("down", "") },
			{ null, PieceFactory.getBishop("down", ""), null, null, null, null, null,
					PieceFactory.getRook("down", ""), null },
			{ PieceFactory.getPawn("down", ""), PieceFactory.getPawn("down", ""), PieceFactory.getPawn("down", ""),
					PieceFactory.getPawn("down", ""), PieceFactory.getPawn("down", ""),
					PieceFactory.getPawn("down", ""), PieceFactory.getPawn("down", ""),
					PieceFactory.getPawn("down", ""), PieceFactory.getPawn("down", "") },
			{ null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null },
			{ PieceFactory.getPawn("up", ""), PieceFactory.getPawn("up", ""), PieceFactory.getPawn("up", ""),
					PieceFactory.getPawn("up", ""), PieceFactory.getPawn("up", ""), PieceFactory.getPawn("up", ""),
					PieceFactory.getPawn("up", ""), PieceFactory.getPawn("up", ""),
					PieceFactory.getPawn("up", "") },
			{ null, PieceFactory.getBishop("up", ""), null, null, null, null, null, PieceFactory.getRook("up", ""),
					null },
			{ PieceFactory.getLance("up", ""), PieceFactory.getKnight("up", ""),
					PieceFactory.getSilverGeneral("up", ""), PieceFactory.getGoldGeneral("up", ""),
					PieceFactory.getKing("up", ""), PieceFactory.getGoldGeneral("up", ""),
					PieceFactory.getSilverGeneral("up", ""), PieceFactory.getKnight("up", ""),
					PieceFactory.getLance("up", "") } };
	}
	
	public void printBoard() {
		for (Piece[] pieces : this.gameBoard) {
			for (Piece piece : pieces) {
				if (piece != null) {
					System.out.print(piece.getId().charAt(0) + " ");
				} else {
					System.out.print("_ ");
				}
			}
			System.out.println();
		}
	}
	
	public Piece[][] getGameBoard() {
		return this.gameBoard;
	}
	
}
