package com.football.championship.service;

import com.football.championship.model.Championship;
import com.football.championship.repository.ChampionshipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipService {
    
    private final ChampionshipRepository championshipRepository;
    
    @Autowired
    public ChampionshipService(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }
    
    public List<Championship> getAllChampionships() {
        return championshipRepository.findAll();
    }
    
    public Championship getChampionshipById(Long id) {
        return championshipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Championnat non trouvé avec l'id: " + id));
    }
    
    public Championship createChampionship(Championship championship) {
        return championshipRepository.save(championship);
    }
    
    public Championship updateChampionship(Long id, Championship championshipDetails) {
        Championship championship = getChampionshipById(id);
        
        championship.setName(championshipDetails.getName());
        championship.setStartDate(championshipDetails.getStartDate());
        championship.setEndDate(championshipDetails.getEndDate());
        championship.setWinPoint(championshipDetails.getWinPoint());
        championship.setDrawPoint(championshipDetails.getDrawPoint());
        championship.setLostPoint(championshipDetails.getLostPoint());
        
        return championshipRepository.save(championship);
    }
    
    public void deleteChampionship(Long id) {
        if (!championshipRepository.existsById(id)) {
            throw new EntityNotFoundException("Championnat non trouvé avec l'id: " + id);
        }
        championshipRepository.deleteById(id);
    }
}