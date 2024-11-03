package org.tiostitch.recreated.pets.interactableAPI;

import org.tiostitch.recreated.pets.configuration.StoragedFiles;
import org.tiostitch.recreated.pets.manager.Pet;
import org.tiostitch.recreated.pets.utilities.AbilityBase;
import org.tiostitch.recreated.pets.utilities.PetBase;
import org.tiostitch.recreated.pets.utilities.others.Rarity;
import org.tiostitch.recreated.pets.utilities.others.SkyBlockStats;

public final class PetSettingsAPI {

    public static boolean hasAbility(PetBase petBase, AbilityBase abilityBase, Rarity rarity) {
        if (petBase == null) return false;
        if (abilityBase == null) return false;

        return (petBase.getAbilities().get(rarity).contains(abilityBase));
    }

    public static double getBonusStatByPet(Pet pet, SkyBlockStats stats, Rarity rarity) {
        return pet.getLevel() * pet.getPetBase().getUpgrade_stats().get(rarity).getOrDefault(stats, 0.0);
    }

    public static PetBase getPetBase(String petName) {
        return StoragedFiles.getStoraged_pets().get(petName);
    }

    public static AbilityBase getAbilityBase(String abilityName) {
        return StoragedFiles.getStoraged_abilities().get(abilityName);
    }
}
