package io.github.ktasra.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.ktasra.CrystalShortcut;
import io.github.ktasra.utilities.CommandUtilities;

public class ShortcutCommand implements CommandExecutor {

	private final CrystalShortcut plugin;

	public ShortcutCommand(CrystalShortcut plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (CommandUtilities.match("config reload", args)) {
			plugin.reloadConfig();
			sender.sendMessage("ÅòaSuccess: Config reloaded");
			return true;
		}
		return false;
	}
}
