package de.mrpixeldream.crystalminers.rushmode;

import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RushMode extends JavaPlugin {
	
	static RushMode instance;
	Vector<RushSign> rushSigns = new Vector<>();
	Vector<RushSign> waitingForLocation = new Vector<>();
	Vector<RushSign> waitingForPlayerCount = new Vector<>();
	
	@Override
	public void onEnable()
	{
		RushMode.instance = this;
		
		RushSignRightClickListener rightClickListener = new RushSignRightClickListener();
		RushSignCreateListener createListener = new RushSignCreateListener();
		
		Bukkit.getPluginManager().registerEvents(rightClickListener, this);
		Bukkit.getPluginManager().registerEvents(createListener, this);
	}
	
	@Override
	public void onDisable()
	{
		
	}

}
