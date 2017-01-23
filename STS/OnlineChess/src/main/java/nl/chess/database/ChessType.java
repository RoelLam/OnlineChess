package nl.chess.database;

import java.util.Arrays;
import java.util.List;

public enum ChessType {
	PAWN(null, null, 1, 0, 1, 2, 3, 4, 5, 6, 7) {
		@Override
		public boolean kanNaar(SchaakStuk stuk, List<Integer> newCords) {
			Boolean hetKan = false;
			
			Integer xStart  = stuk.getCoords().get(0);
			Integer yStart = stuk.getCoords().get(1);
			Integer xEind = newCords.get(0);
			Integer yEind = newCords.get(1);
			Integer xVerschil = xEind - xStart;
			Integer yVerschil = yEind - yStart;

			Integer kleur = stuk.getColor() == ChessColor.BLACK ? 1 : -1;
			
			if(yVerschil==0){
				if(xVerschil*kleur == 1){
					hetKan = true;
				}else if(xVerschil*kleur == 2 && (xStart==kleur || (xStart==6 && kleur==-1))){
					Boolean inDeWeg = staatErStuk(stuk.getBord(),Arrays.asList(xStart+kleur,yStart));
					if (!inDeWeg){
						hetKan = true;						
					}
				}
			}			
			return hetKan;
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

	public Boolean staatErStuk(Bord bord, List<Integer> coords){
		for (SchaakStuk stuk : bord.getSchaakStukken()) {
			if (stuk.getCoords().get(0) == coords.get(0) && stuk.getCoords().get(1) == coords.get(1)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean staatInDeWeg(SchaakStuk stuk, List<Integer> newCords){
		
		Integer xStart  = stuk.getCoords().get(0);
		Integer yStart = stuk.getCoords().get(1);
		Integer xEind = newCords.get(0);
		Integer yEind = newCords.get(1);
		Integer xVerschil = xEind - xStart;
		Integer yVerschil = yEind - yStart;
		Integer xInc = (xVerschil > 0) ? 1 : (xVerschil == 0) ? 0 : -1;
		Integer yInc = (yVerschil > 0) ? 1 : (yVerschil == 0) ? 0 : -1;
		
		
		for(int x=1, y = 1;  x < Math.abs(xVerschil) || y < Math.abs(yVerschil); x++ ,y++) {
			Boolean veldVol = staatErStuk(stuk.getBord(), Arrays.asList(xStart + x*xInc, yStart + y*yInc));
			if (veldVol) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean kanNaar(SchaakStuk stuk, List<Integer> newCords) {
		boolean hetKan = false;
		
		Integer xStart  = stuk.getCoords().get(0);
		Integer yStart = stuk.getCoords().get(1);
		Integer xEind = newCords.get(0);
		Integer yEind = newCords.get(1);
		Integer xVerschil = xEind - xStart;
		Integer yVerschil = yEind - yStart;
		

		
		if (richting == Richting.RECHT || richting == Richting.BEIDEN){
			
			if (xVerschil==0 ^ yVerschil==0){
				if(eenPlaats && Math.abs(xVerschil + yVerschil) == 1){
					hetKan = true;
				}else if(!eenPlaats){
					Boolean inDeWeg = staatInDeWeg(stuk,newCords);
					if (!inDeWeg) {
						hetKan = true;
					}
				}

			}
		}
		if (richting == Richting.SCHUIN || richting == Richting.BEIDEN){

			if (Math.abs(xVerschil) == Math.abs(yVerschil)){
				if(eenPlaats && xVerschil == 1){
					hetKan=true;
				}else if(!eenPlaats && Math.abs(xVerschil)>0){
					Boolean inDeWeg = staatInDeWeg(stuk,newCords);
					if (!inDeWeg) {
						hetKan=true;
					}
				}

			}
		}		
		if(richting == Richting.PAARDENSPRONG){
			if((xVerschil*xVerschil + yVerschil*yVerschil) == 5){
				hetKan=true;
			}
		}
		if(hetKan) System.out.println("true"); else System.out.println("false");	
		return hetKan;
	}
	
	private enum Richting {
		RECHT, SCHUIN, BEIDEN, PAARDENSPRONG;
	}
	
	public boolean juisteKleur(SchaakStuk stuk){
		return (stuk.getColor() == stuk.getBord().getAanDeBeurt());
	}
}
