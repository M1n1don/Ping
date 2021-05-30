package dev.m1n1don.ping;

import dev.m1n1don.ping.api.AbstractRepository;
import dev.m1n1don.ping.command.PingCommand;
import dev.m1n1don.ping.util.CustomConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Ping extends JavaPlugin
{
    private static Ping plugin;

    private AbstractRepository repository;
    private CustomConfig config;

    @Override
    public void onEnable()
    {
        plugin = this;

        repository = new AbstractRepository();

        config = new CustomConfig(this);
        config.saveDefault();

        getCommand("ping").setExecutor(new PingCommand());
    }

    public static Ping getPlugin()
    {
        return plugin;
    }

    public AbstractRepository getAPI()
    {
        return repository;
    }

    public CustomConfig getCustomConfig()
    {
        return config;
    }

    public FileConfiguration getConfiguration()
    {
        return config.getConfiguration();
    }

    public String getPrefix()
    {
        return getConfiguration().getString("prefix") + " ";
    }

    public String getSenderMessage()
    {
        return getConfiguration().getString("sender_message");
    }

    public String getTargetMessage()
    {
        return getConfiguration().getString("target_message");
    }

    public String getErrorMessage(final String error)
    {
        return getConfiguration().getString("error_messages." + error);
    }
}
