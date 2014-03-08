package de.mrpixeldream.crystalminers.rushmode;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class RushSignCreateListener implements Listener {
	
	
	BlockState sign;
	RushSign rs;
	@EventHandler
	public void onSignCreate(SignChangeEvent evt)
	{
		if (evt.getLine(0).equalsIgnoreCase("[Rush]"))
		{
			evt.setLine(0, "§4[Rush]");
			evt.setLine(1, "Players: 0/0");
			
			sign = evt.getBlock().getState();
			rs = new RushSign(evt.getBlock());
			RushMode.instance.waitingForLocation.add(new RushSign(evt.getBlock()));
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
						rs.targetSpawn = evt.getClickedBlock().getLocation();
						RushMode.instance.waitingForLocation.remove(RushMode.instance.waitingForLocation.lastElement());
						RushMode.instance.waitingForPlayerCount.addElement(rs);
						
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
				RushMode.instance.waitingForPlayerCount.remove(RushMode.instance.waitingForPlayerCount.lastElement());
				rs.maxPlayer = maxPlayers;
				((Sign) sign).setLine(0, "§4[Rush]");
				((Sign) sign).setLine(1, "Players: 0/" + maxPlayers);
				sign.update(true);
				rs.sign = sign;
				rs.targetSign = sign.getBlock();
				RushMode.instance.rushSigns.addElement(rs);
				
				evt.getPlayer().sendMessage(ChatColor.GREEN + "Schild erfolgreich erstellt.");
			}
			catch (NumberFormatException ex)
			{
				evt.getPlayer().sendMessage(ChatColor.RED + "Es muss eine ganze Zahl sein! Nochmal bitte.");
			}
		}
	}

}
