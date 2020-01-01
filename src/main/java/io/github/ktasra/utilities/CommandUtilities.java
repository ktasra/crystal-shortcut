package io.github.ktasra.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class CommandUtilities {

	private static Map<String, String> argumentAliases;

	static {
		argumentAliases = new TreeMap<>();
	}

	public static boolean setArgumentAliases(String key, String value) {
		if (argumentAliases.containsKey(key)) {
			return false;
		}
		argumentAliases.put(key, value);
		return true;
	}

	public static void reload() {
		argumentAliases.clear();
	}

	public static String molding(String... args) {
		List<String> newArgs = new ArrayList<>();
		for (String arg : args) {
			if (argumentAliases.containsKey(arg)) {
				newArgs.add(argumentAliases.get(arg));
			} else {
				newArgs.add(arg);
			}
		}
		return String.join(" ", newArgs);
	}

	public static boolean match(String cmd, String... args) {
		return molding(args).startsWith(cmd);
	}

	private CommandUtilities() {
		throw new IllegalStateException("Utility class");
	}
}
