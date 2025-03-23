package com.football.championship.controller;

import com.football.championship.model.Game;
import com.football.championship.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    
    private final GameService gameService;
    
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }
    
    @GetMapping("/championship/{championshipId}")
    public ResponseEntity<List<Game>> getGamesByChampionshipId(@PathVariable Long championshipId) {
        return ResponseEntity.ok(gameService.getGamesByChampionshipId(championshipId));
    }
    
    @GetMapping("/day/{dayId}")
    public ResponseEntity<List<Game>> getGamesByDayId(@PathVariable Long dayId) {
        return ResponseEntity.ok(gameService.getGamesByDayId(dayId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }
    
    @PostMapping("/day/{dayId}/team1/{team1Id}/team2/{team2Id}")
    public ResponseEntity<Game> createGame(@Valid @RequestBody Game game, 
                                          @PathVariable Long dayId, 
                                          @PathVariable Long team1Id, 
                                          @PathVariable Long team2Id) {
        return new ResponseEntity<>(gameService.createGame(game, dayId, team1Id, team2Id), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @Valid @RequestBody Game game) {
        return ResponseEntity.ok(gameService.updateGame(id, game));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}