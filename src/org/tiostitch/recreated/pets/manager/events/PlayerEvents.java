package org.tiostitch.recreated.pets.manager.events;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.tiostitch.recreated.pets.Main;
import org.tiostitch.recreated.pets.manager.SkyBlockPlayer;

@RequiredArgsConstructor
public final class PlayerEvents
implements Listener {

    private final Main main;

    @EventHandler
    void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        main.getPetData().openConnection().createData(player.getName());
    }

    @EventHandler
    void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        final SkyBlockPlayer skyBlockPlayer = main.getLoaded_players().get(player.getName());
        if (skyBlockPlayer == null) return;

        main.getPetData().openConnection().saveData(skyBlockPlayer);
    }
}
