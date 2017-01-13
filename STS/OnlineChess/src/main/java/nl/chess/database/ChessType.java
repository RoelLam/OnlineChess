package nl.chess.database;

import java.util.Arrays;
import java.util.List;

public enum ChessType {
	PION(1, 0, 1, 2, 3, 4, 5, 6, 7), TOREN(0, 0, 7), PAARD(0, 1, 6), LOPER(0, 2, 5), KONINGIN(0, 3), KONING(0, 4);
	
	private final Integer rij;
	private final Integer[] kolommen;

	private ChessType(Integer rij, Integer... kolommen) {
		this.rij = rij;
		this.kolommen = kolommen;
	}
	
	public List<Integer> getCoordinaten(ChessColor color, Integer kolom) {
		Integer mijnRij = color == ChessColor.WHITE ? rij : 7 - rij;		
		return Arrays.asList(mijnRij, kolom);
	}

	public Integer[] getKolommen() {
		return kolommen;
	}
}
