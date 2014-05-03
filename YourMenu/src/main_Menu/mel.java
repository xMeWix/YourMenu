 package main_Menu;


import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class mel implements Listener{
	private Inventory inv=null;
	private int slot = 0;
	private HashMap<Player, Boolean> isedit = new HashMap<Player, Boolean>();
	String work = "";
	public mel(main plugin)
	{
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	@EventHandler
	public void onShop(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		
		try
		{
			if(!isedit.get(p))
			{
				
			}
		}catch(NullPointerException ex)
		{
			isedit.put(p, false);
		}
		
		if(!isedit.get(p))
		{
		
		String aname = plugin.getConfig().getString("menu.name");
		
		aname = aname.replaceAll("<p>", p.getName());
		aname = aname.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
		aname = aname.replaceAll("<ip>", plugin.getServer().getIp());
		aname = aname.replaceAll("<motd>", plugin.getServer().getMotd());
		aname = aname.replaceAll("<version>", plugin.getServer().getBukkitVersion());
		aname = aname.replaceAll("<servername>", plugin.getServer().getServerName());
		aname = aname.replaceAll("<world>", p.getWorld().getName());
		aname = aname.replaceAll("<level>", "" + p.getLevel());
		aname = aname.replaceAll("&", "§");
		
		if(e.getInventory().getName().equalsIgnoreCase(aname))
		{
			slot = 0;
			for(int x = 0; x < 36; x++)
			{
				
				slot = x+1;
				
				work = "";
				String thename = plugin.getConfig().getString("menu.slot." + slot + ".name");
				
				thename = thename.replaceAll("<p>", p.getName());
				thename = thename.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
				thename = thename.replaceAll("<ip>", plugin.getServer().getIp());
				thename = thename.replaceAll("<motd>", plugin.getServer().getMotd());
				thename = thename.replaceAll("<version>", plugin.getServer().getBukkitVersion());
				thename = thename.replaceAll("<servername>", plugin.getServer().getServerName());
				thename = thename.replaceAll("<world>", p.getWorld().getName());
				thename = thename.replaceAll("<level>", "" + p.getLevel());
				thename = thename.replace("&", "§");  //siehe oben

				
				if(e.getCurrentItem().getTypeId() == plugin.getConfig().getInt("menu.slot." + slot + ".id") && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(thename))   //Vorsicht mit den Deprecated Methoden wie getTypeId()...
				{
					if(p.hasPermission("menu.slot." + slot)||p.hasPermission("menu.slot.every")||p.hasPermission("menu.admin"))
					{
					for(int w = 0;w < plugin.getConfig().getStringList("menu.slot." + slot + ".commands").size();w++){
					
					work = plugin.getConfig().getStringList("menu.slot." + slot + ".commands").get(w);
					if(work.indexOf("messageplayer") != -1)
					{
						work = work.replaceAll("messageplayer ", "");
						work = work.replaceAll("<p>", p.getName());
						work = work.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
						work = work.replaceAll("<ip>", plugin.getServer().getIp());
						work = work.replaceAll("<motd>", plugin.getServer().getMotd());
						work = work.replaceAll("<version>", plugin.getServer().getBukkitVersion());
						work = work.replaceAll("<servername>", plugin.getServer().getServerName());
						work = work.replaceAll("<world>", p.getWorld().getName());
						work = work.replaceAll("<level>", "" + p.getLevel());
						work = work.replaceAll("&", "§");  //siehe oben
						p.sendMessage(work);
						
						
					}
					else
					{
						if(work.indexOf("playercommand") != -1)
						{
							work = work.replaceAll("playercommand ", "");
							work = work.replaceAll("<p>", p.getName());
							work = work.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
							work = work.replaceAll("<ip>", plugin.getServer().getIp());
							work = work.replaceAll("<motd>", plugin.getServer().getMotd());
							work = work.replaceAll("<version>", plugin.getServer().getBukkitVersion());
							work = work.replaceAll("<servername>", plugin.getServer().getServerName());
							work = work.replaceAll("<world>", p.getWorld().getName());
							work = work.replaceAll("<level>", "" + p.getLevel());
							work = work.replace("/", "");
							work = work.replaceAll("&", "§");  //siehe oben
							p.performCommand(work);
							
							
						}
						else
						{
							if(work.indexOf("consolecommand") != -1)
							{
								work = work.replaceAll("consolecommand ", "");
								work = work.replaceAll("<p>", p.getName());
								work = work.replace("/", "");
								work = work.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
								work = work.replaceAll("<ip>", plugin.getServer().getIp());
								work = work.replaceAll("<motd>", plugin.getServer().getMotd());
								work = work.replaceAll("<version>", plugin.getServer().getBukkitVersion());
								work = work.replaceAll("<servername>", plugin.getServer().getServerName());
								work = work.replaceAll("<world>", p.getWorld().getName());
								work = work.replaceAll("<level>", "" + p.getLevel());
								work = work.replaceAll("&", "§");  //siehe oben
								plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), work);
								
								
							}
							else
							{
								if(work.indexOf("broadcast") != -1)
								{
									work = work.replaceAll("broadcast ", "");
									work = work.replaceAll("<p>", p.getName());
									work = work.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
									work = work.replaceAll("<ip>", plugin.getServer().getIp());
									work = work.replaceAll("<motd>", plugin.getServer().getMotd());
									work = work.replaceAll("<version>", plugin.getServer().getBukkitVersion());
									work = work.replaceAll("<servername>", plugin.getServer().getServerName());
									work = work.replaceAll("<world>", p.getWorld().getName());
									work = work.replaceAll("<level>", "" + p.getLevel());
									work = work.replaceAll("&", "§");  //siehe oben
									plugin.getServer().broadcastMessage(work);
									
									
								}
							}
						}
					}
					e.getView().close();
					}
					}else{
				
				p.sendMessage("§4You do not have the permisson to use this slot!");
			}
				}
			
			}
			e.getView().close();
		}
	}else
	{
		if(p.hasPermission("menu.work.edit"))
		{
		return;
		}
		else
		{
			e.getView().close();
		}
	}
		
	
	//hier
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e)
	{
		if(e.getPlayer() instanceof Player)
		{
		Player p = (Player) e.getPlayer();
		String aname = plugin.getConfig().getString("menu.name");
		
		aname = aname.replaceAll("<p>", p.getName());
		aname = aname.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
		aname = aname.replaceAll("<ip>", plugin.getServer().getIp());
		aname = aname.replaceAll("<motd>", plugin.getServer().getMotd());
		aname = aname.replaceAll("<version>", plugin.getServer().getBukkitVersion());
		aname = aname.replaceAll("<servername>", plugin.getServer().getServerName());
		aname = aname.replaceAll("<world>", p.getWorld().getName());
		aname = aname.replaceAll("<level>", "" + p.getLevel());
		aname = aname.replaceAll("&", "§");
		
		if(e.getInventory().getName().equalsIgnoreCase(aname))
		{
			try
			{
			if(isedit.get(p))
			{
			for(int x = 0; x < plugin.getConfig().getInt("menu.slots");x++)
			{
				slot = x +1;
				try{
					plugin.getConfig().set("menu.slot." + slot + ".enabled", true);
					plugin.getConfig().set("menu.slot." + slot + ".id", e.getInventory().getItem(x).getTypeId());
				
				}catch(NullPointerException ex)
				{
					plugin.getConfig().set("menu.slot." + slot + ".enabled", false);
				}

					
				
				
				plugin.saveConfig();
			}
			isedit.put(p, false);
			}
		}catch(NullPointerException ex)
		{
			return;
		}
		}
		}
		
	}
	
	@EventHandler
	public void onMenu(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(p.getItemInHand().getTypeId() == plugin.getConfig().getInt("menu.item.id"))
			{
			if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("menu.item.name")))
	    	{
				if(p.hasPermission("menu.open")||p.hasPermission("menu.user"))
				{
					p.performCommand("menu");
					return;
					
				}
	    	}
		}
		}
		if(e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(p.getItemInHand().getTypeId() == 340)
			{
			if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("EditItem"))
	    	{
				if(p.hasPermission("menu.work.edit")||p.hasPermission("menu.admin"))
				{
					isedit.put(p, true);
					p.performCommand("menu");
				}
	    	}
		}
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e)
	{
		ItemStack item = e.getItemDrop().getItemStack();
		if(item.getTypeId() == plugin.getConfig().getInt("menu.item.id"))
		{
		String name = plugin.getConfig().getString("menu.item.name");
		name = name.replaceAll("&", "§");  //siehe oben
		name = name.replaceAll("<p>", e.getPlayer().getName());
		name = name.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
		name = name.replaceAll("<ip>", plugin.getServer().getIp());
		name = name.replaceAll("<motd>", plugin.getServer().getMotd());
		name = name.replaceAll("<version>", plugin.getServer().getBukkitVersion());
		name = name.replaceAll("<servername>", plugin.getServer().getServerName());
		name = name.replaceAll("<world>", e.getPlayer().getWorld().getName());
		name = name.replaceAll("<level>", "" + e.getPlayer().getLevel());
		if(name.equalsIgnoreCase(item.getItemMeta().getDisplayName()))
		{
			if(e.getPlayer().hasPermission("menu.dropitem")||e.getPlayer().hasPermission("menu.admin"))
			{
				return;
			}
			
			e.getPlayer().sendMessage("§4You can't drop the menu item!");
			e.setCancelled(true);
			return;
		}
		}
		
	}

	
	
	@EventHandler
	public void onMoveItem(InventoryClickEvent e)
	{
		ItemStack item = e.getCurrentItem();
		if(item.getTypeId() == plugin.getConfig().getInt("menu.item.id"))
		{
		if(e.getWhoClicked() instanceof Player)
		{
		Player p = (Player) e.getWhoClicked();
		String name = plugin.getConfig().getString("menu.item.name");
		name = name.replaceAll("&", "§");  //siehe oben
		name = name.replaceAll("<p>", p.getName());
		name = name.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
		name = name.replaceAll("<ip>", plugin.getServer().getIp());
		name = name.replaceAll("<motd>", plugin.getServer().getMotd());
		name = name.replaceAll("<version>", plugin.getServer().getBukkitVersion());
		name = name.replaceAll("<servername>", plugin.getServer().getServerName());
		name = name.replaceAll("<world>", p.getWorld().getName());
		name = name.replaceAll("<level>", "" + p.getLevel());
		if(name.equalsIgnoreCase(item.getItemMeta().getDisplayName()))
		{
			if(p.hasPermission("menu.moveitem")||p.hasPermission("menu.admin"))
			{
				return;
			}
			p.sendMessage("§4You can't move the menu item!");
			e.setCancelled(true);
			e.setCursor(new ItemStack(Material.AIR));
			e.getWhoClicked().closeInventory();
			
			return;
		}
		}
		}
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		if(e.getPlayer().hasPermission("menu.item")||e.getPlayer().hasPermission("menu.user"))
		{
		boolean allow = false;
		
		for(int x = 0; x < plugin.getConfig().getStringList("menu.worlds").size() ;x++)
		{
			if(plugin.getConfig().getStringList("menu.worlds").get(x).equalsIgnoreCase(e.getPlayer().getWorld().getName()))
			{
				allow = true;
			}
		}
		if(allow)
		{
		
		ItemStack istack = new  ItemStack(plugin.getConfig().getInt("menu.item.id"));
		ItemMeta istackMeta = istack.getItemMeta();
		String desc = plugin.getConfig().getString("menu.item.name");
		desc = desc.replaceAll("&", "§");  //siehe oben
		
		desc = desc.replaceAll("<p>", e.getPlayer().getName());
		desc = desc.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
		desc = desc.replaceAll("<ip>", plugin.getServer().getIp());
		desc = desc.replaceAll("<motd>", plugin.getServer().getMotd());
		desc = desc.replaceAll("<version>", plugin.getServer().getBukkitVersion());
		desc = desc.replaceAll("<servername>", plugin.getServer().getServerName());
		desc = desc.replaceAll("<world>", e.getPlayer().getWorld().getName());
		desc = desc.replaceAll("<level>", "" + e.getPlayer().getLevel());
		istackMeta.setDisplayName(desc);
		List<String> desca = (List<String>) plugin.getConfig().getStringList("menu.item.lore");
		for(int a = 0; a < desca.size(); a++)
		{
			desca.set(a, desca.get(a).replaceAll("&", "§"));  //siehe oben
			
			
			desca.set(a, desca.get(a).replaceAll("<p>", e.getPlayer().getName()));
			desca.set(a, desca.get(a).replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length));
			desca.set(a, desca.get(a).replaceAll("<ip>", plugin.getServer().getIp()));
			desca.set(a, desca.get(a).replaceAll("<motd>", plugin.getServer().getMotd()));
			desca.set(a, desca.get(a).replaceAll("<version>", plugin.getServer().getBukkitVersion()));
			desca.set(a, desca.get(a).replaceAll("<servername>", plugin.getServer().getServerName()));
			desca.set(a, desca.get(a).replaceAll("<world>", e.getPlayer().getWorld().getName()));
			desca.set(a, desca.get(a).replaceAll("<level>", "" + e.getPlayer().getLevel()));
		}
		istackMeta.setLore(desca);
		istack.setItemMeta(istackMeta);
		
		String nix;
		for(int y = 0;y < plugin.getConfig().getStringList("menu.item.enchantment").size();y++)
		{
		
		nix = plugin.getConfig().getStringList("menu.item.enchantment").get(y);
		
		if(nix.equalsIgnoreCase("ARROW_DAMAGE")) 
		{
			istack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		}
		if(nix.equalsIgnoreCase("ARROW_FIRE"))
		{
			istack.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
		}
		if(nix.equalsIgnoreCase("ARROW_INFINITE"))
		{
			istack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		}
		if(nix.equalsIgnoreCase("ARROW_KNOCKBACK"))
		{
			istack.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		}
		if(nix.equalsIgnoreCase("DAMAGE_ALL"))
		{
			istack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		}
		if(nix.equalsIgnoreCase("DAMAGE_ARTHROPODS"))
		{
			istack.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 1);
		}
		if(nix.equalsIgnoreCase("DAMAGE_UNDEAD"))
		{
			istack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		}
		if(nix.equalsIgnoreCase("DIG_SPEED"))
		{
			istack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
		}
		if(nix.equalsIgnoreCase("DURABILITY"))
		{
			istack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		if(nix.equalsIgnoreCase("FIRE_ASPECT"))
		{
			istack.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		}
		if(nix.equalsIgnoreCase("KNOCKBACK"))
		{
			istack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
		}
		if(nix.equalsIgnoreCase("LOOT_BONUS_BLOCKS"))
		{
			istack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);
		}
		if(nix.equalsIgnoreCase("LOOT_BONUS_MOBS"))
		{
			istack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
		}
		if(nix.equalsIgnoreCase("LUCK"))
		{
			istack.addUnsafeEnchantment(Enchantment.LUCK, 1);
		}
		if(nix.equalsIgnoreCase("LURE"))
		{
			istack.addUnsafeEnchantment(Enchantment.LURE, 1);
		}
		if(nix.equalsIgnoreCase("OXYGEN"))
		{
			istack.addUnsafeEnchantment(Enchantment.OXYGEN, 1);
		}
		if(nix.equalsIgnoreCase("PROTECTION_ENVIRONMENTAL"))
		{
			istack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		}
		if(nix.equalsIgnoreCase("PROTECTION_EXPLOSIONS"))
		{
			istack.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);
		}
		if(nix.equalsIgnoreCase("PROTECTION_FALL"))
		{
			istack.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 1);
		}
		if(nix.equalsIgnoreCase("PROTECTION_FIRE"))
		{
			istack.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
		}
		if(nix.equalsIgnoreCase("PROTECTION_PROJECTILE"))
		{
			istack.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		}
		if(nix.equalsIgnoreCase("SILK_TOUCH"))
		{
			istack.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		}
		if(nix.equalsIgnoreCase("THORNS"))
		{
			istack.addUnsafeEnchantment(Enchantment.THORNS, 1);
		}
		if(nix.equalsIgnoreCase("WATER_WORKER"))
		{
			istack.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
		}
		
	}
		try
		{
		if(e.getPlayer().getInventory().getItem(plugin.getConfig().getInt("menu.item.slot")-1).getType() == istack.getType()&&e.getPlayer().getInventory().getItem(plugin.getConfig().getInt("menu.item.slot")-1).getItemMeta().getDisplayName().equalsIgnoreCase(istack.getItemMeta().getDisplayName()))
		{

		}
		else
		{
			ItemStack stack = e.getPlayer().getInventory().getItem(plugin.getConfig().getInt("menu.item.slot")-1);
			e.getPlayer().getInventory().setItem(plugin.getConfig().getInt("menu.item.slot")-1, istack);
			e.getPlayer().getInventory().addItem(stack);
		}
		}catch(NullPointerException ex)
		{
			e.getPlayer().getInventory().setItem(plugin.getConfig().getInt("menu.item.slot")-1, istack);
		}
		}
	}
	}
	
	@EventHandler
	public void onBuild(BlockPlaceEvent e)
	{
		if(e.getPlayer().getItemInHand().getTypeId() == plugin.getConfig().getInt("menu.item.id")&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("menu.item.name")))
		{
			e.setCancelled(true);
		}
	}
	
	
	
	private main plugin;
	
}
