package donkeykong;
import java.awt.*;

public class Collide {
    // checks if area on player is inside	
	public static boolean playerPlatformCollide(Point playerPoint, Platform pl1) 																		// the platform
	{	return pl1.contains(playerPoint);  } // if inside returns true
    // checks if area on player is in ladder
	public static boolean playerLadderCollide(Point playerPoint, Ladder l1) 
	{	return l1.contains(playerPoint); 	}
	// checks if area on player has reached Pauline
	public static boolean playerPaulineCollide(Point playerPoint, Characters c1) 
	{	return c1.contains(playerPoint); 	}
	//meant to check if area in barrels
	public static boolean playerBarrelCollide(Point playerPoint, EnemyBarrel b1) 
	{	return b1.contains(playerPoint); 	}
}
