package nl.chess.bewerking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.chess.database.SchaakStuk;
import nl.chess.repository.StukManipulatie;
import nl.chess.repository.VeldManipulatie;

@RestController
public class StukMaken {
	@Autowired
	private StukManipulatie dataStuk;
	@Autowired
	private VeldManipulatie dataVeld;
	
	@RequestMapping("stukmaken")
	public SchaakStuk createStuk(){
		SchaakStuk stuk = new SchaakStuk();
		List<Integer> coords = new ArrayList<Integer>();;
		coords.add(1);coords.add(2);
		stuk.setType("pion");
		stuk.setColor("white");
		stuk.setCoords(coords);
		stuk.setOnBoard(true);		
		
		if(stuk.getOnBoard()){
			
		}
		
		
		return dataStuk.save(stuk);
	}
	
}