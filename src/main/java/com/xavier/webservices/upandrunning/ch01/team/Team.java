package com.xavier.webservices.upandrunning.ch01.team;

import java.util.List;

/**
 * Created by Xavier on 2017/12/4.
 */
public class Team {
    private String name;
    private List<Player> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Team() {
    }

    public Team(String name, List<Player> players) {
        setName(name);
        setPlayers(players);
    }

    public void setRosterCount(int n) {}
    public int getRosterCount() {
        return (players == null) ? 0: players.size();
    }
}
