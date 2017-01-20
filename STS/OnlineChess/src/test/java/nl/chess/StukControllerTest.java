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
import nl.chess.repository.StukManipulatie;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OnlineChessApplication.class)
public class StukControllerTest {
	@Autowired
	private StukMaken stukMaken;
	@Autowired
	private StukManipulatie stukManipulatie;
	@Test
	public void testBehaalKoningin() {
		Bord bord = stukMaken.createBord();
		SchaakStuk pion = bord.getSchaakStukken().get(0);
		pion.setCoords(Arrays.asList(2,7));
		// controle of { pion } koningin wordt
		SchaakStuk shouldBeQueen = stukMaken.changePiece(pion, "queen");
		
		Assert.assertEquals("Pion moet koningin zijn geworden",  "queen", shouldBeQueen.getType());
	}
	@Test
	public void testCoordinaatBinnenBord(){
		Bord b = stukMaken.createBord();
		SchaakStuk schaakStuk = b.getSchaakStukken().get(0);
		System.out.println(schaakStuk.getCoords().get(0));
		System.out.println(schaakStuk.getCoords().get(1));
		stukMaken.zet(schaakStuk.getId(),0, -1);
		schaakStuk = stukManipulatie.findOne(schaakStuk.getId());
		Assert.assertArrayEquals(new Integer[] {1, 0}, schaakStuk.getCoords().toArray());
		stukMaken.zet(schaakStuk.getId(),0, 10);
		schaakStuk = stukManipulatie.findOne(schaakStuk.getId());
		Assert.assertArrayEquals(new Integer[] {1, 0}, schaakStuk.getCoords().toArray());
		stukMaken.zet(schaakStuk.getId(),-1, 0);
		schaakStuk = stukManipulatie.findOne(schaakStuk.getId());
		Assert.assertArrayEquals(new Integer[] {1, 0}, schaakStuk.getCoords().toArray());
		stukMaken.zet(schaakStuk.getId(),10, 0);
		schaakStuk = stukManipulatie.findOne(schaakStuk.getId());
		Assert.assertArrayEquals(new Integer[] {1, 0}, schaakStuk.getCoords().toArray());
			
	}
}
