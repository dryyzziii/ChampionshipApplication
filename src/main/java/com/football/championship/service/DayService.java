package com.football.championship.service;

import com.football.championship.model.Championship;
import com.football.championship.model.Day;
import com.football.championship.repository.DayRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayService {
    
    private final DayRepository dayRepository;
    private final ChampionshipService championshipService;
    
    @Autowired
    public DayService(DayRepository dayRepository, ChampionshipService championshipService) {
        this.dayRepository = dayRepository;
        this.championshipService = championshipService;
    }
    
    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }
    
    public List<Day> getDaysByChampionshipId(Long championshipId) {
        return dayRepository.findByChampionshipId(championshipId);
    }
    
    public Day getDayById(Long id) {
        return dayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Journée non trouvée avec l'id: " + id));
    }
    
    public Day createDay(Day day, Long championshipId) {
        Championship championship = championshipService.getChampionshipById(championshipId);
        day.setChampionship(championship);
        
        return dayRepository.save(day);
    }
    
    public Day updateDay(Long id, Day dayDetails) {
        Day day = getDayById(id);
        
        day.setNumber(dayDetails.getNumber());
        
        return dayRepository.save(day);
    }
    
    public void deleteDay(Long id) {
        if (!dayRepository.existsById(id)) {
            throw new EntityNotFoundException("Journée non trouvée avec l'id: " + id);
        }
        dayRepository.deleteById(id);
    }
}