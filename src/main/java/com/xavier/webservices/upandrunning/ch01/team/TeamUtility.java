package com.xavier.webservices.upandrunning.ch01.team;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Xavier on 2017/12/4.
 */
public class TeamUtility {
    private Map<String, Team> team_map;

    public TeamUtility( ) {
        team_map = new HashMap<String, Team>();
    }

    public Team getTeam(String name) {
        return team_map.get(name);
    }

    public List<Team> getTeams () {
        List<Team> list = new ArrayList<Team>( );
        Set<String> keys = team_map.keySet();
        for(String key: keys) {
            list.add(team_map.get(key));
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        TeamUtility u = new TeamUtility();
        u.make_test_teams();
        FileOutputStream fo =new FileOutputStream(new File("team_ser"));
        XMLEncoder encoder = new XMLEncoder(fo);
        encoder.writeObject(u.getTeams());
        encoder.flush();
        encoder.close();
        fo.close();
    }

    public void make_test_teams () {
        List<Team> teams = new ArrayList<Team>();
        Player chico = new Player("Leonard Marx", "Chico");
        Player groucho = new Player("Julius Marx", "Groucho");
        Player harpo = new Player("Adolph Marx", "Harpo");
        List<Player> mb = new ArrayList<Player>();
        mb.add(chico);
        mb.add(groucho);
        mb.add(harpo);
        Team marx_brothers = new Team("Marx Brothers", mb);
        teams.add(marx_brothers);
        store_teams(teams);
    }

    public void store_teams(List<Team> teams) {
        for(Team team : teams){
            team_map.put(team.getName(), team);
        }
    }
}
