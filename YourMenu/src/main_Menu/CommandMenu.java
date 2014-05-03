package main_Menu;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandMenu implements CommandExecutor{
	
	private main plugin;
	
	private Inventory inv=null;
	private int slot = 0;
	private int aviabledslots = 36;
	private HashMap<Integer, ItemStack> is = new HashMap<Integer, ItemStack>();
	Integer price = 0;
	public CommandMenu(main plugin) {
		this.plugin = plugin;
	}
 
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player p = (Player) sender;
		

		boolean allow = false;
		
		for(int x = 0; x < plugin.getConfig().getStringList("menu.worlds").size() ;x++)
		{
			if(plugin.getConfig().getStringList("menu.worlds").get(x).equalsIgnoreCase(p.getWorld().getName()))
			{
				allow = true;
			}
		}
		
		if(allow)
		{
		if(p.hasPermission("menu.open")||p.hasPermission("menu.user"))
		{
		if(args.length == 0)
		{
			String menuname = plugin.getConfig().getString("menu.name");
			menuname = menuname.replaceAll("&", "§");
			
			menuname = menuname.replaceAll("<p>", p.getName());
			menuname = menuname.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
			menuname = menuname.replaceAll("<ip>", plugin.getServer().getIp());
			menuname = menuname.replaceAll("<motd>", plugin.getServer().getMotd());
			menuname = menuname.replaceAll("<version>", plugin.getServer().getBukkitVersion());
			menuname = menuname.replaceAll("<servername>", plugin.getServer().getServerName());
			menuname = menuname.replaceAll("<world>", p.getWorld().getName());
			menuname = menuname.replaceAll("<level>", "" + p.getLevel());
			aviabledslots = plugin.getConfig().getInt("menu.slots");
			inv = p.getPlayer().getServer().createInventory(null, aviabledslots, menuname);
			is.clear();
			slot = 0;
			for(int x = 0;x < aviabledslots;x++)
			{
				
				
				slot = x+1;
				if((plugin.getConfig().getBoolean("menu.slot." + slot + ".enabled")))
				{
				
					ItemStack istack = new  ItemStack(plugin.getConfig().getInt("menu.slot." + slot + ".id"));
					ItemMeta istackMeta = istack.getItemMeta();
					String desc = plugin.getConfig().getString("menu.slot." + slot + ".name");
					desc = desc.replaceAll("&", "§");
					
					desc = desc.replaceAll("<p>", p.getName());
					desc = desc.replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length);
					desc = desc.replaceAll("<ip>", plugin.getServer().getIp());
					desc = desc.replaceAll("<motd>", plugin.getServer().getMotd());
					desc = desc.replaceAll("<version>", plugin.getServer().getBukkitVersion());
					desc = desc.replaceAll("<servername>", plugin.getServer().getServerName());
					desc = desc.replaceAll("<world>", p.getWorld().getName());
					desc = desc.replaceAll("<level>", "" + p.getLevel());
					istackMeta.setDisplayName(desc);
					List<String> desca = (List<String>) plugin.getConfig().getStringList("menu.slot." + slot + ".lore");
					for(int a = 0; a < desca.size(); a++)
					{
						desca.set(a, desca.get(a).replaceAll("&", "§"));
						desca.set(a, desca.get(a).replaceAll("<p>", p.getName()));
						desca.set(a, desca.get(a).replaceAll("<online>", "" + plugin.getServer().getOnlinePlayers().length));
						desca.set(a, desca.get(a).replaceAll("<ip>", plugin.getServer().getIp()));
						desca.set(a, desca.get(a).replaceAll("<motd>", plugin.getServer().getMotd()));
						desca.set(a, desca.get(a).replaceAll("<version>", plugin.getServer().getBukkitVersion()));
						desca.set(a, desca.get(a).replaceAll("<servername>", plugin.getServer().getServerName()));
						desca.set(a, desca.get(a).replaceAll("<world>", p.getWorld().getName()));
						desca.set(a, desca.get(a).replaceAll("<level>", "" + p.getLevel()));
					}
					
					
					istackMeta.setLore(desca);
					istack.setItemMeta(istackMeta);
					is.put(slot -1, istack);
					
					
					String nix;
					
					for(int y = 0;y < plugin.getConfig().getStringList("menu.slot." + slot + ".enchantment").size();y++)
					{
					
						nix = plugin.getConfig().getStringList("menu.slot." + slot + ".enchantment").get(y);
						nix = nix.replaceAll(" ", "");
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
					
					
				}
				else
				{
					ItemStack istack = new  ItemStack(Material.AIR);
					is.put(slot -1, istack);
				}
				
			
				
			}

			
			
			
			

			for(int x = 0;x < aviabledslots;x++)    
			{
				inv.setItem(x, is.get(x));
			}
			

			
			p.getPlayer().openInventory(inv);
			return true;
		}
		p.sendMessage("You do not have the permissons to use this command!");
		return true;
		}
		}else
		{
			p.sendMessage("§4In this world, the menu is disabled!");  
			return true;
		}
		return false;
	}

}
