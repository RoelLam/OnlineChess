package nl.chess.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.chess.database.SchaakStuk;

@Repository
@Transactional
public interface StukManipulatie extends JpaRepository<SchaakStuk,Long>{
		List<SchaakStuk> findByOnBoard(Boolean onBoard);
}

