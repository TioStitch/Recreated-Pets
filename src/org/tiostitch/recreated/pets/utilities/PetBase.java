package org.tiostitch.recreated.pets.utilities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tiostitch.recreated.pets.utilities.others.Rarity;
import org.tiostitch.recreated.pets.utilities.others.SkillType;
import org.tiostitch.recreated.pets.utilities.others.SkyBlockStats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
public final class PetBase {

    private String name;
    private String texture;
    private SkillType skillType;

    private HashMap<Rarity, List<AbilityBase>> abilities;
    private Map<Rarity, Map<SkyBlockStats, Double>> upgrade_stats;

}
