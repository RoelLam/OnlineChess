package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.database.Bord;

@Repository
public interface BordManipulatie extends JpaRepository<Bord,Long>{
}
