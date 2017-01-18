package nl.chess.bewerking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.chess.database.Bord;
import nl.chess.repository.BordManipulatie;

@RestController
public class getBordController{
	@Autowired
	private BordManipulatie dataBord;
	
	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping(value ="/nieuwbord" ,method = RequestMethod.GET)
	public Bord nieuwBord(){
		Bord b = dataBord.findOne(new Long(1));
		return b;
	}
}
