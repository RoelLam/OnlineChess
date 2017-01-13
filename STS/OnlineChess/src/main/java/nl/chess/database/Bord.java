package nl.chess.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Bord {
	
	public Bord(){	
		this.velden=voegVeldenToe();
	}

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToMany
	private List<Veld> velden;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Veld> getVelden() {
		return velden;
	}
	public void setVelden(List<Veld> velden) {
		this.velden = velden;
	}		
	
	private static List<Veld> voegVeldenToe() {
		List<Veld> lijst = new ArrayList<Veld>();
		
		for(int xcoord=0;xcoord<8;xcoord++){
			for(int ycoord=0;ycoord<8;ycoord++){
				List<Integer> coords = new ArrayList<Integer>();
				coords.add(xcoord); coords.add(ycoord); 
				Veld veld = new Veld(coords);
				lijst.add(veld);
			}
		}
		
		return lijst;
	}
}