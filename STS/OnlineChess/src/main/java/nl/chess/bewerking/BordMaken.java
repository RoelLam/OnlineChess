package nl.chess.bewerking;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.chess.database.Bord;
import nl.chess.database.SchaakStuk;
import nl.chess.repository.BordManipulatie;
import nl.chess.repository.StukManipulatie;

@RestController
public class BordMaken {
	@Autowired
	private StukManipulatie dataStuk;
	@Autowired
	private BordManipulatie dataBord;
	
	@RequestMapping("bordmaken")
	public Bord createBord(){
		Bord bord = new Bord();
		List<SchaakStuk> lijst = new ArrayList<SchaakStuk>();
		
		for(int xcoord=0;xcoord<8;xcoord++){
			for(int ycoord=0;ycoord<8;ycoord++){
				List<Integer> coords = new ArrayList<Integer>();
				coords.add(xcoord); coords.add(ycoord); 
				lijst.add(createSchaakstuk(coords));
			}
		}
		
		bord.setSchaakStukken(lijst);
		
		return dataBord.save(bord);		
	}
	
	
	@RequestMapping("veldmaken")
	public SchaakStuk createSchaakstuk(List<Integer> coords){
		SchaakStuk stuk = new SchaakStuk();
		stuk.setCoords(coords);
		return dataStuk.save(stuk);
	}
		
}
