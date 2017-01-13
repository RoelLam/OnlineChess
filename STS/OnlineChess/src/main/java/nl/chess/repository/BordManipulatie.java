package nl.chess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.chess.database.Bord;

@Repository
public interface BordManipulatie extends JpaRepository<Bord,Long>{
}
