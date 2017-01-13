package nl.chess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.chess.database.SchaakStuk;

@Repository
public interface StukManipulatie extends JpaRepository<SchaakStuk,Long>{

}