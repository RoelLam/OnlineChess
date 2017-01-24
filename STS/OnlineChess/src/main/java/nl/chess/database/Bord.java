package nl.chess.database;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bord {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToMany(mappedBy="bord", fetch=FetchType.EAGER)
	private List<SchaakStuk> schaakStukken = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private ChessColor aanDeBeurt = ChessColor.WHITE;
	@Column
	private Integer enPassant;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<SchaakStuk> getSchaakStukken() {
		return schaakStukken;
	}
	public void setSchaakStukken(List<SchaakStuk> schaakStukken) {
		this.schaakStukken = schaakStukken;
	}
	public ChessColor getAanDeBeurt() {
		return aanDeBeurt;
	}
	public void setAanDeBeurt(ChessColor aanDeBeurt) {
		this.aanDeBeurt = aanDeBeurt;
	}
	
	public Integer getEnPassant() {
		return enPassant;
	}
	public void setEnPassant(Integer enPassant) {
		this.enPassant = enPassant;
	}
	
}