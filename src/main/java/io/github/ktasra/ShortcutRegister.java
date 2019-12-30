package io.github.ktasra;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

public final class ShortcutRegister {

	private final CrystalShortcut plugin;
	private final CommandMap map;

	public ShortcutRegister(CrystalShortcut plugin, CommandMap map) {
		this.plugin = plugin;
		this.map = map;
	}

	public void put(String alias, String run) {
		Command command = new Command(alias) {

			@Override
			public boolean execute(CommandSender sender, String commandLabel, String[] args) {
				return plugin.getServer().dispatchCommand(sender, run);
			}
		};
		command.setDescription("Execute the command: /" + run);
		command.setUsage("/<command>");
		map.register(plugin.getName(), command);
	}
}
