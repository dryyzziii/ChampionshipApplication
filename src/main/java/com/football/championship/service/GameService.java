package com.football.championship.service;

import com.football.championship.model.Day;
import com.football.championship.model.Game;
import com.football.championship.model.Team;
import com.football.championship.repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    
    private final GameRepository gameRepository;
    private final DayService dayService;
    private final TeamService teamService;
    
    @Autowired
    public GameService(GameRepository gameRepository, DayService dayService, TeamService teamService) {
        this.gameRepository = gameRepository;
        this.dayService = dayService;
        this.teamService = teamService;
    }
    
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
    
    public List<Game> getGamesByChampionshipId(Long championshipId) {
        return gameRepository.findByChampionshipId(championshipId);
    }
    
    public List<Game> getGamesByDayId(Long dayId) {
        return gameRepository.findByDayId(dayId);
    }
    
    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Résultat non trouvé avec l'id: " + id));
    }
    
    public Game createGame(Game game, Long dayId, Long team1Id, Long team2Id) {
        Day day = dayService.getDayById(dayId);
        Team team1 = teamService.getTeamById(team1Id);
        Team team2 = teamService.getTeamById(team2Id);
        
        game.setDay(day);
        game.setTeam1(team1);
        game.setTeam2(team2);
        
        return gameRepository.save(game);
    }
    
    public Game updateGame(Long id, Game gameDetails) {
        Game game = getGameById(id);
        
        game.setTeam1Point(gameDetails.getTeam1Point());
        game.setTeam2Point(gameDetails.getTeam2Point());
        
        return gameRepository.save(game);
    }
    
    public void deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new EntityNotFoundException("Résultat non trouvé avec l'id: " + id);
        }
        gameRepository.deleteById(id);
    }
}