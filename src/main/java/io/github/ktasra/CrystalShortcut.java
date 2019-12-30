package io.github.ktasra;

import java.lang.reflect.Field;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public class CrystalShortcut extends JavaPlugin {

	@Override
	public void onEnable() {
		CommandMap commandMap = null;
		try {
			final Field bukkitCommandMap = getServer().getClass().getDeclaredField("commandMap");
			bukkitCommandMap.setAccessible(true);
			commandMap = (CommandMap) bukkitCommandMap.get(getServer());

		} catch (Exception e) {
			getLogger().severe("�R�}���h�̓o�^�Ɏ��s���܂���");
			return;
		}
		ShortcutRegister register = new ShortcutRegister(this, commandMap);
		register.put("kl", "kill @e[type=!Player]");
		register.put("wl", "control world list");
	}
}
