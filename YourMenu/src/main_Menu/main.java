package main_Menu;



import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
public class main extends JavaPlugin{
	
	//String Config;
	String lang = "";
	private CommandMenu CommandEx;
	@Override
	public void onEnable()
	{
		loadConfig();
		registerEvent();
		//CommandExecutor
		CommandEx = new CommandMenu(this);
		getCommand("menu").setExecutor(CommandEx);
		/*Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run()
			{
				
			}
			
		}, 1, 5);*/
		System.out.println("YourMenu enabled!");
		
	    

		
		
		
		
	}
	@Override
	public void onDisable()
	{
		System.out.println("YourMenu disabled!");
	}
	
	public boolean onCommand(CommandSender sender,Command cmd, String cmdLabel, String[] args)
	{
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("ym"))
		{
			if(p.hasPermission("menu.work.menuitem"))
			{
				if(args.length == 1)
				{
					if(args[0].equalsIgnoreCase("edititem"))
					{

						ItemStack istack = new ItemStack(Material.BOOK);
						ItemMeta istackMeta = istack.getItemMeta();
						istackMeta.setDisplayName("EditItem");
						istack.setItemMeta(istackMeta);
						p.getInventory().addItem(istack);
						p.sendMessage("§2You get a menu edit item!");
						return true;
					}
				}
			}
			if(p.hasPermission("menu.work")||p.hasPermission("menu.admin"))
			{
				if(p.hasPermission("menu.work.reload")||p.hasPermission("menu.admin"))
				{
					if(args.length == 0)
					{
						p.sendMessage("§4Error with arguments");
						return true;
					}
					if(args.length == 1)
					{
					if(args[0].equalsIgnoreCase("reload"))
					{
						this.reloadConfig();
						p.sendMessage("§4Config reloaded!");
						return true;
					}
					}
				}
				
				if(p.hasPermission("menu.work.title")||p.hasPermission("menu.admin"))
				{
				if(args.length > 1)
				{
				if(args[0].equalsIgnoreCase("title"))
				{
					lang = "";
					for(int i = 1; i < args.length; i++)
					{
						this.lang = lang + args[i] + " ";
					}
					this.getConfig().set("menu.name", lang);
					this.saveConfig();
					p.sendMessage("§4Editing saved!");
					return true;
				}
				
				}
				if(args.length == 2)
				{
					if(p.hasPermission("menu.setlines"))
					{
						if(args[0].equalsIgnoreCase("setlines"))
						{
							if(Integer.parseInt(args[1]) == 1)
							{
							this.getConfig().set("menu.slots", 9);
							this.saveConfig();
							p.sendMessage("§4Editing saved!");
							return true;
							}
							if(Integer.parseInt(args[1]) == 2)
							{
							this.getConfig().set("menu.slots", 18);
							this.saveConfig();
							p.sendMessage("§4Editing saved!");
							return true;
							}
							if(Integer.parseInt(args[1]) == 3)
							{
							this.getConfig().set("menu.slots", 27);
							this.saveConfig();
							p.sendMessage("§4Editing saved!");
							return true;
							}
							if(Integer.parseInt(args[1]) == 4)
							{
							this.getConfig().set("menu.slots", 36);
							this.saveConfig();
							p.sendMessage("§4Editing saved!");
							return true;
							}
							
							
								p.sendMessage("§4You can only set so many menu lines: §61/2/3/4");
								return true;
							
						}
					}
					if(p.hasPermission("menu.worlds")||p.hasPermission("menu.admin"))
					{
					if(args[0].equalsIgnoreCase("addworld"))
					{
						List<String> worlds = (List<String>) this.getConfig().getStringList("menu.worlds");
						for(int x = 0;x < worlds.size();x++)
						{
							if(worlds.get(x).equalsIgnoreCase(args[1]))
							{
								p.sendMessage("§4This world has already been added!");  
								return true;
								
							}
						}
						
						worlds.add(args[1]);
						this.getConfig().set("menu.worlds", worlds);
						this.saveConfig();
						p.sendMessage("§2The world §6" + args[1] + " §2was added!");	
						return true;
					}
					if(args[0].equalsIgnoreCase("removeworld"))
					{
						List<String> worlds = (List<String>) this.getConfig().getStringList("menu.worlds");
						for(int x = 0;x < worlds.size();x++)
						{
							if(worlds.get(x).equalsIgnoreCase(args[1]))
							{
								worlds.remove(x);
								this.getConfig().set("menu.worlds", worlds);
								this.saveConfig();
								p.sendMessage("§2The world §6" + args[1] + " §2was removed!");	
								return true;
								
							}
						}
						
						p.sendMessage("§4This world is not in the list!");	
						return true;
					}
				}
				}
				if(args.length == 1)
				{
					if(p.hasPermission("menu.worlds")||p.hasPermission("menu.admin"))
					{
					if(args[0].equalsIgnoreCase("worlds"))
					{
						p.sendMessage("§2---Worlds---");
						List<String> worlds = (List<String>) this.getConfig().getStringList("menu.worlds");
						for(int x = 0;x < worlds.size();x++)
						{
							p.sendMessage("§6" + worlds.get(x));
						}
						
						p.sendMessage("§2---Worlds---");
						return true;
					}
					}
				}
			}
				
				if(p.hasPermission("menu.help")||p.hasPermission("menu.admin"))
				{
				if(args.length == 1)
				{
				if(args[0].equalsIgnoreCase("help"))
				{
					p.sendMessage("§2---Commands---");
					p.sendMessage("§2<v> = <value>");
					p.sendMessage("§2<s> = <slot>");
					p.sendMessage("§4/ym reload <v>");
					p.sendMessage("§4/ym edititem");
					p.sendMessage("§4/ym title <v>");
					p.sendMessage("§4/ym setlines <v>");
					p.sendMessage("§4/ym worlds");
					p.sendMessage("§4/ym addworld <v>");
					p.sendMessage("§4/ym removeworld <v>");
					p.sendMessage("§4/ym edit item id <v>");
					p.sendMessage("§4/ym edit item name <v>");
					p.sendMessage("§4/ym edit item lore");
					p.sendMessage("§4/ym edit item addlore <v>");
					p.sendMessage("§4/ym edit item removelore <v>");
					p.sendMessage("§4/ym edit item slot <v>");
					p.sendMessage("§4/ym edit slot <s> id <v>");
					p.sendMessage("§4/ym edit slot <s> enabled <v>");
					p.sendMessage("§4/ym edit slot <s> name <v>");
					p.sendMessage("§4/ym edit slot <s> lore");
					p.sendMessage("§4/ym edit slot <s> addlore <v>");
					p.sendMessage("§4/ym edit slot <s> removelore <v>");
					p.sendMessage("§4/ym edit slot <s> commands <v>");
					p.sendMessage("§4/ym edit slot <s> addcommand <v>");
					p.sendMessage("§4/ym edit slot <s> removecommand <v>");
					p.sendMessage("§4/ym edit slot <s> enchantments");
					p.sendMessage("§4/ym edit slot <s> addenchantment <v>");
					p.sendMessage("§4/ym edit slot <s> removeenchantment <v>");
					p.sendMessage("§2---Commands---");
					return true;
				}
				}
			}
				
				if(p.hasPermission("menu.work.edit")||p.hasPermission("menu.admin"))
				{
					if(args.length > 0)
					{
					if(args[0].equalsIgnoreCase("edit"))
					{
						if(args.length >= 3)
						{
						if(args[1].equalsIgnoreCase("item"))
						{
						
							if(args.length == 4)
							{
							if(args[2].equalsIgnoreCase("id"))
							{
								this.getConfig().set("menu.item.id", Integer.parseInt(args[3]));
								this.saveConfig();
								p.sendMessage("§4Editing saved!");
								return true;
							}
							}
							if(args.length >= 4)
							{
							if(args[2].equalsIgnoreCase("name"))
							{
								lang = "";
								for(int i = 3; i < args.length; i++)
								{
									this.lang = lang + args[i] + " ";
								}
								this.getConfig().set("menu.item.name", lang);
								this.saveConfig();
								p.sendMessage("§4Editing saved!");
								return true;

							}
							
						
							if(args[2].equalsIgnoreCase("addlore"))
							{
								lang = "";
								for(int i = 3; i < args.length; i++)
								{
									this.lang = lang + args[i] + " ";
								}
								List<String> lore = (List<String>) this.getConfig().getStringList("menu.item.lore");
								lore.add(lang);
								this.getConfig().set("menu.item.lore", lore);
								this.saveConfig();
								p.sendMessage("§4Editing saved!");
								return true;
							}
							
							if(args[2].equalsIgnoreCase("addenchantment"))
							{
								lang = "";
								for(int i = 3; i < args.length; i++)
								{
									this.lang = lang + args[i] + " ";
								}
								List<String> lore = (List<String>) this.getConfig().getStringList("menu.item.enchantment");
								lore.add(lang);
								this.getConfig().set("menu.item.enchantment", lore);
								this.saveConfig();
								p.sendMessage("§4Editing saved!");
								return true;
							}
							

						}
							if(args.length == 3)
							{
							if(args[2].equalsIgnoreCase("lore"))
							{

									p.sendMessage("§2---Lore---");
									List<String> lore = (List<String>) this.getConfig().getStringList("menu.item.lore");
									for(int x = 0;x < lore.size();x++)
									{
										p.sendMessage("§7[§4" + x + "§7] " + "§6" + lore.get(x));
									}
									
									p.sendMessage("§2---Lore---");
									return true;
								
							}
							if(args[2].equalsIgnoreCase("enchantments"))
							{

									p.sendMessage("§2---Enchantment---");
									List<String> lore = (List<String>) this.getConfig().getStringList("menu.item.enchantment");
									for(int x = 0;x < lore.size();x++)
									{
										p.sendMessage("§7[§4" + x + "§7] " + "§6" + lore.get(x));
									}
									
									p.sendMessage("§2---Enchantment---");
									return true;
								
							}
							}
							if(args.length == 4)
							{
							if(args[2].equalsIgnoreCase("slot"))
							{
								this.getConfig().set("menu.item.slot", Integer.parseInt(args[3]));
								this.saveConfig();
								
								p.sendMessage("§4Editing saved!");
								return true;
							}
							
							if(args[2].equalsIgnoreCase("removelore"))
							{
								
								try
								{

										List<String> lore = (List<String>) this.getConfig().getStringList("menu.item.lore");
										if(lore.size() >= Integer.parseInt(args[3]))
										{
											lore.remove(Integer.parseInt(args[3]));
											this.getConfig().set("menu.item.lore", lore);
											this.saveConfig();
											p.sendMessage("§4Editing saved!");
											return true;
										}
										
										p.sendMessage("§7There's not the line §6" + Integer.parseInt(args[3]) + "§7!");
										return true;
								}catch(NumberFormatException ex)
								{
									p.sendMessage("§4You have to use a number and §6" + args[3] + "§4 is not a number");
									return true;
								}
								
							}
							if(args[2].equalsIgnoreCase("removeenchantment"))
							{
								
								try
								{

										List<String> lore = (List<String>) this.getConfig().getStringList("menu.item.enchantment");
										if(lore.size() >= Integer.parseInt(args[3]))
										{
											lore.remove(Integer.parseInt(args[3]));
											this.getConfig().set("menu.item.enchantment", lore);
											this.saveConfig();
											p.sendMessage("§4Editing saved!");
											return true;
										}
										
										p.sendMessage("§7There's not the line §6" + Integer.parseInt(args[3]) + "§7!");
										return true;
								}catch(NumberFormatException ex)
								{
									p.sendMessage("§4You have to use a number and §6" + args[3] + "§4 is not a number");
									return true;
								}
								
							}
							}
						}
						}

						if(args[1].equalsIgnoreCase("slot"))
						{
							try
							{
							if(Integer.parseInt(args[2]) < 37&&Integer.parseInt(args[2]) > 0) 
							{
								if(args.length == 5)
								{
							if(args[3].equalsIgnoreCase("id"))
							{
								this.getConfig().set("menu.slot." + args[2] + ".id", Integer.parseInt(args[4]));
								this.saveConfig();
								p.sendMessage("§4Editing saved!");
								return true;
							}
							if(args[3].equalsIgnoreCase("enabled"))
							{
								this.getConfig().set("menu.slot." + args[2] + ".enabled", Boolean.parseBoolean(args[4]));
								this.saveConfig();
								p.sendMessage("§4Editing saved!");
								return true;
							}
							if(args[3].equalsIgnoreCase("removecommand"))
							{
								try{
									List<String> commands = (List<String>) this.getConfig().getStringList("menu.slot." + args[2] + ".commands");
									if(commands.size() >= Integer.parseInt(args[4]))
									{
										commands.remove(Integer.parseInt(args[4]));
										this.getConfig().set("menu.slot." + args[2] + ".commands", commands);
										this.saveConfig();
										p.sendMessage("§4Editing saved!");
										return true;
									}
									
									p.sendMessage("§7There's not the line §6" + Integer.parseInt(args[4]) + "§7!");
									return true;
								
							}catch(NumberFormatException ex)
							{
								p.sendMessage("§4You have to use a number and §6" + args[4] + "§4 is not a number");
								return true;
							}
							}
							if(args[3].equalsIgnoreCase("removelore"))
							{

								try{
										List<String> lore = (List<String>) this.getConfig().getStringList("menu.slot." + args[2] + ".lore");
										if(lore.size() >= Integer.parseInt(args[4]))
										{
											lore.remove(Integer.parseInt(args[4]));
											this.getConfig().set("menu.slot." + args[2] + ".lore", lore);
											this.saveConfig();
											p.sendMessage("§4Editing saved!");
											return true;
										}
										
										p.sendMessage("§7There's not the line §6" + Integer.parseInt(args[4]) + "§7!");
										return true;
									
								}catch(NumberFormatException ex)
								{
									p.sendMessage("§4You have to use a number and §6" + args[4] + "§4 is not a number");
									return true;
								}
							}
							if(args[3].equalsIgnoreCase("removeenchantment"))
							{

								try{
										List<String> ench = (List<String>) this.getConfig().getStringList("menu.slot." + args[2] + ".enchantment");
										if(ench.size() >= Integer.parseInt(args[4]))
										{
											ench.remove(Integer.parseInt(args[4]));
											this.getConfig().set("menu.slot." + args[2] + ".enchantment", ench);
											this.saveConfig();
											p.sendMessage("§4Editing saved!");
											return true;
										}
										
										p.sendMessage("§7There's not the line §6" + Integer.parseInt(args[4]) + "§7!");
										return true;
									
								}catch(NumberFormatException ex)
								{
									p.sendMessage("§4You have to use a number and §6" + args[4] + "§4 is not a number");
									return true;
								}
							}
								}
								if(args.length >= 5)
								{
									
									if(args[3].equalsIgnoreCase("addcommand"))
									{
										lang = "";
										for(int i = 4; i < args.length; i++)
										{
											this.lang = lang + args[i] + " ";
										}
										List<String> commands = (List<String>) this.getConfig().getStringList("menu.slot." + args[2] + ".commands");
										commands.add(lang);
										this.getConfig().set("menu.slot." + args[2] + ".commands", commands);
										this.saveConfig();
										p.sendMessage("§4Editing saved!");
										return true;
									}
							if(args[3].equalsIgnoreCase("name"))
							{
								lang = "";
								for(int i = 4; i < args.length; i++)
								{
									this.lang = lang + args[i] + " ";
								}
								this.getConfig().set("menu.slot." + args[2] + ".name", lang);
								this.saveConfig();
								p.sendMessage("§4Editing saved!");
								return true;

							}
							if(args[3].equalsIgnoreCase("addlore"))
							{
								lang = "";
								for(int i = 4; i < args.length; i++)
								{
									this.lang = lang + args[i] + " ";
								}
								List<String> lore = (List<String>) this.getConfig().getStringList("menu.slot." + args[2] + ".lore");
								lore.add(lang);
								this.getConfig().set("menu.slot." + args[2] + ".lore", lore);
								this.saveConfig();
								p.sendMessage("§4Editing saved!");
								return true;
							}
							if(args[3].equalsIgnoreCase("addenchantment"))
							{
								lang = "";
								for(int i = 4; i < args.length; i++)
								{
									this.lang = lang + args[i] + " ";
								}
								List<String> ench = (List<String>) this.getConfig().getStringList("menu.slot." + args[2] + ".enchantment");
								ench.add(lang);
								this.getConfig().set("menu.slot." + args[2] + ".enchantment", ench);
								this.saveConfig();
								p.sendMessage("§4Editing saved!");
								return true;
							}
							}
							if(args.length == 4)
							{
								
								if(args[3].equalsIgnoreCase("commands"))
								{
									p.sendMessage("§2---Commands---");
									
									List<String> commands = (List<String>) this.getConfig().getStringList("menu.slot." + args[2] + ".commands");
									for(int x = 0;x < commands.size();x++)
									{
										p.sendMessage("§7[§4" + x + "§7] " + "§6" + commands.get(x));
									}
									p.sendMessage("§2---Commands---");
									return true;
								}
							if(args[3].equalsIgnoreCase("lore"))
							{

									p.sendMessage("§2---Lore---");
									List<String> lore = (List<String>) this.getConfig().getStringList("menu.slot." + args[2] + ".lore");
									for(int x = 0;x < lore.size();x++)
									{
										p.sendMessage("§7[§4" + x + "§7] " + "§6" + lore.get(x));
									}
									
									p.sendMessage("§2---Lore---");
									return true;
								
							}
							if(args[3].equalsIgnoreCase("enchantments"))
							{

									p.sendMessage("§2---Enchantment---");
									List<String> ench = (List<String>) this.getConfig().getStringList("menu.slot." + args[2] + ".enchantment");
									for(int x = 0;x < ench.size();x++)
									{
										p.sendMessage("§7[§4" + x + "§7] " + "§6" + ench.get(x));
									}
									
									p.sendMessage("§2---Enchantment---");
									return true;
								
							}
							}
							p.sendMessage("§4Error with arguments");
						}
						}catch(NumberFormatException exe)
						{
							p.sendMessage("§4You have to use a slot number!");
							return true;
						}
					}
						}
						else
						{
							p.sendMessage("§4Error with arguments");
						}
					}
			}
				}
			
			return true;
			}
		
		
		if(cmd.getName().equalsIgnoreCase("menuitem"))
		{
			if(p.hasPermission("menu.item")||p.hasPermission("menu.user"))
			{
				boolean allow = false;
				
				for(int x = 0; x < this.getConfig().getStringList("menu.worlds").size() ;x++)
				{
					if(this.getConfig().getStringList("menu.worlds").get(x).equalsIgnoreCase(p.getWorld().getName()))
					{
						allow = true;
					}
				}
				ItemStack istack = new  ItemStack(this.getConfig().getInt("menu.item.id"));
				ItemMeta istackMeta = istack.getItemMeta();
				String desc = this.getConfig().getString("menu.item.name");
				desc = desc.replaceAll("&", "§");
				
	
				desc = desc.replaceAll("<p>", p.getName());
				desc = desc.replaceAll("<online>", "" + this.getServer().getOnlinePlayers().length);
				desc = desc.replaceAll("<ip>", this.getServer().getIp());
				desc = desc.replaceAll("<motd>", this.getServer().getMotd());
				desc = desc.replaceAll("<version>", this.getServer().getBukkitVersion());
				desc = desc.replaceAll("<servername>", this.getServer().getServerName());
				desc = desc.replaceAll("<world>", p.getWorld().getName());
				desc = desc.replaceAll("<level>", "" + p.getLevel());
				
				istackMeta.setDisplayName(desc);
				List<String> desca = (List<String>) this.getConfig().getStringList("menu.item.lore");
				for(int a = 0; a < desca.size(); a++)
				{
					desca.set(a, desca.get(a).replaceAll("&0", "§0"));
					
					desca.set(a, desca.get(a).replaceAll("<p>", p.getName()));
					desca.set(a, desca.get(a).replaceAll("<online>", "" + this.getServer().getOnlinePlayers().length));
					desca.set(a, desca.get(a).replaceAll("<ip>", this.getServer().getIp()));
					desca.set(a, desca.get(a).replaceAll("<motd>", this.getServer().getMotd()));
					desca.set(a, desca.get(a).replaceAll("<version>", this.getServer().getBukkitVersion()));
					desca.set(a, desca.get(a).replaceAll("<servername>", this.getServer().getServerName()));
					desca.set(a, desca.get(a).replaceAll("<world>", p.getWorld().getName()));
					desca.set(a, desca.get(a).replaceAll("<level>", "" + p.getLevel()));
				}
				istackMeta.setLore(desca);
				istack.setItemMeta(istackMeta);
				
				
				String nix;
				for(int y = 0;y < this.getConfig().getStringList("menu.item.enchantment").size();y++)
				{
				
				nix = this.getConfig().getStringList("menu.item.enchantment").get(y);
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
				
			
				try	{
					if(allow) {
						int d = this.getConfig().getInt("");
						if(p.getInventory().getItem(this.getConfig().getInt("menu.item.slot")-1).getType() == istack.getType()&&p.getInventory().getItem(this.getConfig().getInt("menu.item.slot")-1).getItemMeta().getDisplayName().equalsIgnoreCase(istack.getItemMeta().getDisplayName())) {
							p.sendMessage("You have already a menu item!");  
							return true;
						}
						else {
							ItemStack stack = p.getInventory().getItem(this.getConfig().getInt("menu.item.slot")-1);
							p.getInventory().setItem(this.getConfig().getInt("menu.item.slot")-1, istack);
							p.getInventory().addItem(stack);
							p.sendMessage("You received a menu item!"); 
						}
					}
					else {
						p.sendMessage("§4In this world, the menu is disabled!");  
						return true;
					}
				}
				catch(NullPointerException e) {
					p.getInventory().setItem(this.getConfig().getInt("menu.item.slot")-1, istack);
					p.sendMessage("You received a menu item!");
					return true;
				}
			
			}
		}
		
		return false;
	}

	
	
	
	
	
	
