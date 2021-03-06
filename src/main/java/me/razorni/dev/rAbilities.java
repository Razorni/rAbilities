package me.razorni.dev;

import lombok.Getter;
import lombok.SneakyThrows;
import me.razorni.dev.items.abilities.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.plugin.java.JavaPlugin;
import me.razorni.dev.commands.AbilityCommand;
import me.razorni.dev.cooldown.Cooldowns;
import me.razorni.dev.gui.abilityreset.AbilityResetGUIListener;
import me.razorni.dev.items.pocketbards.PocketBards;
import me.razorni.dev.util.config.JavaUtils;
import me.razorni.dev.util.config.YamlDoc;
import me.razorni.dev.util.chatUtil;
import me.razorni.dev.gui.pocketbard.PocketBardGUIListener;
import me.razorni.dev.items.refund.Refund;
import me.razorni.dev.gui.give.pages.GiveGUIListener;

import java.util.HashMap;
import java.util.Map;

// razorni is a dog big big dog tessst

public class rAbilities extends JavaPlugin {
    // Cooldowns
    @Getter public static rAbilities instance;

    @Getter public static Cooldowns cooldowns;

    @Getter public static Map<Player, Egg> eggRefunds;

    @Getter public static Map<Player, Snowball> snowballRefunds;

    public static YamlDoc config;

    @Getter public static YamlDoc configuration;

    @Getter private Map<Player, Player> playerMap;

    @SneakyThrows
    @Override
    public void onEnable() {

        instance = this;
        cooldowns = new Cooldowns();
        eggRefunds = new HashMap<>();
        snowballRefunds = new HashMap<>();
        playerMap = new HashMap<>();
        this.saveDefaultConfig();
        config = new YamlDoc(getDataFolder(), "config.yml");
        config.init();
        configuration = new YamlDoc(getDataFolder(), "gui.yml");
        configuration.init();

        registerAbilities();
        registerCommands();
        Bukkit.getServer().getConsoleSender().sendMessage(chatUtil.chat("&7&m--------------------"));
        Bukkit.getServer().getConsoleSender().sendMessage(chatUtil.chat("&6rAbilities &fhas been &aenabled."));
        Bukkit.getServer().getConsoleSender().sendMessage(chatUtil.chat("&fForked by &eRazorni#7254"));
        Bukkit.getServer().getConsoleSender().sendMessage(chatUtil.chat("&7&m--------------------"));

    }

    public void registerCommands() {
        this.getCommand("ability").setExecutor(new AbilityCommand());
    }

    public void registerAbilities() {
        Bukkit.getPluginManager().registerEvents(new AbilityReset(), this);
        Bukkit.getPluginManager().registerEvents(new AntiTrapStar(), this);
        Bukkit.getPluginManager().registerEvents(new AntiFallBoots(), this);
        Bukkit.getPluginManager().registerEvents(new AntiPot(), this);
        Bukkit.getPluginManager().registerEvents(new AntiRedstone(), this);
        Bukkit.getPluginManager().registerEvents(new BelchBomb(), this);
        Bukkit.getPluginManager().registerEvents(new Br0Invis(), this);
        Bukkit.getPluginManager().registerEvents(new Cleave(), this);
        Bukkit.getPluginManager().registerEvents(new DebuffFish(), this);
        Bukkit.getPluginManager().registerEvents(new EnderButt(), this);
        Bukkit.getPluginManager().registerEvents(new ExoticBone(), this);
        Bukkit.getPluginManager().registerEvents(new Freezer(), this);
        Bukkit.getPluginManager().registerEvents(new FakePearl(), this);
        Bukkit.getPluginManager().registerEvents(new FakeLogger(), this);
        Bukkit.getPluginManager().registerEvents(new GrapplingHook(), this);
        Bukkit.getPluginManager().registerEvents(new GuardianAngel(), this);
        Bukkit.getPluginManager().registerEvents(new Harpoon(), this);
        Bukkit.getPluginManager().registerEvents(new LuckyIngot(), this);
        Bukkit.getPluginManager().registerEvents(new ImpulseBomb(), this);
        Bukkit.getPluginManager().registerEvents(new Mixer(), this);
        Bukkit.getPluginManager().registerEvents(new PrePearl(), this);
        Bukkit.getPluginManager().registerEvents(new PocketBard(), this);
        Bukkit.getPluginManager().registerEvents(new PocketBards(), this);
        Bukkit.getPluginManager().registerEvents(new PrePearl(), this);
        Bukkit.getPluginManager().registerEvents(new PotionCounter(), this);
        Bukkit.getPluginManager().registerEvents(new PotionInheritor(), this);
        Bukkit.getPluginManager().registerEvents(new PortableBackstab(), this);
        Bukkit.getPluginManager().registerEvents(new PoisonDart(), this);
        Bukkit.getPluginManager().registerEvents(new RageAbility(), this);
        Bukkit.getPluginManager().registerEvents(new RottenEgg(), this);
        Bukkit.getPluginManager().registerEvents(new RageBrick(), this);
        Bukkit.getPluginManager().registerEvents(new Refund(), this);
        Bukkit.getPluginManager().registerEvents(new Rocket(), this);
        Bukkit.getPluginManager().registerEvents(new SwitchStick(), this);
        Bukkit.getPluginManager().registerEvents(new SecondChance(), this);
        Bukkit.getPluginManager().registerEvents(new SwapperAxe(), this);
        Bukkit.getPluginManager().registerEvents(new Switcher(), this);
        Bukkit.getPluginManager().registerEvents(new TankIngot(), this);
        Bukkit.getPluginManager().registerEvents(new TeleportBow(), this);
        Bukkit.getPluginManager().registerEvents(new WebGun(), this);
        Bukkit.getPluginManager().registerEvents(new WitherGun(), this);
        Bukkit.getPluginManager().registerEvents(new NinjaStar(), this);


        //GUIS

        Bukkit.getPluginManager().registerEvents(new PocketBardGUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new GiveGUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new AbilityResetGUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new JavaUtils(), this);


    }
}
