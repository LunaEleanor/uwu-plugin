package com.uwuScape;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class uwuScapeTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(uwuScapePlugin.class);
		RuneLite.main(args);
	}
}