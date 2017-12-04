package com.xavier.webservices.upandrunning.ch01.team;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Xavier on 2017/12/4.
 */
@WebService
public class Teams {
    private TeamUtility utils;

    public Teams() {
        utils = new TeamUtility();
        utils.make_test_teams();
    }

    @WebMethod
    public Team getTeam(String name) {
        return utils.getTeam(name);
    }

    @WebMethod
    public List<Team> getTeams() {
        return utils.getTeams();
    }
}
