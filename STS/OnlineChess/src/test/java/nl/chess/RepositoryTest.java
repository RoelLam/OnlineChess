package nl.chess;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.chess.database.Bord;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OnlineChessApplication.class)
public class RepositoryTest {
	
	@Test
	public void testBord(){
	}
	
}

