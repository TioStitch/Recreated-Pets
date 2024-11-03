package org.tiostitch.recreated.pets.manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tiostitch.recreated.pets.utilities.PetBase;
import org.tiostitch.recreated.pets.utilities.others.Rarity;

@Getter @Setter
@NoArgsConstructor
public final class Pet {

    private int level;
    private double currentExp;

    private PetBase petBase;
    private Rarity rarity;
}
