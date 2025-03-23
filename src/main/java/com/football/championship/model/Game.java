package com.football.championship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "games")
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "team1_point")
    private int team1Point;
    
    @Column(name = "team2_point")
    private int team2Point;
    
    @NotNull(message = "L'équipe 1 est obligatoire")
    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;
    
    @NotNull(message = "L'équipe 2 est obligatoire")
    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;
    
    @NotNull(message = "La journée est obligatoire")
    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTeam1Point() {
        return team1Point;
    }

    public void setTeam1Point(int team1Point) {
        this.team1Point = team1Point;
    }

    public int getTeam2Point() {
        return team2Point;
    }

    public void setTeam2Point(int team2Point) {
        this.team2Point = team2Point;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}