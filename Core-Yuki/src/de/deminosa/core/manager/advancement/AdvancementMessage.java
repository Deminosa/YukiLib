package de.deminosa.core.manager.advancement;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:11:40 # 08.07.2019
*
*/

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import de.deminosa.core.utils.CoreID;

public class AdvancementMessage extends DisplayMessage	{
	
	Advancement display; 
	
	private NamespacedKey id;
    private String icon;
    private String title, description, text;
    // challenge
    // goal
    // task
    private String frame = "task";
    private boolean announce = false, toast = true;
	private JavaPlugin pl;
	
	public AdvancementMessage(String id, FrameType type, String title, String icon, JavaPlugin pl)	{
		this(new NamespacedKey(pl, id), type, title, icon, pl);
	}
	
	public AdvancementMessage(NamespacedKey id, FrameType type, String title, String icon, JavaPlugin pl) {
		this.id = id;
		if(title.toCharArray().length > 60) {
			new ArrayIndexOutOfBoundsException("to many Chars! ("+title.toCharArray().length+")");
		}else if(title.toCharArray().length > 45) {
			this.title = title;
		}else {
			this.title = title;
		}
		this.description = "§7" + CoreID.generate(8);
		this.icon = icon;
		this.pl = pl;
		this.frame = type.name();
	}

	@Override
	public void showTo(Player player)	{
		showTo(Arrays.asList(player));
	}
	
	@Override
	public void showTo(Collection<? extends Player> players)	{
		add();
		grant(players);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				revoke(players);
				remove();
			}
		}.runTaskLater(pl, 20);
	}
	
	@SuppressWarnings("deprecation")
	private void add()	{
		try {
			Bukkit.getUnsafe().loadAdvancement(id, getJson());
			Bukkit.getLogger().info("Advancement " + id + " saved");
		} catch (IllegalArgumentException e){
			Bukkit.getLogger().info("Error while saving, Advancement " + id + " seems to already exist");
		}
	}
	
	@SuppressWarnings("deprecation")
	private void remove()	{
		Bukkit.getUnsafe().removeAdvancement(id);
	}
	
	private void grant(Collection<? extends Player> players) {
		Advancement advancement = Bukkit.getAdvancement(id);
		AdvancementProgress progress;
		for (Player player : players)	{
			
			progress = player.getAdvancementProgress(advancement);
			if (!progress.isDone())	{
				for (String criteria : progress.getRemainingCriteria())	{
					progress.awardCriteria(criteria);
				}
			}
		}
    }
	
	private void revoke(Collection<? extends Player> players)	{
		Advancement advancement = Bukkit.getAdvancement(id);
		AdvancementProgress progress;
		for (Player player : players)	{
			
			progress = player.getAdvancementProgress(advancement);
			if (progress.isDone())	{
				for (String criteria : progress.getAwardedCriteria())	{
					progress.revokeCriteria(criteria);
				}
			}
		}
	}
	
	public String getJson()	{
		
		JsonObject json = new JsonObject();

        JsonObject icon = new JsonObject();
        icon.addProperty("item", this.icon);

        JsonObject display = new JsonObject();
        display.add("icon", icon);
        display.addProperty("title", this.title);
        display.addProperty("description", this.description);
        display.addProperty("background", "minecraft:textures/gui/advancements/backgrounds/adventure.png");
        display.addProperty("frame", frame);
        display.addProperty("announce_to_chat", announce);
        display.addProperty("show_toast", toast);
        display.addProperty("hidden", true);
        display.addProperty("text", this.text);
        display.addProperty("theme", "narrator");

        JsonObject criteria = new JsonObject();
        JsonObject trigger = new JsonObject();

        trigger.addProperty("trigger", "minecraft:impossible");
        criteria.add("impossible", trigger);

        json.add("criteria", criteria);
        json.add("display", display);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(json);
		
	}
}
