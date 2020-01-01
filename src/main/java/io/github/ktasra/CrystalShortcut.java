package io.github.ktasra;

import java.lang.reflect.Field;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.ktasra.command.ShortcutCommand;
import io.github.ktasra.utilities.CommandUtilities;

public class CrystalShortcut extends JavaPlugin {

	public static final String PREFIX = "short";
	private FileConfiguration config;

	@Override
	public void onEnable() {

		saveDefaultConfig();
		loadConfig();

		getCommand("shortcut").setExecutor(new ShortcutCommand(this));
	}

	public FileConfiguration loadConfig() {
		CommandUtilities.reload();
		config = getConfig();
		String pathCommands = "commands";
		String pathArgs = "arguments";
		for (String key : config.getConfigurationSection(pathCommands).getKeys(false)) {
			String value = config.getString(pathCommands + "." + key);
			CommandUtilities.setArgumentAliases(key, value);
			registerCommand(key, value);
		}
		for (String key : config.getConfigurationSection(pathArgs).getKeys(false)) {
			String value = config.getString(pathArgs + "." + key);
			CommandUtilities.setArgumentAliases(key, value);
		}
		return config;
	}

	private void registerCommand(String name, String run) {
		CommandMap commandMap = (CommandMap) getPrivateField(getServer(), "commandMap");
		if (commandMap == null) {
			getLogger().info("Failed command register");
			return;
		}
		Command command = new Command(name) {

			@Override
			public boolean execute(CommandSender sender, String commandLabel, String[] args) {
				return getServer().dispatchCommand(sender, run + " " + String.join(" ", args));
			}
		};
		command.setDescription("This command is shortcut command for /" + run);
		command.setUsage("/" + run + " args...");
		commandMap.register(PREFIX, command);
	}

	private static Object getPrivateField(Object object, String field) {
		try {
			Class<?> clazz = object.getClass();
			Field objectField = clazz.getDeclaredField(field);
			objectField.setAccessible(true);
			Object result = objectField.get(object);
			objectField.setAccessible(false);
			return result;
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void reloadConfig() {
		super.reloadConfig();
		if (config != null) {
			loadConfig();
		}
	}
}
