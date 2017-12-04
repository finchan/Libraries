package com.xavier.webservices.upandrunning.ch01.team;

/**
 * Created by Xavier on 2017/12/4.
 */
public class Player {
    private String name;
    private String nickname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Player() {
    }

    public Player(String name, String nickname) {
        setName(name);
        setNickname(nickname);
    }
}
