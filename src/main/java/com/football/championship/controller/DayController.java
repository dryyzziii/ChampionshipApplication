package com.football.championship.controller;

import com.football.championship.model.Day;
import com.football.championship.service.DayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/days")
public class DayController {
    
    private final DayService dayService;
    
    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }
    
    @GetMapping
    public ResponseEntity<List<Day>> getAllDays() {
        return ResponseEntity.ok(dayService.getAllDays());
    }
    
    @GetMapping("/championship/{championshipId}")
    public ResponseEntity<List<Day>> getDaysByChampionshipId(@PathVariable Long championshipId) {
        return ResponseEntity.ok(dayService.getDaysByChampionshipId(championshipId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Day> getDayById(@PathVariable Long id) {
        return ResponseEntity.ok(dayService.getDayById(id));
    }
    
    @PostMapping("/championship/{championshipId}")
    public ResponseEntity<Day> createDay(@Valid @RequestBody Day day, @PathVariable Long championshipId) {
        return new ResponseEntity<>(dayService.createDay(day, championshipId), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Day> updateDay(@PathVariable Long id, @Valid @RequestBody Day day) {
        return ResponseEntity.ok(dayService.updateDay(id, day));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long id) {
        dayService.deleteDay(id);
        return ResponseEntity.noContent().build();
    }
}