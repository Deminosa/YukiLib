package de.deminosa.core.entitys.user;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:51:31 # 04.06.2021
*
*/

public class UserLocation {

	private final Player player;
	
	public UserLocation(Player player) {
		this.player = player;
	}
	
	/**
	 * <p>see also {@link org.bukkit.Location}</p>
	 * @return Location
	 */
	public Location get() {
		return player.getLocation();
	}
	
	/**
	 * <p>Edit the player's velocity to e.g. shoot in the air.</p>
	 * <p><b>Example:</b> <code>editVelocity(0, 1, 0)</code></p>
	 * 
	 * @param float x
	 * @param float y
	 * @param float z
	 */
	public void editVelocity(float x, float y, float z) {
		player.setVelocity(new Vector(x, y, z));
	}

	/**
	 * <p>Teleports the player to a location.</p>
	 * @param location
	 */
	public void teleport(Location location) {
		player.teleport(location);
	}
	
	/**
	 * <p>Teleports the player to player.</p>
	 * @param userLocation
	 */
	public void teleport(UserLocation userLocation) {
		player.teleport(userLocation.get());
	}

	/**
	 * <p>Teleports the player to an entity</p>
	 * @param entity
	 */
	public void teleport(Entity entity) {
		player.teleport(entity);
	}
	
	/**
	 * <p>It returns true if the block locations match. It uses int and not double or float.</p>
	 * @param location
	 * @return boolean
	 */
	public boolean isLocationEqual(Location loc) {
		if(get().getBlockX() == loc.getBlockX() &&
			get().getBlockY() == loc.getBlockY() &&
			get().getBlockZ() == loc.getBlockZ()) {
			return true;
		}
		return false;
		
	}
	
	
	/**
	 * <p>It comes true if the block was found in the cubed radius.</p>
	 * <p>r = cubed radius</p>
	 * @param material
	 * @param r
	 * @return boolean
	 */
	public boolean isBlockNearby(Material mat, int r) {
		for(int x = -r; x<r; x++) {
			for(int y = -r; y<r; y++) {
				for(int z = -r; z<r; z++) {
					if(get().add(x,y,z).getBlock().getType() == mat) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * <p>If the searched block was found as a material, it will be returned as a Block.</p>
	 * <p>r = cubed radius</p>
	 * @param material
	 * @param r
	 * @return org.bukkit.block.Block
	 */
	public Block getNearbyBlock(Material mat, int r) {
		for(int x = -r; x<r; x++) {
			for(int y = -r; y<r; y++) {
				for(int z = -r; z<r; z++) {
					if(get().add(x,y,z).getBlock().getType() == mat) {
						return get().add(x,y,z).getBlock();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * <p>If the searched block was found as a material, it will be returned as a Location.</p>
	 * <p>r = cubed radius</p>
	 * @param material
	 * @param r
	 * @return org.bukkit.Location
	 */
	public Location getNearbyBlockLocation(Material mat, int r) {
		for(int x = -r; x<r; x++) {
			for(int y = -r; y<r; y++) {
				for(int z = -r; z<r; z++) {
					if(get().add(x,y,z).getBlock().getType() == mat) {
						return get().add(x,y,z);
					}
				}
			}
		}
		return null;
	}
}
