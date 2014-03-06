package de.mrpixeldream.crystalminers.rushmode;

import org.bukkit.Material;
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
			System.out.println("rechtsklick " + RushMode.instance.rushSigns.size());
			for (RushSign elem : RushMode.instance.rushSigns)
			{
				System.out.println("schild durchlauf");
				if (evt.getClickedBlock().getLocation().equals(elem.targetSign))
				{
					System.out.println("schild angeklickt");
					break;
				}
				System.out.println("no match");
			}
		}
	}

}
