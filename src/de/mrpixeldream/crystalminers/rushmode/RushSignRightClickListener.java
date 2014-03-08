package de.mrpixeldream.crystalminers.rushmode;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RushSignRightClickListener implements Listener {
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent evt)
	{
		if (evt.getAction() == Action.RIGHT_CLICK_BLOCK && evt.getClickedBlock().getType() == Material.WALL_SIGN)
		{
			System.out.println("Auswahl: " + RushMode.instance.rushSigns.size());
			for (RushSign elem : RushMode.instance.rushSigns)
			{
				System.out.println("Durchsuche Schilderdatenbank");
				if (evt.getClickedBlock().getLocation().equals(elem.targetSign.getLocation()))
				{
					if (elem.playerCount < elem.maxPlayer)
					{
						evt.getPlayer().teleport(elem.targetSpawn);
						elem.playerCount++;
						((Sign) elem.sign).setLine(1, "§4[Rush]");
						((Sign) elem.sign).setLine(1, "Players: " + elem.playerCount + "/" + elem.maxPlayer);
						elem.sign.update(true);
					}
					else
					{
						evt.getPlayer().sendMessage(ChatColor.RED + "Diese Lobby ist voll!");
					}
					break;
				}
				System.out.println("Das Schild wurde nicht in der Datenbank gefunden");
			}
		}
	}

}
