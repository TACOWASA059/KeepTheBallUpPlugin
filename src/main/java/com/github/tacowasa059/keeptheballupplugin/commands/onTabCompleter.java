package com.github.tacowasa059.keeptheballupplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class onTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("spawn");
            completions.add("setlocation");
            completions.add("setR_max");
            completions.add("setMultiply");
            completions.add("info");
            completions.add("showConfig");
            completions.add("setPlayerOnly");
        }
        return completions;
    }
}
