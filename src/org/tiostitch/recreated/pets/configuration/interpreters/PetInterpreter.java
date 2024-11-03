package org.tiostitch.recreated.pets.configuration.interpreters;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.tiostitch.recreated.pets.configuration.StoragedFiles;
import org.tiostitch.recreated.pets.utilities.AbilityBase;
import org.tiostitch.recreated.pets.utilities.PetBase;
import org.tiostitch.recreated.pets.utilities.others.Rarity;
import org.tiostitch.recreated.pets.utilities.others.SkillType;
import org.tiostitch.recreated.pets.utilities.others.SkyBlockStats;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PetInterpreter {

    public PetBase getPetBase(File file) {
        final FileConfiguration fC = YamlConfiguration.loadConfiguration(file);

        final PetBase petBase = new PetBase();
        petBase.setName(fC.getString("name"));
        petBase.setTexture(fC.getString("texture"));
        petBase.setSkillType(SkillType.valueOf(fC.getString("skill-type")));

        final Map<Rarity, Map<SkyBlockStats, Double>> petStats = new HashMap<>();

        for (Rarity rarity : Rarity.values()) {

            final HashMap<SkyBlockStats, Double> rarityStatsMap = new HashMap<>();
            final List<String> registered_stats = fC.getStringList("upgrade-stats." + rarity.name().toLowerCase());
            if (registered_stats == null) continue;

            if (!registered_stats.isEmpty()) {
                for (String statString : registered_stats) {
                    final String NAME = statString.split(":")[0];
                    final double AMOUNT = Double.parseDouble(statString.split(":")[1]);

                    final SkyBlockStats stat = SkyBlockStats.valueOf(NAME);
                    rarityStatsMap.put(stat, AMOUNT);
                }
            }

            petStats.put(rarity, rarityStatsMap);
        }

        petBase.setUpgrade_stats(petStats);

        final HashMap<Rarity, List<AbilityBase>> petAbilities = new HashMap<>();

        for (Rarity rarity : Rarity.values()) {

            if (!fC.contains("abilities." + rarity.name().toLowerCase())) continue;

            final List<String> abilities = fC.getStringList("abilities." + rarity.name().toLowerCase());

            final List<AbilityBase> registeredAbilities = new ArrayList<>();

            for (String ability : abilities) {
                registeredAbilities.add(StoragedFiles.getStoraged_abilities().get(ability));
            }

            petAbilities.put(rarity, registeredAbilities);
        }

        petBase.setAbilities(petAbilities);
        return petBase;
    }
}
