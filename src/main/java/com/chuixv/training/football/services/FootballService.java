package com.chuixv.training.football.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chuixv.training.football.exceptions.AlreadyExistsException;
import com.chuixv.training.football.exceptions.NotFoundException;
import com.chuixv.training.football.model.Player;


@Service
public class FootballService {

    private Map<String, Player> players = new HashMap<>(
    Map.ofEntries(
            Map.entry("1884823", new Player("1884823", 5, "Ivana ANDRES", "Defender", LocalDate.of(1994, 07, 13))),
            Map.entry("325636", new Player("325636", 11, "Alexia PUTELLAS", "Midfielder", LocalDate.of(1994, 02, 04)))));



    public List<Player> listPlayers() {
        return players.values().stream().collect(Collectors.toList());
    }

    public Player getPlayer(String id) {
        Player player = players.get(id);
        if (player == null)
            throw new NotFoundException("Player not found");
        return player;
    }

    public Player addPlayer(Player player) {
        if (players.containsKey(player.id())) {
            throw new AlreadyExistsException("The player already exists");
        } else {
            //players.put(player.id(),new Player(player.id(),player.jerseyNumber(), player.name(), player.position(), player.dateOfBirth()));
            players.put(player.id(), player);
            return player;
        }
    }

    public void deletePlayer(String id) {
        if (players.containsKey(id)) {
            players.remove(id);
        }
    }

    public Player updatePlayer(Player player) {
        if (!players.containsKey(player.id())) {
            throw new NotFoundException("The player does not exist");
        } else {
            this.players.put(player.id(), player);
            return player;
        }
    }

}
