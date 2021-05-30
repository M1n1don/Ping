package dev.m1n1don.ping.api;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class AbstractRepository implements MainRepository
{
    @Override
    public int getPing(Player player)
    {
        if (player == null) return -1;
        return ((CraftPlayer) player).getHandle().ping;
    }
}