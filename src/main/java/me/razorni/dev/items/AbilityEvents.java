package me.razorni.dev.items;

import me.razorni.dev.rAbilities;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.razorni.dev.util.chatUtil;

import java.util.List;

public enum AbilityEvents {

    TANKINGOT(rAbilities.config.getConfig().getString("TankIngot.Name"),
            rAbilities.config.getConfig().getStringList("TankIngot.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("TankIngot.Item")), 1)),
    PREPEARL(rAbilities.config.getConfig().getString("PrePearl.Name"),
            rAbilities.config.getConfig().getStringList("PrePearl.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PrePearl.Item")), 1)),
    EXOTICBONE(rAbilities.config.getConfig().getString("ExoticBone.Name"),
            rAbilities.config.getConfig().getStringList("ExoticBone.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("ExoticBone.Item")), 1)),
    POCKETBARD(rAbilities.config.getConfig().getString("PocketBard.PocketBard.Name"),
            rAbilities.config.getConfig().getStringList("PocketBard.PocketBard.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.PocketBard.Item")), 1)),
    INVIS(rAbilities.config.getConfig().getString("PocketBard.Invis.Name"),
            rAbilities.config.getConfig().getStringList("PocketBard.Invis.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Invis.Item")), 1)),
    STRENGTH(rAbilities.config.getConfig().getString("PocketBard.Strength.Name"),
            rAbilities.config.getConfig().getStringList("PocketBard.Strength.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Strength.Item")), 1)),
    NINJASTAR(rAbilities.config.getConfig().getString("NinjaStar.Name"),
            rAbilities.config.getConfig().getStringList("NinjaStar.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("NinjaStar.Item")), 1)),
    JUMP(rAbilities.config.getConfig().getString("PocketBard.JumpBoost.Name"),
            rAbilities.config.getConfig().getStringList("PocketBard.JumpBoost.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.JumpBoost.Item")), 1)),
    SPEED(rAbilities.config.getConfig().getString("PocketBard.Speed.Name"),
            rAbilities.config.getConfig().getStringList("PocketBard.Speed.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Speed.Item")), 1)),
    REGEN(rAbilities.config.getConfig().getString("PocketBard.Regen.Name"),
            rAbilities.config.getConfig().getStringList("PocketBard.Regen.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Regen.Item")), 1)),
    RES(rAbilities.config.getConfig().getString("PocketBard.Resistance.Name"),
            rAbilities.config.getConfig().getStringList("PocketBard.Resistance.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Resistance.Item")), 1)),
    WITHER(rAbilities.config.getConfig().getString("PocketBard.Wither.Name"),
            rAbilities.config.getConfig().getStringList("PocketBard.Wither.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PocketBard.Wither.Item")), 1)),
    LUCKYINGOT(rAbilities.config.getConfig().getString("LuckyIngot.Name"),
            rAbilities.config.getConfig().getStringList("LuckyIngot.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("LuckyIngot.Item")), 1)),
    TELEBOW(rAbilities.config.getConfig().getString("TeleBow.Name"),
            rAbilities.config.getConfig().getStringList("TeleBow.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("TeleBow.Item")), 1)),
    BELCHBOMB(rAbilities.config.getConfig().getString("BelchBomb.Name"),
            rAbilities.config.getConfig().getStringList("BelchBomb.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("BelchBomb.Item")), 1)),
    SWITCHER(rAbilities.config.getConfig().getString("Switcher.Name"),
            rAbilities.config.getConfig().getStringList("Switcher.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("Switcher.Item")), 1)),
    SECONDCHANCE(rAbilities.config.getConfig().getString("SecondChance.Name"),
            rAbilities.config.getConfig().getStringList("SecondChance.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("SecondChance.Item")), 1)),
    BROINVIS(rAbilities.config.getConfig().getString("BroInvis.Name"),
            rAbilities.config.getConfig().getStringList("BroInvis.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("BroInvis.Item")), 1)),
    ANTITRAPSTAR(rAbilities.config.getConfig().getString("AntiTrapStar.Name"),
            rAbilities.config.getConfig().getStringList("AntiTrapStar.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("AntiTrapStar.Item")), 1)),
    FAKELOGGER(rAbilities.config.getConfig().getString("FakeLogger.Name"),
            rAbilities.config.getConfig().getStringList("FakeLogger.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("FakeLogger.Item")), 1)),
    DEBUFFFISH(rAbilities.config.getConfig().getString("DebuffFish.Name"),
            rAbilities.config.getConfig().getStringList("DebuffFish.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("DebuffFish.Item")), 1)),
    SWAPPERAXE(rAbilities.config.getConfig().getString("SwapperAxe.Name"),
            rAbilities.config.getConfig().getStringList("SwapperAxe.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("SwapperAxe.Item")), 1)),
    STICK(rAbilities.config.getConfig().getString("SwitchStick.Name"),
            rAbilities.config.getConfig().getStringList("SwitchStick.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("SwitchStick.Item")), 1)),
    ANTIFALL(rAbilities.config.getConfig().getString("AntiFall.Name"),
            rAbilities.config.getConfig().getStringList("AntiFall.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("AntiFall.Item")), 1)),
    GRAPPLER(rAbilities.config.getConfig().getString("Grappler.Name"),
            rAbilities.config.getConfig().getStringList("Grappler.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("Grappler.Item")), 1)),
    FREEZER(rAbilities.config.getConfig().getString("Freezer.Name"),
            rAbilities.config.getConfig().getStringList("Freezer.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("Freezer.Item")), 1)),
    WITHERGUN(rAbilities.config.getConfig().getString("WitherGun.Name"),
            rAbilities.config.getConfig().getStringList("WitherGun.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("WitherGun.Item")), 1)),
    ABILITYRESET(rAbilities.config.getConfig().getString("Ability-Reset.Name"),
            rAbilities.config.getConfig().getStringList("Ability-Reset.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("Ability-Reset.Item")), 1)),
    WEBGUN(rAbilities.config.getConfig().getString("WebGun.Name"),
            rAbilities.config.getConfig().getStringList("WebGun.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("WebGun.Item")), 1)),
    MIXER(rAbilities.config.getConfig().getString("Mixer.Name"),
            rAbilities.config.getConfig().getStringList("Mixer.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("Mixer.Item")), 1)),
    RAGEABILITY(rAbilities.config.getConfig().getString("RageAbility.Name"),
            rAbilities.config.getConfig().getStringList("RageAbility.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("RageAbility.Item")), 1)),
    GUARDIAN(rAbilities.config.getConfig().getString("GuardianAngel.Name"),
            rAbilities.config.getConfig().getStringList("GuardianAngel.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("GuardianAngel.Item")), 1)),
    HARPOON(rAbilities.config.getConfig().getString("Harpoon.Name"),
            rAbilities.config.getConfig().getStringList("Harpoon.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("Harpoon.Item")), 1)),
    ANTIREDSTONE(rAbilities.config.getConfig().getString("AntiRedstone.Name"),
            rAbilities.config.getConfig().getStringList("AntiRedstone.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("AntiRedstone.Item")), 1)),
    ROTTENEGG(rAbilities.config.getConfig().getString("RottenEgg.Name"),
            rAbilities.config.getConfig().getStringList("RottenEgg.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("RottenEgg.Item")), 1)),
    POTIONCOUNTER(rAbilities.config.getConfig().getString("PotionCounter.Name"),
            rAbilities.config.getConfig().getStringList("PotionCounter.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PotionCounter.Item")), 1)),
    POTIONINHERITOR(rAbilities.config.getConfig().getString("PotionInheritor.Name"),
            rAbilities.config.getConfig().getStringList("PotionInheritor.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PotionInheritor.Item")), 1)),
    ANTIPOT(rAbilities.config.getConfig().getString("AntiPotion.Name"),
            rAbilities.config.getConfig().getStringList("AntiPotion.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("AntiPotion.Item")), 1)),
    CLEAVE(rAbilities.config.getConfig().getString("Cleave.Name"),
            rAbilities.config.getConfig().getStringList("Cleave.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("Cleave.Item")), 1)),
    ENDERBUTT(rAbilities.config.getConfig().getString("EnderButt.Name"),
            rAbilities.config.getConfig().getStringList("EnderButt.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("EnderButt.Item")), 1)),
    BACKSTAB(rAbilities.config.getConfig().getString("BackStab.Name"),
            rAbilities.config.getConfig().getStringList("BackStab.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("BackStab.Item")), 1)),
    POISONDART(rAbilities.config.getConfig().getString("PoisonDart.Name"),
            rAbilities.config.getConfig().getStringList("PoisonDart.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("PoisonDart.Item")), 1)),
    RAGEBRICK(rAbilities.config.getConfig().getString("RageBrick.Name"),
            rAbilities.config.getConfig().getStringList("RageBrick.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("RageBrick.Item")), 1)),
    IMPULSE(rAbilities.config.getConfig().getString("ImpulseBomb.Name"),
            rAbilities.config.getConfig().getStringList("ImpulseBomb.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("ImpulseBomb.Item")), 1)),
    FAKEPEARL(rAbilities.config.getConfig().getString("FakePearl.Name"),
            rAbilities.config.getConfig().getStringList("FakePearl.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("FakePearl.Item")), 1)),
    ROCKET(rAbilities.config.getConfig().getString("Rocket.Name"),
            rAbilities.config.getConfig().getStringList("Rocket.Lore"),
            new ItemStack(Material.valueOf(rAbilities.config.getConfig().getString("Rocket.Item")), 1));

    private String display;
    private List<String> lore;
    public ItemStack stack;

    AbilityEvents(String display, List<String> lore, ItemStack stack) {
        this.display = display;
        this.lore = lore;
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public String getDisplay() {
        return chatUtil.chat(display);
    }

    public List<String> getLore() {
        return chatUtil.list(lore);
    }

    public boolean isSimilar(ItemStack toCompare) {
        return toCompare != null &&
                toCompare.getType().equals(stack.getType()) &&
                toCompare.hasItemMeta() &&
                toCompare.getItemMeta().hasDisplayName() &&
                toCompare.getItemMeta().getDisplayName().equals(chatUtil.chat(getDisplay())) &&
                toCompare.getItemMeta().hasLore() &&
                toCompare.getItemMeta().getLore().equals(chatUtil.list(getLore()));
    }

    public static void takeItem(Player player, ItemStack stack) {
        if (stack.getAmount() > 1) {
            stack.setAmount(stack.getAmount() - 1);
        } else {
            player.setItemInHand(null);
        }
    }

    public static void takeItem(ItemStack stack) {
        if (stack.getAmount() > 1) {
            stack.setAmount(stack.getAmount() - 1);
        } else {
            stack.setAmount(0);
        }
    }

    public static int getPots(Player player) {
        int pots = 0;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            ItemStack stack = contents[i];
            if (stack != null && stack.getType() != null) {
                if (stack.getType() != Material.AIR) {
                    if (stack.getType() == Material.POTION) {
                        ++pots;
                    }
                }
            }
        }
        return pots;
    }
    
    public static boolean checkLocation(Location loc, int radius, int x, int z) {
        radius = radius + 1;
        int cornerx = x + radius;
        int cornerz = z + radius;
        int cornerx1 = x - radius;
        int cornerz1 = z - radius;
        if (loc.getBlockX() < cornerx && loc.getBlockX() > cornerx1) {
            if (loc.getBlockZ() < cornerz && loc.getBlockZ() > cornerz1) {
                return true;
            }
        }
        return false;
    }
}