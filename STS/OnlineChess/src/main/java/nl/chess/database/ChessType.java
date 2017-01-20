package nl.chess.database;

import java.util.Arrays;
import java.util.List;

public enum ChessType {
	PAWN(null, null, 1, 0, 1, 2, 3, 4, 5, 6, 7) {
		@Override
		public boolean kanNaar(List<Integer> currentCords, List<Integer> newCords, ChessColor color) {
			return super.kanNaar(currentCords, newCords, color);
		}
		
	}, 
	ROOK(false, Richting.RECHT, 0, 0, 7), 
	KNIGHT(false, Richting.PAARDENSPRONG, 0, 1, 6), 
	BISHOP(false, Richting.SCHUIN, 0, 2, 5), 
	QUEEN(false, Richting.BEIDEN, 0, 3), 
	KING(true, Richting.BEIDEN, 0, 4);
	
	private final Integer rij;
	private final Integer[] kolommen;
	private final int NUMBER_OF_ROWS_MINUS_ONE = 7;
	private final Boolean eenPlaats;
	private final Richting richting;
	
	private ChessType(Boolean eenPlaats, Richting richting, Integer rij, Integer... kolommen) {
		this.eenPlaats = eenPlaats;
		this.richting = richting;
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

	public boolean kanNaar(List<Integer> currentCords, List<Integer> newCords, ChessColor color) {
		boolean hetKan = false;
		Integer xverschil = currentCords.get(0)-newCords.get(0);
		Integer yverschil = currentCords.get(1) - newCords.get(1);
		
		if (richting == Richting.RECHT || richting == Richting.BEIDEN){
			
			if (xverschil==0 ^ yverschil==0){
				if(eenPlaats && Math.abs(xverschil + yverschil) == 1){
					hetKan = true;
				}else if(!eenPlaats){
					hetKan = true;
				}

			}
		}
		if (richting == Richting.SCHUIN || richting == Richting.BEIDEN){

			if (Math.abs(xverschil) == Math.abs(yverschil)){
				if(eenPlaats && xverschil == 1){
					hetKan=true;
				}else if(!eenPlaats && Math.abs(xverschil)>0){
					hetKan=true;
				}

			}
		}		
		if(richting == Richting.PAARDENSPRONG){
			if((xverschil*xverschil + yverschil*yverschil) == 5){
				hetKan=true;
			}
		}
		if(hetKan) System.out.println("true"); else System.out.println("false");	
		return hetKan;
	}
	
	private enum Richting {
		RECHT, SCHUIN, BEIDEN, PAARDENSPRONG;
	}
}
