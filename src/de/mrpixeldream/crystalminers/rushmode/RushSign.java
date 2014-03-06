package de.mrpixeldream.crystalminers.rushmode;

import org.bukkit.Location;

public class RushSign {
	
	Location targetSign;
	
	int playerCount;
	int maxPlayer;
	
	Location targetSpawn;
	
	public RushSign(Location targetSign)
	{
		this.targetSign = targetSign;
	}
}
