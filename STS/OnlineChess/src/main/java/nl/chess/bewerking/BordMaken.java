package nl.chess.bewerking;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.chess.database.Bord;
import nl.chess.database.Veld;
import nl.chess.repository.BordManipulatie;
import nl.chess.repository.VeldManipulatie;

@RestController
public class BordMaken {
	@Autowired
	private BordManipulatie dataBord;
	
	@Autowired
	private VeldManipulatie dataVeld;
	
	@RequestMapping("bord")
	public Bord save(){
		Bord bord = new Bord();
		dataVeld.save(bord.getVelden());
		return dataBord.save(bord);
	}
		
}
