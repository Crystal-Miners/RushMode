package de.mrpixeldream.crystalminers.rushmode;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class RushSign {
	
	Block targetSign;
	
	int playerCount;
	int maxPlayer;
	
	Location targetSpawn;
	
	public RushSign(Block targetSign)
	{
		this.targetSign = targetSign;
	}
}
