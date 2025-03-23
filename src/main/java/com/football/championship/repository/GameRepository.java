package com.football.championship.repository;

import com.football.championship.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByDayId(Long dayId);
    
    @Query("SELECT g FROM Game g WHERE g.day.championship.id = :championshipId")
    List<Game> findByChampionshipId(Long championshipId);
}