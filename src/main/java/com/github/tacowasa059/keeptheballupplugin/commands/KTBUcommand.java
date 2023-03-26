package com.github.tacowasa059.keeptheballupplugin.commands;

import com.github.tacowasa059.keeptheballupplugin.KeepTheBallUpPlugin;
import com.github.tacowasa059.keeptheballupplugin.utils.SpawnArmorStand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class KTBUcommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player=(Player) sender;
            if(!player.isOp()){
                player.sendMessage(ChatColor.RED+"このコマンドの実行にはOP権限が必要です");
                return true;
            }
            if(args.length==1&&args[0].equalsIgnoreCase("info")){
                player.sendMessage(ChatColor.AQUA+"-----------------");
                player.sendMessage(ChatColor.AQUA+"/ktbu spawn : "+ChatColor.GREEN+"基準座標にアーマースタンドを1体だけ出現させる");
                player.sendMessage(ChatColor.AQUA+"/ktbu spawn "+ChatColor.GRAY+"<num>"+" : "+ChatColor.GREEN+"基準座標にアーマースタンドを"+ChatColor.GRAY+"<num>"+ChatColor.GREEN+"体だけ出現させる");
                player.sendMessage(ChatColor.AQUA+"/ktbu setlocation : " + ChatColor.GREEN+"アーマースタンドを召喚する基準座標の設定");
                player.sendMessage(ChatColor.AQUA+"/ktbu setR_max "+ChatColor.GRAY+"<value>"+ChatColor.AQUA+" : " +ChatColor.GREEN+"スポーン範囲の最大半径を"+ChatColor.GRAY+"<value>"+ChatColor.GREEN+"に設定する.");
                player.sendMessage(ChatColor.AQUA+"/ktbu setMultiply "+ChatColor.GRAY+"<value>"+ChatColor.AQUA+" : " +ChatColor.GREEN+"打ち出し時の初速を"+ChatColor.GRAY+"<value>"+ChatColor.GREEN+"に設定する.");
                player.sendMessage(ChatColor.AQUA+"/ktbu setPlayerOnly" +ChatColor.GRAY +"<true/false>"+ChatColor.AQUA+" : "+ChatColor.GREEN+"アーマースタンドを飛ばせるのをプレイヤー限定にするかどうか。");
                player.sendMessage(ChatColor.AQUA+"-----------------");
            }
            else if(args.length==1&& args[0].equalsIgnoreCase("showConfig")){
                player.sendMessage(ChatColor.AQUA+"-----------------");
                player.sendMessage(ChatColor.AQUA+"player_only"+ChatColor.GREEN+" = "+ChatColor.DARK_AQUA+KeepTheBallUpPlugin.plugin.getConfig().getBoolean("player_only")+ChatColor.GREEN+" : アーマースタンドを飛ばせるのをプレイヤーに限定(投射物を含めない)");
                player.sendMessage(ChatColor.AQUA+"R_max"+ChatColor.GREEN+" = "+ChatColor.DARK_AQUA+KeepTheBallUpPlugin.plugin.getConfig().getDouble("R_max")+ ChatColor.GREEN+" : 最大半径");
                player.sendMessage(ChatColor.AQUA+"multiply"+ChatColor.GREEN+" = "+ChatColor.DARK_AQUA+KeepTheBallUpPlugin.plugin.getConfig().getDouble("multiply")+ ChatColor.GREEN+" : 初速");
                player.sendMessage(ChatColor.AQUA+"ref_coordinate"+ChatColor.GREEN+" = "+ChatColor.DARK_AQUA+String.format("%.2f",KeepTheBallUpPlugin.plugin.getConfig().getLocation("ref_coordinate").getX())+" "+String.format("%.2f",KeepTheBallUpPlugin.plugin.getConfig().getLocation("ref_coordinate").getY())+" "+String.format("%.2f",KeepTheBallUpPlugin.plugin.getConfig().getLocation("ref_coordinate").getZ())+ ChatColor.GREEN+" : 基準座標");
                player.sendMessage(ChatColor.AQUA+"-----------------");
            }
            else if(args.length==1&&args[0].equalsIgnoreCase("spawn")){
                spawn(player,1);
            }
            else if(args.length==2&&args[0].equalsIgnoreCase("spawn")){
                Integer num=1;
                try{
                    num=Integer.parseInt(args[1]);
                }catch (NumberFormatException e){
                    player.sendMessage(ChatColor.RED+"個数の指定が間違っています。");
                }

                spawn(player,num);
            }
            else if(args.length==1&&args[0].equalsIgnoreCase("setlocation")){
                KeepTheBallUpPlugin.plugin.setRef_coordinate(player);
                player.sendMessage(ChatColor.GREEN+"基準座標が"+ChatColor.AQUA+KeepTheBallUpPlugin.plugin.getRef_coordinate()+ChatColor.GREEN+"に設定されました。");
            }
            else if(args.length==2&&args[0].equalsIgnoreCase("setR_max")){
                Double d=KeepTheBallUpPlugin.plugin.getConfig().getDouble("R_max");
                try{
                    d=Double.parseDouble(args[1]);
                    player.sendMessage(ChatColor.GREEN+"最大半径 R_maxが"+d+"に変更されました。");
                }catch (NumberFormatException e){
                    player.sendMessage(ChatColor.RED+"数値で指定してください。");
                }
                KeepTheBallUpPlugin.plugin.getConfig().set("R_max",d);
            }
            else if(args.length==2&&args[0].equalsIgnoreCase("setMultiply")){
                Double d=KeepTheBallUpPlugin.plugin.getConfig().getDouble("multiply");
                try{
                    d=Double.parseDouble(args[1]);
                    player.sendMessage(ChatColor.GREEN+"初速の大きさmultiplyが"+d+"に変更されました。");
                }catch (NumberFormatException e){
                    player.sendMessage(ChatColor.RED+"数値で指定してください。");
                }
                KeepTheBallUpPlugin.plugin.getConfig().set("multiply",d);
            }
            else if(args.length==2&&args[0].equalsIgnoreCase("setPlayerOnly")){
                Boolean flag=KeepTheBallUpPlugin.plugin.getConfig().getBoolean("player_only");
                try{
                    flag=Boolean.parseBoolean(args[1]);
                    player.sendMessage(ChatColor.GREEN+"設定を"+ChatColor.AQUA+flag+ChatColor.GREEN+"に変更しました");
                }catch(NumberFormatException e){
                    player.sendMessage(ChatColor.RED+"true/falseで指定してください。");
                }
                KeepTheBallUpPlugin.plugin.getConfig().set("player_only",flag);

            }
            else{
                player.sendMessage(ChatColor.RED+"コマンドを間違えています。");
            }
        }

        return true;
    }
    private void spawn(Player player,Integer num){
        if(KeepTheBallUpPlugin.plugin.getRef_coordinate()!=null){
            for(int i=0;i<num;i++) SpawnArmorStand.spawn();
            player.sendMessage(ChatColor.GREEN+"防具縦が召喚されました");
        }
        else{
            player.sendMessage(ChatColor.RED+"先に基準座標を設定してください");
            player.sendMessage(ChatColor.RED+"/ktbu setlocation");
        }
    }
}