	private void registerEvent()
	{
		MyEventListener = new mel(this);

	}
	private mel MyEventListener = null;

	
	public void loadConfig()
	{
		List<String> l = new ArrayList<String>();
		List<String> worlds = new ArrayList<String>();
		List<String> ench = new ArrayList<String>();
		List<String> commands = new ArrayList<String>();
		commands.add("playercommand /help");
		ench.add("no");
		worlds.add("world");
		worlds.add("city");
		l.add("§1This is lore line 1");
		
		
		this.getConfig().options().copyDefaults(true);
		
		this.getConfig().addDefault("menu.name", "&4Menu for &6 <p>");
		this.getConfig().addDefault("menu.worlds", worlds);
		this.getConfig().addDefault("menu.slots", 36);
		this.getConfig().addDefault("menu.item.id", 340);
		this.getConfig().addDefault("menu.item.name", "Menu");
		this.getConfig().addDefault("menu.item.lore", l);
		this.getConfig().addDefault("menu.item.enchantment", ench);
		this.getConfig().addDefault("menu.item.slot", 1);
		
		this.getConfig().addDefault("menu.slot.1.enabled", true);
		this.getConfig().addDefault("menu.slot.1.id", 4);
		this.getConfig().addDefault("menu.slot.1.name", "1");
		this.getConfig().addDefault("menu.slot.1.lore", l);
		this.getConfig().addDefault("menu.slot.1.commands", commands);
		this.getConfig().addDefault("menu.slot.1.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.2.enabled", true);
		this.getConfig().addDefault("menu.slot.2.id", 4);
		this.getConfig().addDefault("menu.slot.2.name", "2");
		this.getConfig().addDefault("menu.slot.2.lore", l);
		this.getConfig().addDefault("menu.slot.2.commands", commands);
		this.getConfig().addDefault("menu.slot.2.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.3.enabled", true);
		this.getConfig().addDefault("menu.slot.3.id", 4);
		this.getConfig().addDefault("menu.slot.3.name", "3");
		this.getConfig().addDefault("menu.slot.3.lore", l);
		this.getConfig().addDefault("menu.slot.3.commands", commands);
		this.getConfig().addDefault("menu.slot.3.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.4.enabled", true);
		this.getConfig().addDefault("menu.slot.4.id", 4);
		this.getConfig().addDefault("menu.slot.4.name", "4");
		this.getConfig().addDefault("menu.slot.4.lore", l);
		this.getConfig().addDefault("menu.slot.4.commands", commands);
		this.getConfig().addDefault("menu.slot.4.enchantment", ench);
		
		
	
		
		this.getConfig().addDefault("menu.slot.5.enabled", true);
		this.getConfig().addDefault("menu.slot.5.id", 4);
		this.getConfig().addDefault("menu.slot.5.name", "5");
		this.getConfig().addDefault("menu.slot.5.lore", l);
		this.getConfig().addDefault("menu.slot.5.commands", commands);
		this.getConfig().addDefault("menu.slot.5.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.6.enabled", true);
		this.getConfig().addDefault("menu.slot.6.id", 4);
		this.getConfig().addDefault("menu.slot.6.name", "6");
		this.getConfig().addDefault("menu.slot.6.lore", l);
		this.getConfig().addDefault("menu.slot.6.commands", commands);
		this.getConfig().addDefault("menu.slot.6.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.7.enabled", true);
		this.getConfig().addDefault("menu.slot.7.id", 4);
		this.getConfig().addDefault("menu.slot.7.name", "7");
		this.getConfig().addDefault("menu.slot.7.lore", l);
		this.getConfig().addDefault("menu.slot.7.commands", commands);
		this.getConfig().addDefault("menu.slot.7.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.8.enabled", true);
		this.getConfig().addDefault("menu.slot.8.id", 4);
		this.getConfig().addDefault("menu.slot.8.name", "8");
		this.getConfig().addDefault("menu.slot.8.lore", l);
		this.getConfig().addDefault("menu.slot.8.commands", commands);
		this.getConfig().addDefault("menu.slot.8.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.9.enabled", true);
		this.getConfig().addDefault("menu.slot.9.id", 4);
		this.getConfig().addDefault("menu.slot.9.name", "9");
		this.getConfig().addDefault("menu.slot.9.lore", l);
		this.getConfig().addDefault("menu.slot.9.commands", commands);
		this.getConfig().addDefault("menu.slot.9.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.10.enabled", true);
		this.getConfig().addDefault("menu.slot.10.id", 4);
		this.getConfig().addDefault("menu.slot.10.name", "10");
		this.getConfig().addDefault("menu.slot.10.lore", l);
		this.getConfig().addDefault("menu.slot.10.commands", commands);
		this.getConfig().addDefault("menu.slot.10.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.11.enabled", true);
		this.getConfig().addDefault("menu.slot.11.id", 4);
		this.getConfig().addDefault("menu.slot.11.name", "11");
		this.getConfig().addDefault("menu.slot.11.lore", l);
		this.getConfig().addDefault("menu.slot.11.commands", commands);
		this.getConfig().addDefault("menu.slot.11.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.12.enabled", true);
		this.getConfig().addDefault("menu.slot.12.id", 4);
		this.getConfig().addDefault("menu.slot.12.name", "12");
		this.getConfig().addDefault("menu.slot.12.lore", l);
		this.getConfig().addDefault("menu.slot.12.commands", commands);
		this.getConfig().addDefault("menu.slot.12.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.13.enabled", true);
		this.getConfig().addDefault("menu.slot.13.id", 4);
		this.getConfig().addDefault("menu.slot.13.name", "13");
		this.getConfig().addDefault("menu.slot.13.lore", l);
		this.getConfig().addDefault("menu.slot.13.commands", commands);
		this.getConfig().addDefault("menu.slot.13.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.14.enabled", true);
		this.getConfig().addDefault("menu.slot.14.id", 4);
		this.getConfig().addDefault("menu.slot.14.name", "14");
		this.getConfig().addDefault("menu.slot.14.lore", l);
		this.getConfig().addDefault("menu.slot.14.commands", commands);
		this.getConfig().addDefault("menu.slot.14.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.15.enabled", true);
		this.getConfig().addDefault("menu.slot.15.id", 4);
		this.getConfig().addDefault("menu.slot.15.name", "15");
		this.getConfig().addDefault("menu.slot.15.lore", l);
		this.getConfig().addDefault("menu.slot.15.commands", commands);
		this.getConfig().addDefault("menu.slot.15.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.16.enabled", true);
		this.getConfig().addDefault("menu.slot.16.id", 4);
		this.getConfig().addDefault("menu.slot.16.name", "16");
		this.getConfig().addDefault("menu.slot.16.lore", l);
		this.getConfig().addDefault("menu.slot.16.commands", commands);
		this.getConfig().addDefault("menu.slot.16.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.17.enabled", true);
		this.getConfig().addDefault("menu.slot.17.id", 4);
		this.getConfig().addDefault("menu.slot.17.name", "17");
		this.getConfig().addDefault("menu.slot.17.lore", l);
		this.getConfig().addDefault("menu.slot.17.commands", commands);
		this.getConfig().addDefault("menu.slot.17.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.18.enabled", true);
		this.getConfig().addDefault("menu.slot.18.id", 4);
		this.getConfig().addDefault("menu.slot.18.name", "18");
		this.getConfig().addDefault("menu.slot.18.lore", l);
		this.getConfig().addDefault("menu.slot.18.commands", commands);
		this.getConfig().addDefault("menu.slot.18.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.19.enabled", true);
		this.getConfig().addDefault("menu.slot.19.id", 4);
		this.getConfig().addDefault("menu.slot.19.name", "19");
		this.getConfig().addDefault("menu.slot.19.lore", l);
		this.getConfig().addDefault("menu.slot.19.commands", commands);
		this.getConfig().addDefault("menu.slot.19.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.20.enabled", true);
		this.getConfig().addDefault("menu.slot.20.id", 4);
		this.getConfig().addDefault("menu.slot.20.name", "20");
		this.getConfig().addDefault("menu.slot.20.lore", l);
		this.getConfig().addDefault("menu.slot.20.commands", commands);
		this.getConfig().addDefault("menu.slot.20.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.21.enabled", true);
		this.getConfig().addDefault("menu.slot.21.id", 4);
		this.getConfig().addDefault("menu.slot.21.name", "21");
		this.getConfig().addDefault("menu.slot.21.lore", l);
		this.getConfig().addDefault("menu.slot.21.commands", commands);
		this.getConfig().addDefault("menu.slot.21.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.22.enabled", true);
		this.getConfig().addDefault("menu.slot.22.id", 4);
		this.getConfig().addDefault("menu.slot.22.name", "22");
		this.getConfig().addDefault("menu.slot.22.lore", l);
		this.getConfig().addDefault("menu.slot.22.commands", commands);
		this.getConfig().addDefault("menu.slot.22.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.23.enabled", true);
		this.getConfig().addDefault("menu.slot.23.id", 4);
		this.getConfig().addDefault("menu.slot.23.name", "23");
		this.getConfig().addDefault("menu.slot.23.lore", l);
		this.getConfig().addDefault("menu.slot.23.commands", commands);
		this.getConfig().addDefault("menu.slot.23.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.24.enabled", true);
		this.getConfig().addDefault("menu.slot.24.id", 4);
		this.getConfig().addDefault("menu.slot.24.name", "24");
		this.getConfig().addDefault("menu.slot.24.lore", l);
		this.getConfig().addDefault("menu.slot.24.commands", commands);
		this.getConfig().addDefault("menu.slot.24.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.25.enabled", true);
		this.getConfig().addDefault("menu.slot.25.id", 4);
		this.getConfig().addDefault("menu.slot.25.name", "25");
		this.getConfig().addDefault("menu.slot.25.lore", l);
		this.getConfig().addDefault("menu.slot.25.commands", commands);
		this.getConfig().addDefault("menu.slot.25.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.26.enabled", true);
		this.getConfig().addDefault("menu.slot.26.id", 4);
		this.getConfig().addDefault("menu.slot.26.name", "26");
		this.getConfig().addDefault("menu.slot.26.lore", l);
		this.getConfig().addDefault("menu.slot.26.commands", commands);
		this.getConfig().addDefault("menu.slot.26.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.27.enabled", true);
		this.getConfig().addDefault("menu.slot.27.id", 4);
		this.getConfig().addDefault("menu.slot.27.name", "27");
		this.getConfig().addDefault("menu.slot.27.lore", l);
		this.getConfig().addDefault("menu.slot.27.commands", commands);
		this.getConfig().addDefault("menu.slot.27.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.28.enabled", true);
		this.getConfig().addDefault("menu.slot.28.id", 4);
		this.getConfig().addDefault("menu.slot.28.name", "28");
		this.getConfig().addDefault("menu.slot.28.lore", l);
		this.getConfig().addDefault("menu.slot.28.commands", commands);
		this.getConfig().addDefault("menu.slot.28.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.29.enabled", true);
		this.getConfig().addDefault("menu.slot.29.id", 4);
		this.getConfig().addDefault("menu.slot.29.name", "29");
		this.getConfig().addDefault("menu.slot.29.lore", l);
		this.getConfig().addDefault("menu.slot.29.commands", commands);
		this.getConfig().addDefault("menu.slot.29.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.30.enabled", true);
		this.getConfig().addDefault("menu.slot.30.id", 4);
		this.getConfig().addDefault("menu.slot.30.name", "30");
		this.getConfig().addDefault("menu.slot.30.lore", l);
		this.getConfig().addDefault("menu.slot.30.commands", commands);
		this.getConfig().addDefault("menu.slot.30.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.31.enabled", true);
		this.getConfig().addDefault("menu.slot.31.id", 4);
		this.getConfig().addDefault("menu.slot.31.name", "31");
		this.getConfig().addDefault("menu.slot.31.lore", l);
		this.getConfig().addDefault("menu.slot.31.commands", commands);
		this.getConfig().addDefault("menu.slot.31.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.32.enabled", true);
		this.getConfig().addDefault("menu.slot.32.id", 4);
		this.getConfig().addDefault("menu.slot.32.name", "32");
		this.getConfig().addDefault("menu.slot.32.lore", l);
		this.getConfig().addDefault("menu.slot.32.commands", commands);
		this.getConfig().addDefault("menu.slot.32.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.33.enabled", true);
		this.getConfig().addDefault("menu.slot.33.id", 4);
		this.getConfig().addDefault("menu.slot.33.name", "33");
		this.getConfig().addDefault("menu.slot.33.lore", l);
		this.getConfig().addDefault("menu.slot.33.commands", commands);
		this.getConfig().addDefault("menu.slot.33.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.34.enabled", true);
		this.getConfig().addDefault("menu.slot.34.id", 4);
		this.getConfig().addDefault("menu.slot.34.name", "34");
		this.getConfig().addDefault("menu.slot.34.lore", l);
		this.getConfig().addDefault("menu.slot.34.commands", commands);
		this.getConfig().addDefault("menu.slot.34.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.35.enabled", true);
		this.getConfig().addDefault("menu.slot.35.id", 4);
		this.getConfig().addDefault("menu.slot.35.name", "35");
		this.getConfig().addDefault("menu.slot.35.lore", l);
		this.getConfig().addDefault("menu.slot.35.commands", commands);
		this.getConfig().addDefault("menu.slot.35.enchantment", ench);
		
		this.getConfig().addDefault("menu.slot.36.enabled", true);
		this.getConfig().addDefault("menu.slot.36.id", 4);
		this.getConfig().addDefault("menu.slot.36.name", "36");
		this.getConfig().addDefault("menu.slot.36.lore", l);
		this.getConfig().addDefault("menu.slot.36.commands", commands);
		this.getConfig().addDefault("menu.slot.36.enchantment", ench);
		
		this.saveConfig();
		
		
	}
	
	
	
	
	
}
