package me.marco.tribos.domain;

import org.bukkit.entity.Player;

import java.util.List;

public class Tribe {
    private String name;
    private Player owner;
    private List<Player> members;
    private Long money;
    private Integer level;

    public Tribe(String name, Player owner, List<Player> members, Long money, Integer level) {
        this.name = name;
        this.owner = owner;
        this.members = members;
        this.money = money;
        this.level = level;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public List<Player> getMembers() {
        return members;
    }

    public void setMembers(List<Player> members) {
        this.members = members;
    }
}
