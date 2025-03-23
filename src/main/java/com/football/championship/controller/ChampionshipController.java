package com.football.championship.controller;

import com.football.championship.model.Championship;
import com.football.championship.service.ChampionshipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/championships")
public class ChampionshipController {
    
    private final ChampionshipService championshipService;
    
    @Autowired
    public ChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }
    
    @GetMapping
    public ResponseEntity<List<Championship>> getAllChampionships() {
        return ResponseEntity.ok(championshipService.getAllChampionships());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Championship> getChampionshipById(@PathVariable Long id) {
        return ResponseEntity.ok(championshipService.getChampionshipById(id));
    }
    
    @PostMapping
    public ResponseEntity<Championship> createChampionship(@Valid @RequestBody Championship championship) {
        return new ResponseEntity<>(championshipService.createChampionship(championship), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Championship> updateChampionship(@PathVariable Long id, @Valid @RequestBody Championship championship) {
        return ResponseEntity.ok(championshipService.updateChampionship(id, championship));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChampionship(@PathVariable Long id) {
        championshipService.deleteChampionship(id);
        return ResponseEntity.noContent().build();
    }
}