package nl.chess.database;

import java.util.Arrays;
import java.util.List;

public enum ChessType {
	PAWN(1, 0, 1, 2, 3, 4, 5, 6, 7), ROOK(0, 0, 7), KNIGHT(0, 1, 6), BISHOP(0, 2, 5), QUEEN(0, 3), KING(0, 4);
	
	private final Integer rij;
	private final Integer[] kolommen;
	private final int NUMBER_OF_ROWS_MINUS_ONE = 7;
	
	private ChessType(Integer rij, Integer... kolommen) {
		this.rij = rij;
		this.kolommen = kolommen;
	}
	
	public List<Integer> getCoordinaten(ChessColor color, Integer kolom) {
		Integer mijnRij = color == ChessColor.BLACK ? rij : NUMBER_OF_ROWS_MINUS_ONE - rij;		
		return Arrays.asList(mijnRij, kolom);
	}

	public Integer[] getKolommen() {
		return kolommen;
	}
}
