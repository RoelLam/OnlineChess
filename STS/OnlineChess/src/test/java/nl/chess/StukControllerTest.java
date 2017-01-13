package nl.chess;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.chess.bewerking.StukMaken;
import nl.chess.database.Bord;
import nl.chess.database.SchaakStuk;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OnlineChessApplication.class)
public class StukControllerTest {
	@Autowired
	private StukMaken stukMaken;
	@Test
	public void testBehaalKoningin() {
		Bord bord = stukMaken.createBord();
		SchaakStuk pion = bord.getSchaakStukken().get(0);
		pion.setCoords(Arrays.asList(2,7));
		// controle of { pion } koningin wordt
		SchaakStuk shouldBeQueen = stukMaken.changePiece(pion, "queen");
		
		Assert.assertEquals("Pion moet koningin zijn geworden",  "queen", shouldBeQueen.getType());
	}
}
