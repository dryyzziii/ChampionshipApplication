package com.football.championship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.football.championship.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t JOIN t.championships c WHERE c.id = :championshipId")
    List<Team> findByChampionshipId(Long championshipId);
}