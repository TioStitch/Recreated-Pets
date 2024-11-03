package org.tiostitch.recreated.pets.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.tiostitch.recreated.pets.Main;
import org.tiostitch.recreated.pets.configuration.interpreters.AbilityInterpreter;
import org.tiostitch.recreated.pets.configuration.interpreters.PetInterpreter;
import org.tiostitch.recreated.pets.utilities.AbilityBase;
import org.tiostitch.recreated.pets.utilities.PetBase;

import java.io.File;

@Getter
@RequiredArgsConstructor
public final class FileLoader {

    private final Main main;

    public void loadAbilities() {
        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {

            int loaded_abilities = 0;

            File[] abilities_folder = new File(main.getDataFolder().getPath() + "/abilities").listFiles();

            for (File ability_file : abilities_folder) {
                final AbilityBase abilityBase = new AbilityInterpreter().getAbilityBase(ability_file);
                StoragedFiles.getStoraged_abilities().put(ability_file.getName().replace(".yml", ""), abilityBase);

                loaded_abilities++;
            }

            Bukkit.getConsoleSender().sendMessage("§a[+] Loaded +" + loaded_abilities + " §aAbilities!");
        });
    }

    public void loadPets() {
        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {

            int loaded_pets = 0;

            final File pet_folder = new File(main.getDataFolder().getPath() + "/pets");
            final File[] pets_folder = pet_folder.listFiles();

            for (File pets_file : pets_folder) {
                final PetBase petBase = new PetInterpreter().getPetBase(pets_file);
                StoragedFiles.getStoraged_pets().put(pets_file.getName().replace(".yml", ""), petBase);

                loaded_pets++;
            }

            Bukkit.getConsoleSender().sendMessage("§a[+] Loaded +" + loaded_pets + " §aPets!");
        });
    }
}
