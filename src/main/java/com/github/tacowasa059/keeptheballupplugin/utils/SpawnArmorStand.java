package com.github.tacowasa059.keeptheballupplugin.utils;

import com.github.tacowasa059.keeptheballupplugin.KeepTheBallUpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class SpawnArmorStand {
    public static void spawn(){
        Location location=KeepTheBallUpPlugin.plugin.getRef_coordinate().clone();
        Random random=new Random();
        double radius=Math.sqrt(random.nextDouble())*KeepTheBallUpPlugin.plugin.getConfig().getDouble("R_max");
        double theta=random.nextDouble()*2.0*Math.PI;
        double x_add=radius*Math.cos(theta);
        double z_add=radius*Math.sin(theta);
        location.add(x_add,2.0,z_add);

        Entity entity=KeepTheBallUpPlugin.plugin.getRef_coordinate().getWorld().spawnEntity(location,KeepTheBallUpPlugin.plugin.entityType);
        entity.setCustomName("ball");
        entity.setCustomNameVisible(false);
        entity.setGravity(false);
        entity.setGlowing(true);
        if(entity instanceof ArmorStand){
            ArmorStand armorStand = (ArmorStand)entity;
            armorStand.setBasePlate(false); // ベースプレートを非表示にする
            armorStand.setArms(true); // アームを表示する
            armorStand.setInvisible(true);
            armorStand.setHelmet(new ItemStack(Material.SNOW_BLOCK,1));
            armorStand.setDisabledSlots(EquipmentSlot.HAND);
            KeepTheBallUpPlugin.plugin.armorStandslist.put(armorStand,0);
        }
        else{
            if(entity instanceof LivingEntity)((LivingEntity) entity).setAI(false);
            KeepTheBallUpPlugin.plugin.armorStandslist.put(entity,0);
        }

    }
}
