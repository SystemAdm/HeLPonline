package com.spillhuset.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class TreasureHunterCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("treasure")) {
            if (strings.length == 0) {
//todo
            } else if (strings.length == 1 && strings[0].equalsIgnoreCase("help")) {
//todo
            } else if (strings.length == 1 && strings[0].equalsIgnoreCase("list")) {
//todo
            } else if (strings.length == 1 && strings[0].equalsIgnoreCase("hunt")) {
//todo
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        //todo
        return null;
    }
}
