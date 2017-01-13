package nl.chess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.chess.database.Stuk;

@Repository
public interface StukManipulatie extends JpaRepository<Stuk,Long>{

}