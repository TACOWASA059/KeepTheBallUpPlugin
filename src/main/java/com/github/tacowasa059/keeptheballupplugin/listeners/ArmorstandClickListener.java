package com.github.tacowasa059.keeptheballupplugin.listeners;

import com.github.tacowasa059.keeptheballupplugin.KeepTheBallUpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class ArmorstandClickListener implements Listener {

    @EventHandler
    public void onArmorStandDamage(EntityDamageByEntityEvent event) {
            Entity entity=event.getEntity();
                if(KeepTheBallUpPlugin.plugin.armorStandslist.containsKey(entity)){
                    Entity entity1 = event.getDamager();
                    event.setCancelled(true);
                    Vector velocity;
                    if(entity1 instanceof Player) velocity=entity1.getLocation().getDirection().clone();
                    else{
                        if(KeepTheBallUpPlugin.plugin.getConfig().getBoolean("player_only")){
                            return;
                        }
                        velocity=entity1.getVelocity();
                    }
                    entity.setGravity(true);
                    velocity.normalize();
                    velocity.multiply(KeepTheBallUpPlugin.plugin.getConfig().getDouble("multiply"));

                    if(entity.getVelocity().getY()<=0.0) KeepTheBallUpPlugin.plugin.armorStandslist.put(entity,KeepTheBallUpPlugin.plugin.armorStandslist.get(entity)+1);
                    entity.setVelocity(velocity);
                }
    }
}