package com.chuixv.training.football.model;

import java.time.LocalDate;

public class player {
    
    public record Player(String id, int jerseyNumber, String name, String position, LocalDate dateOfBirth) {
    }    
}

