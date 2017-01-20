package nl.chess;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.chess.database.ChessColor;
import nl.chess.database.ChessType;
import nl.chess.database.SchaakStuk;
import nl.chess.repository.StukManipulatie;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OnlineChessApplication.class)
public class StukManipulatieTests {
	
	@Autowired
	private StukManipulatie manipulatie;
	
	@Test
	public void testOpslaanEnLadenVanSchaakstuk() {
		SchaakStuk stuk = newSchaakStuk(ChessColor.BLACK, ChessType.QUEEN, true);
		assertNull(stuk.getId());
		stuk = manipulatie.save(stuk);
		assertNotNull(stuk.getId());
		
		assertEquals("Ik verwacht ID 1",  Long.valueOf(1), stuk.getId());
		
		List<SchaakStuk> lijst = manipulatie.findAll();
		
		assertEquals("Er moet nu 1 record zijn", 1, lijst.size());
		SchaakStuk loaded = manipulatie.findOne(stuk.getId());
		assertNotNull(loaded);
		
		
	}

	private SchaakStuk newSchaakStuk(ChessColor color, ChessType type, Boolean onBoard) {
		SchaakStuk stuk = new SchaakStuk();
		stuk.setColor(color);
		stuk.setOnBoard(onBoard);
		stuk.setType(type);
		return stuk;
	}
}

