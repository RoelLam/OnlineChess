package nl.chess.bewerking;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.chess.database.Bord;
import nl.chess.database.ChessColor;
import nl.chess.database.ChessType;
import nl.chess.database.SchaakStuk;
import nl.chess.repository.BordManipulatie;
import nl.chess.repository.StukManipulatie;

@RestController
@RequestMapping("zetten")
public class StukMaken {
	@Autowired
	private StukManipulatie dataStuk;
	@Autowired
	private BordManipulatie dataBord;
	
	@CrossOrigin("http://localhost:8081")
	@RequestMapping("bordmaken")
	public Bord createBord(){
		Bord b = new Bord();
		b = dataBord.save(b);
		
		for (ChessColor color : ChessColor.values()) {
			for (ChessType schaakstukType : ChessType.values()) {
				for (Integer kolom : schaakstukType.getKolommen()) {
					SchaakStuk stuk = new SchaakStuk();
					stuk.setBord(b);
					stuk.setType(schaakstukType);
					if(stuk.getType()==ChessType.KING || stuk.getType()==ChessType.ROOK){
						stuk.setRokeren(true);
					}else{
						stuk.setRokeren(false);
					}
					stuk.setColor(color);
					stuk.setOnBoard(true);
					List<Integer> coordinaten = schaakstukType.getCoordinaten(color, kolom);
					stuk.setCoords(coordinaten);
					stuk = dataStuk.save(stuk);
					b.getSchaakStukken().add(stuk);
					b = dataBord.save(b);
				}
			}
		}
		return b;
	}

	@CrossOrigin("http://localhost:8081")
	@RequestMapping(value = "zet/{schaakstukId}/{x}/{y}", method = RequestMethod.GET)
	public String zet(@PathVariable Long schaakstukId, @PathVariable Integer x, @PathVariable Integer y) {
		SchaakStuk stuk = dataStuk.findOne(schaakstukId);
		String message = new String();
		if(stuk.getOnBoard()){
		
			Boolean kleurMag = juisteKleur(stuk);
			if(kleurMag){
				Boolean zetMag = magZetten(stuk, x,y);
			
				if(zetMag){
					Integer xOud = stuk.getCoords().get(0);
					Integer yOud = stuk.getCoords().get(1);
				
					SchaakStuk geslagenStuk = stuk.getType().staatErStuk(stuk.getBord(),Arrays.asList(x,y));
					if(geslagenStuk != null){
						geslagenStuk.setOnBoard(false);
						dataStuk.save(geslagenStuk);
					}else if(stuk.getType()==ChessType.PAWN && yOud - y != 0){
						geslagenStuk = stuk.getType().staatErStuk(stuk.getBord(),Arrays.asList(xOud,y));
						geslagenStuk.setOnBoard(false);
						dataStuk.save(geslagenStuk);
					}
				
					stuk.getCoords().clear();
					stuk.getCoords().add(x);
					stuk.getCoords().add(y);
					dataStuk.save(stuk);
				
					if(staatSchaak(stuk.getBord(),stuk.getColor())){
						message = "Let op! Schaak!";
						zetMag = false;
					}
					
					if(!zetMag){
						stuk.getCoords().clear();
						stuk.getCoords().add(xOud);
						stuk.getCoords().add(yOud);
					
						if(geslagenStuk != null){
							geslagenStuk.setOnBoard(true);
							dataStuk.save(geslagenStuk);
						}
						dataStuk.save(stuk);
					}else{
						if(stuk.getType()==ChessType.KING){
							if(y-yOud == -2){
								SchaakStuk toren = stuk.getType().staatErStuk(stuk.getBord(), Arrays.asList(x,0));
								toren.getCoords().clear();
								toren.getCoords().add(x);
								toren.getCoords().add(3);
								dataStuk.save(toren);
							}
							if(y-yOud == 2){
								SchaakStuk toren = stuk.getType().staatErStuk(stuk.getBord(), Arrays.asList(x,7));
								toren.getCoords().clear();
								toren.getCoords().add(x);
								toren.getCoords().add(5);
								dataStuk.save(toren);
							}						
						}	
						
						if(stuk.getType()==ChessType.PAWN && Math.abs(x-xOud)==2){
								stuk.getBord().setEnPassant(y);		
						}else{
							stuk.getBord().setEnPassant(-1);
						}
						
						dataBord.save(stuk.getBord());
						stuk.setRokeren(false);
						stuk = dataStuk.save(stuk);
						andereKleurAanDeBeurt(stuk);
						message = zetMag.toString();
						
			
					}	
				}else{
					message = "Illegale zet!";
				}

			}
			else{
				message = "Foute kleur!";
			}
		}else{
			message = "Onbestaand stuk!";
		}
		
		return message;
	}
		
	@CrossOrigin("http://localhost:8081")
	@RequestMapping(value="bord/{bordId}", method = RequestMethod.GET)
	public Bord geefBestaandBord(@PathVariable Long bordId){
		return dataBord.findOne(bordId);
	}
	
	@CrossOrigin("http://localhost:8081")
	@RequestMapping(value="isKleurAanDeBeurt/{schaakstukId}", method = RequestMethod.GET)
	public String isKleurAanDeBeurt(@PathVariable Long schaakstukId){
		if(dataStuk.findOne(schaakstukId).getColor() == dataStuk.findOne(schaakstukId).getBord().getAanDeBeurt()){
			return "True";
		}
		else{
			return "False";
		}
	}
	
	@CrossOrigin("http://localhost:8081")
	@RequestMapping("verander/{schaakstukId}/{type}")
	public SchaakStuk changePiece(@PathVariable Long schaakstukId, @PathVariable ChessType type) {
		SchaakStuk s = dataStuk.findOne(schaakstukId);
		s.setType(type);
		return dataStuk.save(s);		
	}
	
	public boolean magZetten(SchaakStuk stuk, Integer x, Integer y){
		return stuk.getType().kanNaar(stuk, Arrays.asList(x, y));
	}
	
	public boolean juisteKleur(SchaakStuk stuk){
		return (stuk.getColor() == stuk.getBord().getAanDeBeurt());
	}
	

	public void andereKleurAanDeBeurt(SchaakStuk stuk){
		Bord b = stuk.getBord();
		if(b.getAanDeBeurt() == ChessColor.WHITE){
			b.setAanDeBeurt(ChessColor.BLACK);
		}
		else{
			b.setAanDeBeurt(ChessColor.WHITE);
		}
		dataBord.save(b);
		return;
	}
	
	public ChessColor andereKleur(ChessColor color){
		if (color==ChessColor.WHITE){
			return ChessColor.BLACK;
		}else {
			return ChessColor.WHITE;
		}
	}
	
	public boolean staatSchaak(Bord bord, ChessColor color){
		List<Integer> coordsKoning = Arrays.asList(0,4);
		
		for(SchaakStuk stuk : bord.getSchaakStukken()){
			if(stuk.getColor()==color && stuk.getType() == ChessType.KING){
				coordsKoning = stuk.getCoords();
			}
		}		
				
		for(SchaakStuk stuk : bord.getSchaakStukken()){
			if(stuk.getColor()==andereKleur(color) && stuk.getOnBoard()){
				if(stuk.getType().kanNaar(stuk,coordsKoning)){
					return true;
				}
			}
		}	
		return false;
	}
}