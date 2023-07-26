package me.nixuge.ressourcepackcommand;

import lombok.Getter;
import lombok.Setter;
import me.nixuge.ressourcepackcommand.command.commands.ListPacksCommand;
import me.nixuge.ressourcepackcommand.command.commands.ReloadPacksCommand;
import me.nixuge.ressourcepackcommand.command.commands.RemovePackCommand;
import me.nixuge.ressourcepackcommand.command.commands.AddPackCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(
        modid = McMod.MOD_ID,
        name = McMod.NAME,
        version = McMod.VERSION,
        clientSideOnly = true
)
@Getter
@Setter
public class McMod {
    public static final String MOD_ID = "ressourcepackcommand";
    public static final String NAME = "RessourcePackCommand";
    public static final String VERSION = "1.0.0-1.9.4";

    
    @Getter
    @Mod.Instance(value = McMod.MOD_ID)
    private static McMod instance;

    private Configuration configuration;
    private String configDirectory;

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        this.configDirectory = event.getModConfigurationDirectory().toString();
        final File path = new File(this.configDirectory + File.separator + McMod.MOD_ID + ".cfg");
        this.configuration = new Configuration(path);
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new ListPacksCommand());
        ClientCommandHandler.instance.registerCommand(new AddPackCommand());
        ClientCommandHandler.instance.registerCommand(new RemovePackCommand());
        ClientCommandHandler.instance.registerCommand(new ReloadPacksCommand());
    }
}
