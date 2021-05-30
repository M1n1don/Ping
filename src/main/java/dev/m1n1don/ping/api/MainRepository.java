package dev.m1n1don.ping.api;

import org.bukkit.entity.Player;

interface MainRepository
{
    int getPing(Player player);
}