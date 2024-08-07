package com.chuixv.training.football.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chuixv.training.football.exceptions.AlreadyExistsException;
import com.chuixv.training.football.exceptions.NotFoundException;
import com.chuixv.training.football.model.Player;
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
   public ResponseEntity<Player> readPlayer(@PathVariable String id) 
   {
     try {
             Player player = footballService.getPlayer(id);
             return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (NotFoundException e) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
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

   /*/
   @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
   @ExceptionHandler(NotFoundException.class)
   public void notFoundHandler() {
   }
   */

   @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Already exists")
   @ExceptionHandler(AlreadyExistsException.class)
   public void alreadyExistsHandler() {
   }

}
