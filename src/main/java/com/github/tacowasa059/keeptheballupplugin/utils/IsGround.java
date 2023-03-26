package com.github.tacowasa059.keeptheballupplugin.utils;

import com.github.tacowasa059.keeptheballupplugin.KeepTheBallUpPlugin;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ConcurrentModificationException;

public class IsGround {
    private static Integer taskID;
    private static Boolean status=false;
    public void  set_schedule(){
        if(status)return;//既に実行中の時
        status=true;
        taskID= Bukkit.getScheduler().scheduleSyncRepeatingTask(KeepTheBallUpPlugin.plugin, new Runnable() {
            @Override
            public void run() {
                try{
                    judge();
                }catch (ConcurrentModificationException e){
                    judge();
                }
            }
        },0L,8L);
    }
    public void remove_schedule(){
        if(status) Bukkit.getScheduler().cancelTask(taskID);
        status=false;
    }
    public void judge(){
            for(Entity armorStand:KeepTheBallUpPlugin.plugin.armorStandslist.keySet()){
                if(armorStand.isOnGround()){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(KeepTheBallUpPlugin.plugin,()->{
                            if(armorStand.isOnGround()){
                                Location location=armorStand.getLocation();
                                location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,location,1);
                                location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE,5,1);
                                KeepTheBallUpPlugin.plugin.getServer().broadcastMessage(ChatColor.DARK_AQUA+""+KeepTheBallUpPlugin.plugin.armorStandslist.get(armorStand)+ChatColor.GREEN+"回続きました。");
                                KeepTheBallUpPlugin.plugin.armorStandslist.remove(armorStand);
                                String name=getNearestPlayer(location).getName();
                                Bukkit.broadcastMessage(ChatColor.GREEN+"落下地点から一番近いプレイヤーは"+ChatColor.AQUA+name+ChatColor.GREEN+"です。");
                                armorStand.remove();
                            }
                    },6L);

                }
            }
    }
    public Player getNearestPlayer(Location location) {
        World world = location.getWorld();
        Player nearestPlayer = null;
        double nearestDistance = Double.MAX_VALUE;

        for(Player player : world.getPlayers()) {
            Location playerLocation = player.getLocation();
            double distance = location.distance(playerLocation);

            if(distance < nearestDistance) {
                nearestDistance = distance;
                nearestPlayer = player;
            }
        }

        return nearestPlayer;
    }
}
