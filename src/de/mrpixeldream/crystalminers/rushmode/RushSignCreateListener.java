package de.mrpixeldream.crystalminers.rushmode;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class RushSignCreateListener implements Listener {
	
	@EventHandler
	public void onSignCreate(SignChangeEvent evt)
	{
		if (evt.getLine(0).equalsIgnoreCase("[Rush]"))
		{
			evt.setLine(0, "§4[Rush]");
			evt.setLine(1, "Players: 0/0");
			
			Block signBlock = evt.getBlock();
			
			RushMode.instance.waitingForLocation.add(new RushSign(signBlock));
			evt.getPlayer().sendMessage(ChatColor.GREEN + "Schild erstellt. Klicke nun mit einer Holzspitzhacke auf den Spawn.");
		}
	}
	
	@EventHandler
	public void onPickaxeRightclick(PlayerInteractEvent evt)
	{
		if (evt.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if (evt.hasItem())
			{
				if (evt.getItem().getType() == Material.WOOD_PICKAXE)
				{
					if (RushMode.instance.waitingForLocation.size() > 0)
					{
						RushSign sign = RushMode.instance.waitingForLocation.get(RushMode.instance.waitingForLocation.size() - 1);
						sign.targetSpawn = evt.getClickedBlock().getLocation();
						RushMode.instance.waitingForLocation.remove(RushMode.instance.waitingForLocation.lastElement());
						RushMode.instance.waitingForPlayerCount.addElement(sign);
						
						evt.getPlayer().sendMessage(ChatColor.GREEN + "Bitte nun die maximale Spieleranzahl im Chat eingeben.");
					}
					else
					{
						evt.getPlayer().sendMessage(ChatColor.RED + "Du musst erst ein Schild erstellen!");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent evt)
	{
		if (RushMode.instance.waitingForPlayerCount.size() > 0)
		{
			try
			{
				int maxPlayers = Integer.parseInt(evt.getMessage());
				evt.setCancelled(true);
				RushSign sign = RushMode.instance.waitingForPlayerCount.lastElement();
				sign.maxPlayer = maxPlayers;
				
				RushMode.instance.waitingForPlayerCount.remove(RushMode.instance.waitingForPlayerCount.lastElement());
				RushMode.instance.rushSigns.addElement(sign);
				
				((Sign) sign.targetSign.getState()).setLine(1, "Players: 0/" + sign.maxPlayer);
				
				evt.getPlayer().sendMessage(ChatColor.GREEN + "Schild erfolgreich erstellt.");
			}
			catch (NumberFormatException ex)
			{
				evt.getPlayer().sendMessage(ChatColor.RED + "Es muss eine ganze Zahl sein! Nochmal bitte.");
			}
		}
	}

}
