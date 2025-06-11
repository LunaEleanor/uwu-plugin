package com.uwuSounds;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class uwuSoundsTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(uwuSoundsPlugin.class);
		RuneLite.main(args);
	}
}