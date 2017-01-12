import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;


public class Bord {
	int[][] matrix;	
	public Bord() {
			
	}
	
	
	public void image() {
		try {
			BufferedImage image = ImageIO.read(new File("Chess_Board.svg"));
		} catch (IOException e){
		}
	
	}
	public static void main(String args[]){
		Bord bord = new Bord();

	}

}