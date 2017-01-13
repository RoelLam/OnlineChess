package nl.chess.database;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SchaakStuk {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String type;
	
	@Enumerated(EnumType.STRING)
	private ChessColor color;
	@Column(nullable=false)
	private Boolean onBoard;
	@ElementCollection
	private List<Integer> coords;
	@ManyToOne
	@JsonIgnore
	private Bord bord;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ChessColor getColor() {
		return color;
	}
	public void setColor(ChessColor color) {
		this.color = color;
	}
	public Boolean getOnBoard() {
		return onBoard;
	}
	public void setOnBoard(Boolean onBoard) {
		this.onBoard = onBoard;
	}
	public List<Integer> getCoords() {
		return coords;
	}
	public void setCoords(List<Integer> coords) {
		this.coords = coords;
	}
	public Bord getBord() {
		return bord;
	}
	public void setBord(Bord bord) {
		this.bord = bord;
	}
}
