package de.mrpixeldream.crystalminers.rushmode;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

public class RushSign {
	
	Block targetSign;
	BlockState sign;
	
	int playerCount;
	int maxPlayer;
	
	Location targetSpawn;
	
	public RushSign(Block targetSign)
	{
		this.targetSign = targetSign;
	}
}
