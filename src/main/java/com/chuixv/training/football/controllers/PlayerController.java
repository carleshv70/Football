package com.chuixv.training.football.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuixv.training.football.model.player.Player;
import com.chuixv.training.football.services.FootballService;

@RequestMapping("/players")
@RestController
public class PlayerController {

   private FootballService footballService;

   public PlayerController(FootballService footballService) {
      this.footballService = footballService;
   }

   @GetMapping
   public List<Player> listPlayers() {
      return footballService.listPlayers();
   }

   @GetMapping("/{id}")
   public Player readPlayer(@PathVariable String id) {
      return footballService.getPlayer(id);
   }

   @PostMapping
   public void createPlayer(@RequestBody Player player) {
      footballService.addPlayer(player);
   }

   @PutMapping("/{id}")
   public void updatePlayer(@PathVariable String id, @RequestBody Player player) {
      footballService.updatePlayer(player);
   }

   @DeleteMapping("/{id}")
   public void deletePlayer(@PathVariable String id) {
      footballService.deletePlayer(id);
   }

}
