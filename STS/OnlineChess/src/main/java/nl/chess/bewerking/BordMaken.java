package nl.chess.bewerking;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.chess.database.Bord;
import nl.chess.database.SchaakVeld;
import nl.chess.repository.BordManipulatie;
import nl.chess.repository.VeldManipulatie;

@RestController
public class BordMaken {
	@Autowired
	private VeldManipulatie dataVeld;
	@Autowired
	private BordManipulatie dataBord;
	
	@RequestMapping("bordmaken")
	public Bord createBord(){
		Bord bord = new Bord();
		List<SchaakVeld> lijst = new ArrayList<SchaakVeld>();
		
		for(int xcoord=0;xcoord<8;xcoord++){
			for(int ycoord=0;ycoord<8;ycoord++){
				List<Integer> coords = new ArrayList<Integer>();
				coords.add(xcoord); coords.add(ycoord); 
				lijst.add(createVeld(coords));
			}
		}
		
		bord.setVelden(lijst);
		
		return dataBord.save(bord);		
	}
	
	
	@RequestMapping("veldmaken")
	public SchaakVeld createVeld(List<Integer> coords){
		SchaakVeld veld = new SchaakVeld();
		veld.setCoords(coords);
		veld.setVakNaam("Veld" + coords.get(0) + "" + coords.get(1));
		return dataVeld.save(veld);
	}
		
}
