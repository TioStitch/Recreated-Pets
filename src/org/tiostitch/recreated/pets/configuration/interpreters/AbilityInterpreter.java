package org.tiostitch.recreated.pets.configuration.interpreters;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.tiostitch.recreated.pets.utilities.AbilityBase;
import org.tiostitch.recreated.pets.utilities.AbilityBonus;
import org.tiostitch.recreated.pets.utilities.others.Rarity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AbilityInterpreter {

    public AbilityBase getAbilityBase(File file) {
        final FileConfiguration fC = YamlConfiguration.loadConfiguration(file);

        final AbilityBase abilityBase = new AbilityBase();

        final HashMap<Rarity, AbilityBonus> bonuses = new HashMap<>();

        for (Rarity rarity : Rarity.values()) {
            if (!fC.contains("bonuses." + rarity.name().toLowerCase())) continue;

            final AbilityBonus bonus = new AbilityBonus();
            final String bonus_path = "bonuses." + rarity.name().toLowerCase() + ".";

            bonus.setBaseFirstBonus(fC.getDouble(bonus_path + "base.X"));
            bonus.setBaseSecondBonus(fC.getDouble(bonus_path + "base.Y"));

            bonus.setPerLevelFirstBonus(fC.getDouble(bonus_path + "per_level.X"));
            bonus.setPerLevelSecondBonus(fC.getDouble(bonus_path + "per_level.Y"));

            bonuses.put(rarity, bonus);
        }

        abilityBase.setAbilityBonuses(bonuses);
        abilityBase.setDescription(fC.getStringList("ability-description"));

        return abilityBase;
    }

}
