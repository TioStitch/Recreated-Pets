package org.tiostitch.recreated.pets;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.tiostitch.recreated.pets.commands.AdminCMD;
import org.tiostitch.recreated.pets.configuration.FileLoader;
import org.tiostitch.recreated.pets.database.PetData;
import org.tiostitch.recreated.pets.manager.SkyBlockPlayer;
import org.tiostitch.recreated.pets.manager.events.PlayerEvents;
import org.tiostitch.recreated.pets.utilities.PetBuilder;

import java.util.HashMap;

public final class Main
extends JavaPlugin {

    @Getter private Main instance;
    @Getter private boolean isMySQL = false;

    @Getter private PetData petData;
    @Getter private PetBuilder petBuilder;
    @Getter private FileLoader fileLoader;
    @Getter private HashMap<String, SkyBlockPlayer> loaded_players;


    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        Bukkit.getConsoleSender().sendMessage("§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("   §e§lRECREATED-PETS §f- v1.0");
        Bukkit.getConsoleSender().sendMessage("      §fDeveloped by TioStitch");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-§e-§6-");

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            petData = new PetData(this);
            petBuilder = new PetBuilder(this);
            fileLoader = new FileLoader(this);
            loaded_players = new HashMap<>();

            fileLoader.loadAbilities();
            fileLoader.loadPets();

            isMySQL = getConfig().getBoolean("use-mysql");
            petData.openConnection().startData();
        });

        Bukkit.getPluginCommand("petsdebug").setExecutor(new AdminCMD(this));

        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerEvents(this), this);
    }

    private void registerCommands() {
        Bukkit.getPluginCommand("petsdebug").setExecutor(new AdminCMD(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§e§lRECREATED-PETS - §cPower Off!");
    }
}
