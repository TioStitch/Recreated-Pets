package org.tiostitch.recreated.pets.commands;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.tiostitch.recreated.pets.Main;
import org.tiostitch.recreated.pets.configuration.StoragedFiles;
import org.tiostitch.recreated.pets.database.dataTypes.IPetData;
import org.tiostitch.recreated.pets.manager.Pet;
import org.tiostitch.recreated.pets.manager.SkyBlockPlayer;
import org.tiostitch.recreated.pets.utilities.AbilityBase;
import org.tiostitch.recreated.pets.utilities.AbilityBonus;
import org.tiostitch.recreated.pets.utilities.PetBase;
import org.tiostitch.recreated.pets.utilities.others.Rarity;

@RequiredArgsConstructor
public final class AdminCMD
implements CommandExecutor {

    private final Main main;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) return true;

        if (args.length == 0) {
            sender.sendMessage("");
            sender.sendMessage("§e§lPETS COMMANDS");
            sender.sendMessage("");
            sender.sendMessage("§fMain commands:");
            sender.sendMessage(" §e/petsDebug pet list");
            sender.sendMessage(" §e/petsDebug data list");
            sender.sendMessage(" §e/petsDebug ability list");
            sender.sendMessage("");
            sender.sendMessage(" §e/petsDebug pet give <nickname>");
            sender.sendMessage("");
            sender.sendMessage(" §e/petsDebug pet view <pet_name>");
            sender.sendMessage(" §e/petsDebug data view <nickname>");
            sender.sendMessage(" §e/petsDebug ability view <ability_name>");
            sender.sendMessage("");
            return true;
        }

        {
            if (args[0].equalsIgnoreCase("pet")) {

                if (args[1].equalsIgnoreCase("list")) {
                    sender.sendMessage("");
                    sender.sendMessage("§fPets list:");
                    sender.sendMessage("");

                    StoragedFiles.getStoraged_pets().keySet().forEach(pet -> {
                        sender.sendMessage("§e- " + pet);
                    });

                    sender.sendMessage("");
                    return true;
                }

                if (args[1].equalsIgnoreCase("view")) {

                    final Rarity rarity = Rarity.valueOf(args[2]);

                    PetBase petBase = StoragedFiles.getStoraged_pets().get(args[2]);

                    sender.sendMessage("");
                    sender.sendMessage(" §fPet Name: §e" + petBase.getName());
                    sender.sendMessage(" §fPet Skill: §e" + petBase.getSkillType().name());
                    sender.sendMessage("");
                    sender.sendMessage(" §fPet Abilities: §e" + petBase.getAbilities());
                    sender.sendMessage(" §fPet Stats: §e" + petBase.getUpgrade_stats().size());

                    petBase.getUpgrade_stats().get(rarity).keySet().forEach(attribute -> {
                        sender.sendMessage("§e" + attribute.getReference());
                    });
                    sender.sendMessage("");

                    return true;
                }

                if (args[1].equalsIgnoreCase("give")) {

                    if (args.length == 2) {
                        sender.sendMessage("§cUse /petsdebug give <player> <pet_name> <pet_rarity> <pet_level>");
                        return false;
                    }

                    final Player player = Bukkit.getPlayer(args[2]);
                    if (player != null) {

                        final String pet_name = args[3];
                        final Rarity rarity = Rarity.valueOf(args[4]);
                        final int pet_level = Integer.parseInt(args[5]);

                        final Pet pet = new Pet();
                        pet.setPetBase(StoragedFiles.getStoraged_pets().get(pet_name));
                        pet.setLevel(pet_level);
                        pet.setRarity(rarity);
                        pet.setCurrentExp(0.0);

                        player.getInventory().addItem(main.getPetBuilder().getPetItem(pet));

                        sender.sendMessage("§aAdded §6" + (pet.getPetBase().getName()) + " §afor player " + player.getName());
                        return false;
                    }

                    return true;
                }

                return true;
            }

            if (args[0].equalsIgnoreCase("ability")) {

                if (args[1].equalsIgnoreCase("list")) {
                    sender.sendMessage("");
                    sender.sendMessage("§fAbilities list:");
                    sender.sendMessage("");

                    StoragedFiles.getStoraged_abilities().keySet().forEach(ability -> {
                        sender.sendMessage("§e- " + ability);
                    });

                    sender.sendMessage("");
                    return true;
                }

                if (args[1].equalsIgnoreCase("view")) {

                    AbilityBase abilityBase = StoragedFiles.getStoraged_abilities().get(args[2]);

                    sender.sendMessage("");
                    sender.sendMessage(" §fAbility Description:");

                    for (String line : abilityBase.getDescription()) {
                        sender.sendMessage(line);
                    }
                    sender.sendMessage("");
                    for (Rarity rarity : Rarity.values()) {

                        final AbilityBonus abilityBonus = abilityBase.getAbilityBonuses().get(rarity);

                        sender.sendMessage(rarity.getColor() + rarity.getPrefix());
                        sender.sendMessage(" §f- X Bonuses: §e" + abilityBonus.getBaseFirstBonus());
                        sender.sendMessage(" §f- Y Bonuses: §e" + abilityBonus.getBaseSecondBonus());
                        sender.sendMessage("");
                    }

                    return true;
                }

                return true;
            }

            if (args[0].equalsIgnoreCase("data")) {

                if (args[1].equalsIgnoreCase("list")) {
                    sender.sendMessage("");
                    sender.sendMessage("§fLoaded Players:");
                    sender.sendMessage("");

                    main.getLoaded_players().keySet().forEach(data -> {
                        sender.sendMessage("§e- " + data);
                    });

                    sender.sendMessage("");
                    return true;
                }

                if (args[1].equalsIgnoreCase("view")) {

                    IPetData iPetData = main.getPetData().openConnection();
                    if (!iPetData.existData(args[2])) {
                        sender.sendMessage("§cThis player data doesn't exist!");
                        return true;
                    }

                    final SkyBlockPlayer skyBlockPlayer = iPetData.getData(args[2]);

                    sender.sendMessage("");
                    sender.sendMessage(" §fPlayer Data Description:");
                    sender.sendMessage("");
                    sender.sendMessage(" §fPlayer Name: §e" + skyBlockPlayer.getName());
                    sender.sendMessage("");
                    sender.sendMessage(" §fStored pets:");

                    if (skyBlockPlayer.getStored_pets().isEmpty()) {
                        sender.sendMessage("§cNo stored pets in moment! :(");
                        sender.sendMessage("");
                        return true;
                    }

                    skyBlockPlayer.getStored_pets().forEach(key -> {
                        final String PET_NAME = (key.getRarity().getColor() + key.getPetBase().getName());
                        final String PET_LEVEL = "§7[Lv " + key.getLevel() + "§7]";

                        sender.sendMessage("§e- " + PET_NAME + " §7- " + PET_LEVEL);
                    });
                    sender.sendMessage("");

                    return true;
                }

                return true;
            }
        }

        return true;
    }
}
