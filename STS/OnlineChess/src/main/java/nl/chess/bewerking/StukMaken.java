package nl.chess.bewerking;

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
					stuk.setType(schaakstukType.name());
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
	public Bord zet(@PathVariable Long schaakstukId, @PathVariable Integer x, @PathVariable Integer y) {
		SchaakStuk stuk = dataStuk.findOne(schaakstukId);
		stuk.getCoords().clear();
		dataStuk.save(stuk);
		
		stuk.getCoords().add(x);
		stuk.getCoords().add(y);

		return dataStuk.save(stuk).getBord();
	}
		
	
	@RequestMapping("verander/{type}")
	public SchaakStuk changePiece(@ModelAttribute SchaakStuk pion, @PathVariable String type) {
		pion.setType(type);
		return dataStuk.save(pion);		
	}	
}