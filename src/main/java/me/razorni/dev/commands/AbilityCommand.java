package me.razorni.dev.commands;

import lombok.SneakyThrows;
import me.razorni.dev.cooldown.Cooldowns;
import me.razorni.dev.gui.give.pages.GiveGUI;
import me.razorni.dev.util.build.ItemBuilder;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.rAbilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AbilityCommand implements CommandExecutor {


    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player || !(sender instanceof Player)) {

            if (!sender.hasPermission("rabilities.command.ability")) {
                sender.sendMessage(chatUtil.chat(rAbilities.config.getConfig().getString("NO-PERM")));
                return false;
            }
            if (args.length == 0) {
                for (String list : rAbilities.config.getConfig().getStringList("Invalid-Args")) {
                    sender.sendMessage(chatUtil.chat(list));
                }
                return false;
            }
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("give")) {
                    for (String list : rAbilities.config.getConfig().getStringList("Invalid-Args")) {
                        sender.sendMessage(chatUtil.chat(list));
                    }
                    return false;
                }
                if (args[0].equalsIgnoreCase("list")) {
                    for (String list : rAbilities.config.getConfig().getStringList("Ability-List-1")) {
                        sender.sendMessage(chatUtil.chat(list));
                    }
                    return false;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    rAbilities.configuration.reloadConfig();
                    rAbilities.config.reloadConfig();
                    sender.sendMessage(chatUtil.chat("&aYou have reloaded the config.yml."));
                    return false;
                }
                for (String list : rAbilities.config.getConfig().getStringList("Invalid-Args")) {
                    sender.sendMessage(chatUtil.chat(list));

                }
                return false;
            }
            if (args[0].equalsIgnoreCase("list") && args[1].equalsIgnoreCase("5")) {
                for (String list : rAbilities.config.getConfig().getStringList("Ability-List-5")) {
                    sender.sendMessage(chatUtil.chat(list));
                }
                return false;
            }
            if (args[0].equalsIgnoreCase("list") && args[1].equalsIgnoreCase("4")) {
                for (String list : rAbilities.config.getConfig().getStringList("Ability-List-4")) {
                    sender.sendMessage(chatUtil.chat(list));
                }
                return false;
            }
            if (args[0].equalsIgnoreCase("list") && args[1].equalsIgnoreCase("3")) {
                for (String list : rAbilities.config.getConfig().getStringList("Ability-List-3")) {
                    sender.sendMessage(chatUtil.chat(list));
                }
                return false;
            }
            if (args[0].equalsIgnoreCase("list") && args[1].equalsIgnoreCase("2")) {
                for (String list : rAbilities.config.getConfig().getStringList("Ability-List-2")) {
                    sender.sendMessage(chatUtil.chat(list));
                }
                return false;
            }
            if (args[0].equalsIgnoreCase("list") && args[1].equalsIgnoreCase("1")) {
                for (String list : rAbilities.config.getConfig().getStringList("Ability-List-1")) {
                    sender.sendMessage(chatUtil.chat(list));
                }
                return false;
            }
            Player target2 = Bukkit.getServer().getPlayer(args[1]);
            if (args[0].equalsIgnoreCase("give") && target2 != null) {
                Player player = (Player) sender;
                GiveGUI.openGiveGUI(player, target2);
                return false;
            }
            if (args[1].equalsIgnoreCase("resetcooldown") || args[1].equalsIgnoreCase("rc")) {
                if (target == null) {
                    for (String list : rAbilities.config.getConfig().getStringList("TARGET-NULL")) {
                        sender.sendMessage(chatUtil.chat(list));
                    }
                    return false;
                }
                if (args.length == 2) {
                    for (String list : rAbilities.config.getConfig().getStringList("Invalid-Args")) {
                        sender.sendMessage(chatUtil.chat(list));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("ability")) {
                    Cooldowns.getAbilitycd().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat("&eAbility")));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("rottenegg")) {
                    Cooldowns.getRottenegg().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat("&eAbility")));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("allcooldowns")) {
                    Cooldowns.getRottenegg().removeCooldown(target);
                    Cooldowns.getBone().removeCooldown(target);
                    Cooldowns.getAbilityreset().removeCooldown(target);
                    Cooldowns.getAbilitycd().removeCooldown(target);
                    Cooldowns.getAntitrapstar().removeCooldown(target);
                    Cooldowns.getAntiredstone().removeCooldown(target);
                    Cooldowns.getFreezer().removeCooldown(target);
                    Cooldowns.getMixer().removeCooldown(target);
                    Cooldowns.getPocketbard().removeCooldown(target);
                    Cooldowns.getPreagropearl().removeCooldown(target);
                    Cooldowns.getPrepearl().removeCooldown(target);
                    Cooldowns.getRageability().removeCooldown(target);
                    Cooldowns.getRocket().removeCooldown(target);
                    Cooldowns.getLuckyingot().removeCooldown(target);
                    Cooldowns.getTankingot().removeCooldown(target);
                    Cooldowns.getWebgun().removeCooldown(target);
                    Cooldowns.getSwitcher().removeCooldown(target);
                    Cooldowns.getSwapperaxe().removeCooldown(target);
                    Cooldowns.getHarpoon().removeCooldown(target);
                    Cooldowns.getAntifall().removeCooldown(target);
                    Cooldowns.getPotioncounter().removeCooldown(target);
                    Cooldowns.getPotioninheritor().removeCooldown(target);
                    Cooldowns.getGrappler().removeCooldown(target);
                    Cooldowns.getGuardianangel().removeCooldown(target);
                    Cooldowns.getAntipotion().removeCooldown(target);
                    Cooldowns.getFakepearl().removeCooldown(target);
                    Cooldowns.getBackstab().removeCooldown(target);
                    Cooldowns.getPoisondart().removeCooldown(target);
                    Cooldowns.getCleave().removeCooldown(target);
                    Cooldowns.getPotioncounter().removeCooldown(target);
                    Cooldowns.getPotioninheritor().removeCooldown(target);
                    Cooldowns.getWithergun().removeCooldown(target);
                    Cooldowns.getRefundcool().removeCooldown(target);

                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat("&9ALL COOLDOWNS")));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("switcher")) {
                    Cooldowns.getSwitcher().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("Switcher.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("rocket")) {
                    Cooldowns.getRocket().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("Rocket.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("antipot")) {
                    Cooldowns.getAntipotion().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("AntiPot.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("secondchance")) {
                    Cooldowns.getSecondchance().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("SecondChance.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("pocketbard")) {
                    Cooldowns.getPocketbard().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("tankingot")) {
                    Cooldowns.getTankingot().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("TankIngot.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("luckyingot")) {
                    Cooldowns.getLuckyingot().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("LuckyIngot.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("fakelogger")) {
                    Cooldowns.getFakelogger().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("FakeLogger.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("belchbomb")) {
                    Cooldowns.getBelchbomb().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PrePearl.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("prepearl")) {
                    Cooldowns.getPrepearl().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PrePearl.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("bone") || args[2].equalsIgnoreCase("exoticbone")) {
                    Cooldowns.getBone().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("ExoticBone.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("freezer") || args[2].equalsIgnoreCase("freezegun")) {
                    Cooldowns.getFreezer().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("Freezer.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("antitrapstar") || args[2].equalsIgnoreCase("trapstar")) {
                    Cooldowns.getAntitrapstar().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("AntiTrapStar.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("brick") || args[2].equalsIgnoreCase("ragebrick")) {
                    Cooldowns.getRagebrick().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("RageBrick.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("grappler") || args[2].equalsIgnoreCase("grapplinghook")) {
                    Cooldowns.getGrappler().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("Grappler.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("ability-reset") || args[2].equalsIgnoreCase("abilityreset")) {
                    Cooldowns.getAbilityreset().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("Ability-Reset.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("harpoon") || args[2].equalsIgnoreCase("harp")) {
                    Cooldowns.getHarpoon().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("Harpoon.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("br0invis") || args[2].equalsIgnoreCase("broinvis")) {
                    Cooldowns.getBroinvis().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("BroInvis.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("rage") || args[2].equalsIgnoreCase("rageability")) {
                    Cooldowns.getRageability().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("RageAbility.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("mixer") || args[2].equalsIgnoreCase("scrambler")) {
                    Cooldowns.getMixer().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("Mixer.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("guardianangel") || args[2].equalsIgnoreCase("angel")) {
                    Cooldowns.getGuardianangel().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("GuardianAngel.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("enderbutt") || args[2].equalsIgnoreCase("butt")) {
                    Cooldowns.getEnderbutt().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("EnderButt.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("telebow") || args[2].equalsIgnoreCase("teleportationbow")) {
                    Cooldowns.getTelebow().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("TeleBow.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("potcounter") || args[2].equalsIgnoreCase("potioncounter")) {
                    Cooldowns.getPotioncounter().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PotionCounter.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("switchstick") || args[2].equalsIgnoreCase("rotationstick")) {
                    Cooldowns.getSwitchstick().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("SwitchStick.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("withergun") || args[2].equalsIgnoreCase("wither")) {
                    Cooldowns.getWithergun().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("WitherGun.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("swapperaxe") || args[2].equalsIgnoreCase("swapper")) {
                    Cooldowns.getSwapperaxe().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("SwapperAxe.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("rottenegg") || args[2].equalsIgnoreCase("rotten")) {
                    Cooldowns.getRottenegg().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("RottenEgg.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("potinheritor")) {
                    Cooldowns.getPotioninheritor().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PotionInheritor.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("webgun") || args[2].equalsIgnoreCase("web")) {
                    Cooldowns.getWebgun().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("WebGun.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("antifall") || args[2].equalsIgnoreCase("antifallboots")) {
                    Cooldowns.getAntifall().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("AntiFall.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("antiredstone") || args[2].equalsIgnoreCase("redstonerod")) {
                    Cooldowns.getAntiredstone().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("AntiRedstone.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("impulse") || args[2].equalsIgnoreCase("impulsebomb")) {
                    Cooldowns.getCleave().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PotionInheritor.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("cleave") || args[2].equalsIgnoreCase("cleaver")) {
                    Cooldowns.getCleave().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PotionInheritor.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("debuff") || args[2].equalsIgnoreCase("debufffish")) {
                    Cooldowns.getDebufffish().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("NinjaStar.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("ninjastar") || args[2].equalsIgnoreCase("ninja")) {
                    Cooldowns.getNinjastar().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("NinjaStar.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("potioninheritor") || args[2].equalsIgnoreCase("potionsee")) {
                    Cooldowns.getPotioncounter().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PotionInheritor.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("fakepearl") || args[2].equalsIgnoreCase("pearlfake")) {
                    Cooldowns.getFakepearl().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PotionInheritor.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("backstab") || args[2].equalsIgnoreCase("stab")) {
                    Cooldowns.getBackstab().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("BackStab.Name"))));
                    }
                    return false;
                }
                if (args[2].equalsIgnoreCase("poisondart") || args[2].equalsIgnoreCase("dart")) {
                    Cooldowns.getPoisondart().removeCooldown(target);
                    for (String list : rAbilities.config.getConfig().getStringList("Message.Item.Reset-Cooldown")) {
                        sender.sendMessage(chatUtil.chat(list).replaceAll("%target%", target.getName()).replaceAll("%timer%",
                                chatUtil.chat(rAbilities.config.getConfig().getString("PoisonDart.Name"))));
                    }
                    return false;
                }
                return false;
            }
            if (args.length == 2) {
                for (String list : rAbilities.config.getConfig().getStringList("Invalid-Args")) {
                    sender.sendMessage(chatUtil.chat(list));
                }
                return false;
            }
            int amount;

            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage(chatUtil.chat("&cThat's not a valid number!"));
                return false;
            }
            if (target == null) {
                for (String list : rAbilities.config.getConfig().getStringList("TARGET-NULL")) {
                    sender.sendMessage(chatUtil.chat(list));
                }
                return false;
            }
            if (args[1].equalsIgnoreCase("harpoon")) {
                //ItemStack stack = new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("Harpoon.Item")), amount);

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("Harpoon.Lore")) {
                    lore.add(chatUtil.chat(list));
                }

                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Harpoon.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("Harpoon.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("debufffish")) {
                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("DebuffFish.Lore")) {
                    lore.add(chatUtil.chat(list));
                }

                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("DebuffFish.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("DebuffFish.Name")))
                        .setLore(lore).data((short) rAbilities.config.getConfig().getInt("DebuffFish.Data")).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("antitrapstar") || args[1].equalsIgnoreCase("trapstar")) {
                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("AntiTrapStar.Lore")) {
                    lore.add(chatUtil.chat(list));
                }

                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("AntiTrapStar.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("AntiTrapStar.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("backstab")) {
                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("BackStab.Lore")) {
                    lore.add(chatUtil.chat(list));
                }

                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("BackStab.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("BackStab.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("poisondart")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PoisonDart.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PoisonDart.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PoisonDart.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("broinvis")) {
                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("BroInvis.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("BroInvis.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("BroInvis.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("potioninheritor")) {
                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PotionInheritor.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PotionInheritor.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PotionInheritor.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }

            if (args[1].equalsIgnoreCase("cleave")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("Cleave.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Cleave.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("Cleave.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("secondchance")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("SecondChance.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("SecondChance.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("SecondChance.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("potioncounter")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PotionCounter.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PotionCounter.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PotionCounter.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }

            if (args[1].equalsIgnoreCase("rottenegg")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("RottenEgg.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("RottenEgg.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("RottenEgg.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }

            if (args[1].equalsIgnoreCase("guardianangel")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("GuardianAngel.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("GuardianAngel.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("GuardianAngel.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("rageability")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("RageAbility.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("RageAbility.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("RageAbility.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("antiredstone")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("AntiRedstone.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("AntiRedstone.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("AntiRedstone.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("webgun")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("WebGun.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("WebGun.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("WebGun.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("mixer")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("Mixer.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Mixer.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("Mixer.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("tankingot")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("TankIngot.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("TankIngot.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("TankIngot.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("freezer")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("Freezer.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Freezer.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("Freezer.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("swapperaxe")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("SwapperAxe.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("SwapperAxe.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("SwapperAxe.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("antifall")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("AntiFall.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("AntiFall.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("AntiFall.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("fakepearl")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("FakePearl.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("FakePearl.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("FakePearl.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("grappler")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("Grappler.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Grappler.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("Grappler.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("switchstick")) {
                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("SwitchStick.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("SwitchStick.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("SwitchStick.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("antipot")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("AntiPotion.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("AntiPotion.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("AntiPotion.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("enderbutt")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("EnderButt.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("EnderButt.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("EnderButt.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("teleportationbow") || args[1].equalsIgnoreCase("telebow")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("TeleBow.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("TeleBow.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("TeleBow.Name")))
                        .setLore(lore).build();
                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("ImpulseBomb")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("ImpulseBomb.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("ImpulseBomb.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("ImpulseBomb.Name")))
                        .setLore(lore).build();
                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("prepearl")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PrePearl.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PrePearl.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PrePearl.Name")))
                        .setLore(lore).build();
                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("ragebrick")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("RageBrick.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("RageBrick.Item")), amount,
                        (byte) rAbilities.getInstance().getConfig().getInt("RageBrick.Data")).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("RageBrick.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("fakelogger")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("FakeLogger.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("FakeLogger.Item")), amount,
                        (byte) rAbilities.getInstance().getConfig().getInt("FakeLogger.Data")).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("FakeLogger.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("pocketbard")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PocketBard.PocketBard.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.PocketBard.Item")), amount,
                        (byte) rAbilities.getInstance().getConfig().getInt("PocketBard.PocketBard.Data")).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("bardinvis")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Invis.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Invis.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.Invis.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("bardwither")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Wither.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Wither.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.Wither.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("bardjumpboost")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PocketBard.JumpBoost.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.JumpBoost.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.JumpBoost.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("bardspeed")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Speed.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Speed.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.Speed.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("bardstrength")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Strength.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Strength.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.Strength.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("bardres")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Resistance.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Resistance.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.Resistance.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("bardfireres")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Regen.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Regen.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.Regen.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("bardregen")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("PocketBard.Regen.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Regen.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("PocketBard.Regen.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("abilityreset")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("Ability-Reset.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Ability-Reset.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("Ability-Reset.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("switcher")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("Switcher.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Switcher.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("Switcher.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("withergun")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("WitherGun.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("WitherGun.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("WitherGun.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("ninjastar")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("NinjaStar.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("NinjaStar.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("NinjaStar.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("belchbomb")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("BelchBomb.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("BelchBomb.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("BelchBomb.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("luckyingot")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("LuckyIngot.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("LuckyIngot.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("LuckyIngot.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
            }
            if (args[1].equalsIgnoreCase("rocket")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("Rocket.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("Rocket.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("Rocket.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
                return false;
            }
            if (args[1].equalsIgnoreCase("exoticbone")) {

                List<String> lore = new ArrayList<>();
                for (String list : rAbilities.config.getConfig().getStringList("ExoticBone.Lore")) {
                    lore.add(chatUtil.chat(list));
                }
                ItemStack stack = new ItemBuilder(Material.valueOf(rAbilities.config.getConfig().getString("ExoticBone.Item")), amount).displayName(
                        chatUtil.chat(rAbilities.config.getConfig().getString("ExoticBone.Name")))
                        .setLore(lore).build();

                for (String list : rAbilities.config.getConfig().getStringList("Message.Target-Give")) {
                    target.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                for (String list : rAbilities.config.getConfig().getStringList("Message.Sender-Give")) {
                    sender.sendMessage(chatUtil.chat(list.replaceAll("%target%", target.getName()).replaceAll("%ability%", stack.getItemMeta().getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
                }
                target.getInventory().addItem(stack);
                return false;
            }
        }
        return false;
    }
}