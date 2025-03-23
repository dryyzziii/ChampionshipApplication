package com.football.championship.config;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.football.championship.model.Championship;
import com.football.championship.model.Day;
import com.football.championship.model.Game;
import com.football.championship.model.Team;
import com.football.championship.model.User;
import com.football.championship.repository.ChampionshipRepository;
import com.football.championship.repository.DayRepository;
import com.football.championship.repository.GameRepository;
import com.football.championship.repository.TeamRepository;
import com.football.championship.repository.UserRepository;

import jakarta.transaction.Transactional;

@Configuration
public class DataInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    public CommandLineRunner initData(UserRepository userRepository,
            ChampionshipRepository championshipRepository,
            TeamRepository teamRepository,
            DayRepository dayRepository,
            GameRepository gameRepository) {
        return args -> {
            try {
                // Suppression des données existantes
                gameRepository.deleteAll();
                dayRepository.deleteAll();
                teamRepository.deleteAll();
                championshipRepository.deleteAll();
                userRepository.deleteAll();

                // Création des utilisateurs
                User admin = new User();
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("password123"));
                userRepository.save(admin);

                User user1 = new User();
                user1.setFirstName("John");
                user1.setLastName("Doe");
                user1.setEmail("john.doe@example.com");
                user1.setPassword(passwordEncoder.encode("password123"));
                userRepository.save(user1);

                // Création des championnats
                Championship ligue1 = new Championship();
                ligue1.setName("Ligue 1");
                ligue1.setStartDate(getDate(2024, 8, 1));
                ligue1.setEndDate(getDate(2025, 5, 30));
                ligue1.setWinPoint(3);
                ligue1.setDrawPoint(1);
                ligue1.setLostPoint(0);
                championshipRepository.save(ligue1);

                Championship premierLeague = new Championship();
                premierLeague.setName("Premier League");
                premierLeague.setStartDate(getDate(2024, 8, 15));
                premierLeague.setEndDate(getDate(2025, 5, 15));
                championshipRepository.save(premierLeague);

                // Création des équipes
                Team psg = new Team();
                psg.setName("Paris Saint-Germain");
                teamRepository.save(psg);

                Team marseille = new Team();
                marseille.setName("Olympique de Marseille");
                teamRepository.save(marseille);

                Team lyon = new Team();
                lyon.setName("Olympique Lyonnais");
                teamRepository.save(lyon);

                // Création des journées
                Day ligue1Day1 = new Day();
                ligue1Day1.setNumber("Journée 1");
                ligue1Day1.setChampionship(ligue1);
                dayRepository.save(ligue1Day1);

                Day ligue1Day2 = new Day();
                ligue1Day2.setNumber("Journée 2");
                ligue1Day2.setChampionship(ligue1);
                dayRepository.save(ligue1Day2);

                // Association manuelle des équipes et championnats (sans utiliser
                // addChampionship())
                psg.getChampionships().add(ligue1);
                marseille.getChampionships().add(ligue1);
                lyon.getChampionships().add(ligue1);

                teamRepository.save(psg);
                teamRepository.save(marseille);
                teamRepository.save(lyon);

                // Création des matchs
                Game game1 = new Game();
                game1.setTeam1(psg);
                game1.setTeam2(marseille);
                game1.setTeam1Point(3);
                game1.setTeam2Point(1);
                game1.setDay(ligue1Day1);
                gameRepository.save(game1);

                Game game2 = new Game();
                game2.setTeam1(lyon);
                game2.setTeam2(psg);
                game2.setTeam1Point(2);
                game2.setTeam2Point(2);
                game2.setDay(ligue1Day2);
                gameRepository.save(game2);

                System.out.println("Données d'exemple créées avec succès !");
            } catch (Exception e) {
                System.err.println("Erreur lors de l'initialisation des données : " + e.getMessage());
            }
        };
    }

    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // Les mois dans Calendar commencent à 0
        return calendar.getTime();
    }
}