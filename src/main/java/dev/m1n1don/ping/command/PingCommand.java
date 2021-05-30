package dev.m1n1don.ping.command;

import dev.m1n1don.ping.Ping;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length >= 1)
        {
            if (args[0].equalsIgnoreCase("reload"))
            {
                if (Ping.getPlugin().getConfiguration().getBoolean("opOnly.reload") && !sender.isOp())
                {
                    sender.sendMessage(Ping.getPlugin().getPrefix() + Ping.getPlugin().getErrorMessage("permission"));
                    return true;
                }

                Ping.getPlugin().getCustomConfig().save();
                Ping.getPlugin().getCustomConfig().reload();
                sender.sendMessage(Ping.getPlugin().getPrefix() + ChatColor.GOLD + "コンフィグのリロードが完了しました。");
                return true;
            }

            if (Ping.getPlugin().getConfiguration().getBoolean("opOnly.target") && !sender.isOp())
            {
                sender.sendMessage(Ping.getPlugin().getPrefix() + Ping.getPlugin().getErrorMessage("permission"));
                return true;
            }

            if (!Bukkit.getPlayer(args[0]).isOnline())
            {
                sender.sendMessage(Ping.getPlugin().getPrefix() + ChatColor.RED + "そのプレイヤーはオフラインです。");
                return true;
            }

            if (Bukkit.getPlayer(args[0]) == null)
            {
                sender.sendMessage(Ping.getPlugin().getPrefix() + ChatColor.RED + "そのプレイヤーは存在しません。");
                return true;
            }

            Player p = Bukkit.getPlayer(args[0]);

            sender.sendMessage(Ping.getPlugin().getPrefix() + Ping.getPlugin().getTargetMessage().replaceAll("%TARGET%", p.getName()).replaceAll("%PING%", String.valueOf(Ping.getPlugin().getAPI().getPing(p))));
        }
        else
        {
            if (sender instanceof Player)
            {
                if (Ping.getPlugin().getConfiguration().getBoolean("opOnly.sender") && !sender.isOp())
                {
                    sender.sendMessage(Ping.getPlugin().getPrefix() + Ping.getPlugin().getErrorMessage("permission"));
                    return true;
                }

                sender.sendMessage(Ping.getPlugin().getPrefix() + Ping.getPlugin().getSenderMessage().replaceAll("%PING%", String.valueOf(Ping.getPlugin().getAPI().getPing(((Player) sender).getPlayer()))));
            }
            else sender.sendMessage(Ping.getPlugin().getPrefix() + Ping.getPlugin().getErrorMessage("console"));
        }
        return true;
    }
}