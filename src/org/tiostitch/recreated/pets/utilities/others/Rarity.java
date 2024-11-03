package org.tiostitch.recreated.pets.utilities.others;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rarity {
    COMMON("COMMON", "§f"),
    UNCOMMON("UNCOMMON", "§a"),
    RARE("RARE", "§9"),
    EPIC("EPIC", "§5"),
    LEGENDARY("LEGENDARY", "§6"),
    MYTHICAL("MYTHICAL", "§d");

    private final String prefix;
    private final String color;
}
