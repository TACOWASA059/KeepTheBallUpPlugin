package com.github.tacowasa059.keeptheballupplugin;

import com.github.tacowasa059.keeptheballupplugin.commands.KTBUcommand;
import com.github.tacowasa059.keeptheballupplugin.commands.onTabCompleter;
import com.github.tacowasa059.keeptheballupplugin.listeners.ArmorstandClickListener;
import com.github.tacowasa059.keeptheballupplugin.utils.IsGround;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class KeepTheBallUpPlugin extends JavaPlugin {
    public static KeepTheBallUpPlugin plugin;

    public HashMap<Entity,Integer> armorStandslist=new HashMap<>();

    private static Location ref_coordinate;
    private IsGround isGround;

    public EntityType entityType=EntityType.ARMOR_STAND;
    @Override
    public void onEnable() {
        plugin=this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ArmorstandClickListener(),this);
        getCommand("KTBU").setExecutor(new KTBUcommand());
        getCommand("KTBU").setTabCompleter(new onTabCompleter());

        isGround=new IsGround();
        isGround.set_schedule();
        this.setRef_coordinate();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        isGround.remove_schedule();
    }
    public Location getRef_coordinate() {
        return ref_coordinate;
    }

    public void setRef_coordinate(Player player) {
        this.ref_coordinate=player.getLocation();
        getConfig().set("ref_coordinate",this.getRef_coordinate());

        saveConfig();
    }
    public void setRef_coordinate(){
        Location location=getConfig().getLocation("ref_coordinate");
        if(location!=null){
            this.ref_coordinate=location;
        }
    }
}
