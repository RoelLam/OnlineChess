package nl.chess.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.chess.database.Bord;

@Repository
@Transactional
public interface BordManipulatie extends JpaRepository<Bord,Long>{
}
