package com.football.championship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.football.championship.model.Championship;
import com.football.championship.model.Team;
import com.football.championship.repository.TeamRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamService {
    
    private final TeamRepository teamRepository;
    private final ChampionshipService championshipService;
    
    @Autowired
    public TeamService(TeamRepository teamRepository, ChampionshipService championshipService) {
        this.teamRepository = teamRepository;
        this.championshipService = championshipService;
    }
    
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    
    public List<Team> getTeamsByChampionshipId(Long championshipId) {
        return teamRepository.findByChampionshipId(championshipId);
    }
    
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Équipe non trouvée avec l'id: " + id));
    }
    
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }
    
    public Team addTeamToChampionship(Long teamId, Long championshipId) {
        Team team = getTeamById(teamId);
        Championship championship = championshipService.getChampionshipById(championshipId);
        
        // Vérifier si l'équipe est déjà associée au championnat
        if (team.getChampionships().stream().anyMatch(c -> c.getId().equals(championshipId))) {
            throw new IllegalArgumentException("L'équipe est déjà associée à ce championnat");
        }
        
        team.addChampionship(championship);
        return teamRepository.save(team);
    }
    
    public Team updateTeam(Long id, Team teamDetails) {
        Team team = getTeamById(id);
        
        team.setName(teamDetails.getName());
        
        return teamRepository.save(team);
    }
    
    public void deleteTeam(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("Équipe non trouvée avec l'id: " + id);
        }
        teamRepository.deleteById(id);
    }
    
    public Team removeTeamFromChampionship(Long teamId, Long championshipId) {
        Team team = getTeamById(teamId);
        
        // Vérifier si l'équipe est associée au championnat
        if (team.getChampionships().stream().noneMatch(c -> c.getId().equals(championshipId))) {
            throw new IllegalArgumentException("L'équipe n'est pas associée à ce championnat");
        }
        
        team.removeChampionship(championshipId);
        return teamRepository.save(team);
    }
}