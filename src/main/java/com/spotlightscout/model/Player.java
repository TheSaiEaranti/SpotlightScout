package com.spotlightscout.model;

import jakarta.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private int minutesPlayed;
    private int assists;
    private int tackles;
    private double passAccuracy;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public int getMinutesPlayed() { return minutesPlayed; }
    public void setMinutesPlayed(int minutesPlayed) { this.minutesPlayed = minutesPlayed; }

    public int getAssists() { return assists; }
    public void setAssists(int assists) { this.assists = assists; }

    public int getTackles() { return tackles; }
    public void setTackles(int tackles) { this.tackles = tackles; }

    public double getPassAccuracy() { return passAccuracy; }
    public void setPassAccuracy(double passAccuracy) { this.passAccuracy = passAccuracy; }
}
