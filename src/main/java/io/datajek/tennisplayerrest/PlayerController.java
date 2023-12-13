package io.datajek.tennisplayerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    PlayerService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Tennis Player REST API";
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return service.getAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable("id") int playerId){
        return service.getPlayer(playerId);
    }

    @PostMapping("/players")
    // @RequestBody converts input from JSON to POJO
    public Player addPlayer(@RequestBody Player player) {
        player.setId(0); // If the user inserts a id
        return service.addPlayer(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@RequestBody Player player, @PathVariable int id) {
        return service.updatePlayer(id, player);
    }
}
