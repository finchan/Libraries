package com.xavier.webservices.upandrunning.ch01.team;

import com.xavier.webservices.upandrunning.ch01.team.teamc.TeamsService;
import com.xavier.webservices.upandrunning.ch01.team.teamc.Teams;
import com.xavier.webservices.upandrunning.ch01.team.teamc.Team;
import com.xavier.webservices.upandrunning.ch01.team.teamc.Player;

import java.util.List;

/**
 * Created by Xavier on 2017/12/4.
 */
public class TeamClient {
    public static void main(String[] args) {
        TeamsService service = new TeamsService();
        Teams port = service.getTeamsPort();
        List<Team> teams = port.getTeams();
        for(Team team: teams) {
            System.out.println("Team name: " +team.getName() +
                    " (roster count: " + team.getRosterCount() + ")" );
            for(Player player : team.getPlayers()) {
                System.out.println(" Player: " + player.getNickname());
            }
        }
    }
}
