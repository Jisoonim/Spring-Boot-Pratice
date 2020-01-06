package org.zerock.day3.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.day3.domain.ex1.Player;
import org.zerock.day3.domain.ex1.Team;
import org.zerock.day3.repository.ex1.PlayerRepository;
import org.zerock.day3.repository.ex1.TeamRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * TeamTests
 */
@SpringBootTest
@Slf4j
public class TeamTests {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void test(){
        IntStream.range(1, 6).forEach(i->{
            Team team = new Team();
            team.setTname("team"+i);

            teamRepository.save(team);
        });
    }

    @Test
    public void playerInsert(){
        IntStream.range(6, 11).forEach(i->{
           Player plyer = new Player();
            plyer.setPname("ply"+i);

            playerRepository.save(plyer);
        });
    }

    @Commit
    @Transactional
    @Test
    public void addPlayers(){
        Player p1 = playerRepository.getOne(1);
        Player p2 = playerRepository.getOne(2);

        Team t1 = teamRepository.getOne(1);

        List<Player> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        t1.setPlayers(list);

        teamRepository.save(t1);
    }

    @Test
    public void testReadTeam(){
        
        Team t1 = teamRepository.findById(1).get();
        
        log.info("============");
        log.info(t1.getTno()+"");
        log.info(t1.toString());
        log.info("============");
    }

    @Test
    public void testTeamPaging(){
        Pageable page = PageRequest.of(0, 5, Direction.DESC, "tno");

        Page<Team> result = teamRepository.findAll(page);

        result.forEach(t -> log.info("" + t));

    }

    @Test
    @Transactional
    @Commit
    public void testReleasePlayer() { //선수 방출

        Player target = playerRepository.getOne(4);

        Team team = teamRepository.getOne(1);

        team.releasePlayer(target);
    }


}