package org.tiostitch.recreated.pets.utilities;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.tiostitch.recreated.pets.Main;
import org.tiostitch.recreated.pets.manager.Pet;
import org.tiostitch.recreated.pets.utilities.others.Rarity;
import org.tiostitch.recreated.pets.utilities.others.SkyBlockStats;
import org.tiostitch.recreated.pets.utilities.others.StatType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
public final class PetBuilder {

    private final Main main;

    public ItemStack getPetItem(Pet pet) {

        //DEFINED METHODS
        final PetBase petBase = pet.getPetBase();
        final String PET_NAME = petBase.getName();
        final Rarity PET_RARITY = pet.getRarity();
        final int PET_LEVEL = pet.getLevel();
        //

        final ItemStack petItem = getTexture(petBase.getTexture());
        final ItemMeta petMeta = petItem.getItemMeta();

        petMeta.setDisplayName(getPetRarityName(PET_NAME, PET_LEVEL, PET_RARITY));

        final ArrayList<String> lore = new ArrayList<>();
        lore.add("§8" + petBase.getSkillType().getName() + " Pet");
        lore.add("");
        lore.addAll(statsLore(PET_LEVEL, petBase.getUpgrade_stats().get(pet.getRarity())));
        lore.addAll(abilityLore(PET_LEVEL, PET_RARITY, petBase.getAbilities().get(pet.getRarity())));
        lore.add(PET_RARITY.getColor() + "§l" + PET_RARITY.name());

        petMeta.setLore(lore);
        petItem.setItemMeta(petMeta);
        return petItem;
    }

    public String getPetRarityName(String petName, int level, Rarity rarity) {
        return "§7[Lv " + level + "§7] " + rarity.getColor() + petName;
    }

    public ArrayList<String> statsLore(int level, Map<SkyBlockStats, Double> stats) {

        final ArrayList<String> lore = new ArrayList<>();

        StatType oldType = null;
        for (SkyBlockStats stat : stats.keySet()) {

            double value = (level * stats.get(stat));

            if (oldType != null && oldType != stat.getStatType()) {
                lore.add("");
            }

            lore.add("§7" + stat.getReference() + ":" + " §c+" + value);
            oldType = stat.getStatType();
        }

        lore.add("");
        return lore;
    }

    public ArrayList<String> abilityLore(int level, Rarity rarity, List<AbilityBase> abilities) {

        final ArrayList<String> lore = new ArrayList<>();

        for (AbilityBase abilityBase : abilities) {

            double baseFirst = abilityBase.getAbilityBonuses().get(rarity).getBaseFirstBonus();
            double baseSeco = abilityBase.getAbilityBonuses().get(rarity).getBaseSecondBonus();

            double perLevelFirst = abilityBase.getAbilityBonuses().get(rarity).getPerLevelFirstBonus();
            double perLevelSeco = abilityBase.getAbilityBonuses().get(rarity).getPerLevelSecondBonus();

            double firstValue = (baseFirst + (level * perLevelFirst));
            double secondValue = (baseSeco + (level * perLevelSeco));

            final List<String> description = abilityBase.getDescription();
            description.replaceAll(s -> s.replaceAll("%X", String.valueOf(firstValue)));
            description.replaceAll(s -> s.replaceAll("%Y", String.valueOf(secondValue)));

            lore.addAll(abilityBase.getDescription());
        }

        return lore;
    }

    public ItemStack getTexture(String texture) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta hm = (SkullMeta) item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));
        try {
            Field field = hm.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(hm, profile);
        } catch(IllegalArgumentException  | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        item.setItemMeta(hm);
        return item;
    }
}
