package org.tiostitch.recreated.pets.configuration;

import lombok.Getter;
import org.tiostitch.recreated.pets.utilities.AbilityBase;
import org.tiostitch.recreated.pets.utilities.PetBase;

import java.util.HashMap;

public final class StoragedFiles {
    @Getter private static final HashMap<String, PetBase> storaged_pets = new HashMap<>();
    @Getter private static final HashMap<String, AbilityBase> storaged_abilities = new HashMap<>();
}
