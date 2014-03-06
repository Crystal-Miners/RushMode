package de.mrpixeldream.crystalminers.rushmode;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class RushSignCreateListener implements Listener {
	
	@EventHandler
	public void onSignCreate(SignChangeEvent evt)
	{
		if (evt.getLine(0).equalsIgnoreCase("[Rush]"))
		{
			evt.setLine(0, "§4[Rush]");
			evt.setLine(1, "Players: 0/0");
			
			RushMode.instance.rushSigns.add(new RushSign(evt.getBlock().getLocation()));
			System.out.println("schild erstellt");
		}
	}

}
