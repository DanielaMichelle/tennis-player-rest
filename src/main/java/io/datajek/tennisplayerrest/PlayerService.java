package io.datajek.tennisplayerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Player getPlayer(int id) {

        Optional<Player> tempPlayer = repo.findById(id);

        Player p = null;

        //if the Optional has a value, assign it to p
        if(tempPlayer.isPresent())
            p = tempPlayer.get();
            //if value is not found, throw a runtime exception
        else
            throw new RuntimeException("Player with id "+ id + " not found.");

        return p;
    }

    //Add a player
    public Player addPlayer(Player p) {
        return repo.save(p);
    }

    //Update a player

    //Partial update

    //delete a player
}
