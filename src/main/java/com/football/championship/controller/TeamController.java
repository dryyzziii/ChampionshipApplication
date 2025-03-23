package com.football.championship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.football.championship.model.Team;
import com.football.championship.service.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    
    private final TeamService teamService;
    
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }
    
    @GetMapping("/championship/{championshipId}")
    public ResponseEntity<List<Team>> getTeamsByChampionshipId(@PathVariable Long championshipId) {
        return ResponseEntity.ok(teamService.getTeamsByChampionshipId(championshipId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }
    
    @PostMapping
    public ResponseEntity<Team> createTeam(@Valid @RequestBody Team team) {
        return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.CREATED);
    }
    
    @PostMapping("/{teamId}/championships/{championshipId}")
    public ResponseEntity<Team> addTeamToChampionship(@PathVariable Long teamId, @PathVariable Long championshipId) {
        return new ResponseEntity<>(teamService.addTeamToChampionship(teamId, championshipId), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{teamId}/championships/{championshipId}")
    public ResponseEntity<Team> removeTeamFromChampionship(@PathVariable Long teamId, @PathVariable Long championshipId) {
        return ResponseEntity.ok(teamService.removeTeamFromChampionship(teamId, championshipId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @Valid @RequestBody Team team) {
        return ResponseEntity.ok(teamService.updateTeam(id, team));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}