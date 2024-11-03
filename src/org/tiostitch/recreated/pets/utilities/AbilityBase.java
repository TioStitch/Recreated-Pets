package org.tiostitch.recreated.pets.utilities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tiostitch.recreated.pets.utilities.others.Rarity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
public final class AbilityBase {

    private List<String> description;
    private HashMap<Rarity, AbilityBonus> abilityBonuses;
}
