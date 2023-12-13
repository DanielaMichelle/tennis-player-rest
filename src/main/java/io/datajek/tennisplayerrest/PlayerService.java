package io.datajek.tennisplayerrest;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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
    public Player updatePlayer(int id, Player p) {

        //get player object by ID
        Player player = repo.getOne(id);

        //update player details
        player.setName(p.getName());
        player.setNationality(p.getNationality());
        player.setBirthDate(p.getBirthDate());
        player.setTitles(p.getTitles());

        //save updates
        return repo.save(player);
    }

    //Partial update
    public Player patch( int id, Map<String, Object> playerPatch) {

        Optional<Player> player = repo.findById(id);

        if(player.isPresent()) {
            playerPatch.forEach( (key, value) -> {
                Field field = ReflectionUtils.findField(Player.class, key);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, player.get(), value);
            });
        }
        return repo.save(player.get());
    }

    @Transactional
    public void updateTitles(int id, int titles) {
        repo.updateTitles(id, titles);
    }

    //delete a player
    public String deletePlayer(int id) {
        try {
            repo.deleteById(id);
        } catch(Exception e) {
            return "Player with id " + id + "not found";
        }

        return "Deleted player with id: " + id;
    }
}
