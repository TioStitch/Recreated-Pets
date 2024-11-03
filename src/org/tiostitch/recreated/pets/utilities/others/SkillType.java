package org.tiostitch.recreated.pets.utilities.others;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SkillType {
    COMBAT(new SkyBlockStats[]{SkyBlockStats.CRIT_CHANCE}),
    MINING(new SkyBlockStats[]{SkyBlockStats.DEFENSE}),
    FARMING(new SkyBlockStats[]{SkyBlockStats.HEALTH}),
    FORAGING(new SkyBlockStats[]{SkyBlockStats.STRENGTH}),
    FISHING(new SkyBlockStats[]{SkyBlockStats.HEALTH}),
    ENCHANTING(new SkyBlockStats[]{SkyBlockStats.INTELLIGENCE, SkyBlockStats.ABILITY_DAMAGE}),
    ALCHEMY(new SkyBlockStats[]{SkyBlockStats.INTELLIGENCE}),
    
    TAMING(new SkyBlockStats[]{SkyBlockStats.PET_LUCK}),
    DUNGEONEERING(new SkyBlockStats[]{SkyBlockStats.HEALTH}),
    CARPENTRY(new SkyBlockStats[]{SkyBlockStats.HEALTH}),
    SOCIAL(new SkyBlockStats[]{null}),
    RUNECRAFTING(new SkyBlockStats[]{null});

    private final SkyBlockStats[] buffedStat;

    public String getName() {
        String result = name();
        result = result.toLowerCase();
        result.replace(result.substring(0, 0), String.valueOf(result.charAt(0)).toUpperCase());
        return result;
    }
}
