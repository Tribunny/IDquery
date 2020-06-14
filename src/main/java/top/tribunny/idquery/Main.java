package top.tribunny.idquery;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Tribunny
 */
public class Main extends JavaPlugin implements CommandExecutor {

//    public FileConfiguration id;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[IDQuery]V1.0");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Author:Tribunny");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Email:trirabbits@163.com");
        getCommand("idquery").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if( cmd.getName().equals("idquery")){
            if( (sender instanceof  Player)){
                Player p = (Player)sender;
                String info = p.getInventory().getItemInMainHand().toString();
                String[] item = info.split("\\s+");
                item[0] = item[0].replace("ItemStack{","");
                String json = "{\"clickEvent\": { \"action\": \"suggest_command\", \"value\": \"" + item[0] + "\" }, \"text\": \"[IDQuery]§a点击复制名称到输入框\" }";
                p.sendMessage("§e========================");
                this.getServer().dispatchCommand(this.getServer().getConsoleSender(),"tellraw " + p.getName() + " " + json);
                p.sendMessage("§e========================");
                return true;
            }
            return true;
        }
        return false;
    }
}
