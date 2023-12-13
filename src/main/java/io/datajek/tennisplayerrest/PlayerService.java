package io.datajek.tennisplayerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository repo;

    //Get all players
    public List<Player> getAllPlayers() {
        //call repository method
        return repo.findAll();
    }

    //Get player by ID

    //Add a player

    //Update a player

    //Partial update

    //delete a player
}
