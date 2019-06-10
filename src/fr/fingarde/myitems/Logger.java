package fr.fingarde.myitems;

import org.bukkit.Bukkit;

public class Logger
{
    public static void printError(String error)
    {
        Bukkit.getConsoleSender().sendMessage(error);
    }
}
