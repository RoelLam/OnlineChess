package nl.chess.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.chess.database.Bord;
import nl.chess.database.Stuk;
import nl.chess.database.Veld;

@Repository
public interface VeldManipulatie extends JpaRepository<Veld,Long> {
}



