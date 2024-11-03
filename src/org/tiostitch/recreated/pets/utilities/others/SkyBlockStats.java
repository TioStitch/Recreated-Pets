package org.tiostitch.recreated.pets.utilities.others;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SkyBlockStats {
    HEALTH("Health", StatType.COMBAT_STATS),
    DEFENSE("Defense", StatType.COMBAT_STATS),
    STRENGTH("Strength", StatType.COMBAT_STATS),
    INTELLIGENCE("Intelligence", StatType.COMBAT_STATS),
    CRIT_CHANCE("Crit Chance", StatType.COMBAT_STATS),
    CRIT_DAMAGE("Crit Damage", StatType.COMBAT_STATS),
    BONUS_ATTACK_SPEED("Defense", StatType.COMBAT_STATS),
    ABILITY_DAMAGE("Ability Damage", StatType.COMBAT_STATS),
    TRUE_DEFENSE("True Defense", StatType.COMBAT_STATS),
    FEROCITY("Ferocity", StatType.COMBAT_STATS),
    HEALTH_REGEN("Health Regen", StatType.COMBAT_STATS),
    VITALITY("Vitality", StatType.COMBAT_STATS),
    MENDING("Mending", StatType.COMBAT_STATS),
    SWING_RANGE("Swing Range", StatType.COMBAT_STATS),

    MINING_SPEED("Mining Speed", StatType.GATHERING_STATS),
    MINING_FORTUNE("Mining Fortune", StatType.GATHERING_STATS),
    FARMING_FORTUNE("Farming Fortune", StatType.GATHERING_STATS),
    FORAGING_FORTUNE("Foraging Fortune", StatType.GATHERING_STATS),
    BREAKING_POWER("Breaking Power", StatType.GATHERING_STATS),
    PRISTINE("Pristine", StatType.GATHERING_STATS),

    ALCHEMY_WISDOWN("Alchemy Wisdown", StatType.WISDOM_STATS),
    CARPENTRY_WISDOWN("Carpentry Wisdown", StatType.WISDOM_STATS),
    COMBAT_WISDOWN("Combat Wisdown", StatType.WISDOM_STATS),
    ENCHANTING_WISDOWN("Enchanting Wisdown", StatType.WISDOM_STATS),
    FARMING_WISDOWN("Farming Wisdown", StatType.WISDOM_STATS),
    FISHING_WISDOWN("Fishing Wisdown", StatType.WISDOM_STATS),
    FORAGING_WISDOWN("Foraging Wisdown", StatType.WISDOM_STATS),
    MINING_WISDOWN("Mining Wisdown", StatType.WISDOM_STATS),
    RUNECRAFTING_WISDOWN("Runecrafting Wisdown", StatType.WISDOM_STATS),
    SOCIAL_WISDOWN("Social Wisdown", StatType.WISDOM_STATS),
    TAMING_WISDOWN("Taming Wisdown", StatType.WISDOM_STATS),

    SPEED("Speed", StatType.MISC_STATS),
    MAGIC_FIND("Magic Find", StatType.MISC_STATS),
    PET_LUCK("Pet Luck", StatType.MISC_STATS),
    DOUBLE_HOOK_CHANCE("Double Hook Chance", StatType.MISC_STATS),
    SEA_CREATURE_CHANCE("Sea Creature Chance", StatType.MISC_STATS),
    FISHING_SPEED("Fishing Speed", StatType.MISC_STATS),
    BONUS_PEST_CHANCE("Bonus Pest Chance", StatType.MISC_STATS),
    COLD_RESISTANCE("Cold Resistance", StatType.MISC_STATS),

    ABSORPTION("Absorption", StatType.OTHER_STATS),
    DAMAGE("Damage", StatType.OTHER_STATS),
    FUEL("Fuel", StatType.OTHER_STATS),
    HEAT("Heat", StatType.OTHER_STATS),
    COLD("Cold", StatType.OTHER_STATS),
    MANA("Mana", StatType.OTHER_STATS),
    OVERFLOW("Overflow", StatType.OTHER_STATS),
    TRUE_DAMAGE("True Damage", StatType.OTHER_STATS);

    private final String reference;
    private final StatType statType;
}
