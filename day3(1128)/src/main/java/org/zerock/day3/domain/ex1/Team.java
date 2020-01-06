package org.zerock.day3.domain.ex1;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Team
 */
@Data
@Entity
@Table(name = "d3_team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tno;
    
    private String tname;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_no")
    private List<Player> players;

    public void releasePlayer(Player player) {

        this.players = this.players
        .stream()
        .filter(p -> p.getPno() != player.getPno())
        .collect(Collectors.toList());
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}