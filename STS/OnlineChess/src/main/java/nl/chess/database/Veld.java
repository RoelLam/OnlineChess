package nl.chess.database;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Veld {
	public Veld(List<Integer> coords){
		this.coords =coords;
		this.veldNaam ="Veld"+coords.get(0)+"" + coords.get(1);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String veldNaam;
	@OneToOne
	private Stuk stuk;
	@ElementCollection(fetch=FetchType.EAGER)
	private List<Integer> coords;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVeldNaam() {
		return veldNaam;
	}
	public void setVeldNaam(String veldNaam) {
		this.veldNaam = veldNaam;
	}
	public Stuk getStuk() {
		return stuk;
	}
	public void setStuk(Stuk stuk) {
		this.stuk = stuk;
	}
	public List<Integer> getCoords() {
		return coords;
	}
	public void setCoords(List<Integer> coords) {
		this.coords = coords;
	}

}
