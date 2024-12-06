package me.mchiappinam.pdghlobbybussola;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener  {
	
	private Main plugin;
	public Listeners(Main main) {
		plugin=main;
	}
	
	@EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.LEFT_CLICK_AIR)||e.getAction().equals(Action.LEFT_CLICK_BLOCK)||e.getAction().equals(Action.RIGHT_CLICK_AIR)||e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(e.getPlayer().getItemInHand().getType() == Material.COMPASS){
                e.setCancelled(true);
                plugin.tags();
                e.getPlayer().openInventory(Main.menu);
            }
        }
	}
	
	@EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
		e.setCancelled(true);
		e.getPlayer().sendMessage(" ");
		e.getPlayer().sendMessage(ChatColor.RED+"➩Escolha um servidor para jogar!");
		e.getPlayer().sendMessage(" ");
    }
	
	@EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
		e.getItemDrop().remove();
		e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setItem(2, plugin.cerca());
        e.getPlayer().getInventory().setItem(3, plugin.book());
        e.getPlayer().getInventory().setItem(4, plugin.cerca());
	    e.getPlayer().getInventory().setItem(5, plugin.bussola());
        e.getPlayer().getInventory().setItem(6, plugin.cerca());
    }
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
		e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setItem(2, plugin.cerca());
        e.getPlayer().getInventory().setItem(3, plugin.book());
        e.getPlayer().getInventory().setItem(4, plugin.cerca());
	    e.getPlayer().getInventory().setItem(5, plugin.bussola());
        e.getPlayer().getInventory().setItem(6, plugin.cerca());
    }

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
    	e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
	    ItemStack clicked = e.getCurrentItem();
	    Inventory inventory = e.getInventory();
	    if (inventory.getName().equals(Main.menu.getName())) {
	    	e.setCancelled(true);
	    	if ((e.getCurrentItem() != null)&&(!e.getCurrentItem().getType().equals(Material.AIR))) {
		    	if (clicked.getType() == Material.DIAMOND_BLOCK) { //Creativo
			    	p.closeInventory();
			    	plugin.sendCreativo(p);
				    return;
			    }else if (clicked.getType() == Material.IRON_AXE) { //DayZ2
			    	p.closeInventory();
			    	plugin.sendDayZ2(p);
				    return;
			    }else if (clicked.getType() == Material.DIAMOND_AXE) { //DayZ3
			    	p.closeInventory();
			    	p.sendMessage(" ");
			    	p.sendMessage("§b§lServidor apenas para Minecraft original!");
			    	p.sendMessage("§b§lIP do servidor: §a§ldayz3.pdgh.com.br");
			    	p.sendMessage(" ");
				    return;
			    }else if (clicked.getType() == Material.FIRE) { //DayZHardcore
			    	p.closeInventory();
			    	plugin.sendDayZHardcore(p);
				    return;
			    }else if (clicked.getType() == Material.DIAMOND_SWORD) { //Full PvP
			    	p.closeInventory();
			    	plugin.sendFullPvP(p);
				    return;
			    }else if (clicked.getType() == Material.MUSHROOM_SOUP) { //Hunger Games1
			    	p.closeInventory();
			    	plugin.sendHungerGames1(p);
				    return;
			    }else if (clicked.getType() == Material.BOWL) { //Hunger Games2
			    	p.closeInventory();
			    	plugin.sendHungerGames2(p);
				    return;
			    }else if (clicked.getType() == Material.DIAMOND_PICKAXE) { //PvP
			    	p.closeInventory();
			    	plugin.sendPvP(p);
				    return;
			    }else if (clicked.getType() == Material.PORTAL) { //AEDF
			    	p.closeInventory();
			    	plugin.sendAEDF(p);
				    return;
			    }
	    	}
		    return;
	    }
	}
	
}