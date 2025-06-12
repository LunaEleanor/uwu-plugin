package com.uwuScape;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import java.io.*;
import net.runelite.client.callback.ClientThread;

@Slf4j
@PluginDescriptor(
	name = "uwuScape"
)
public class uwuScapePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private uwuScapeConfig config;

	@Inject
	private ClientThread clientThread;

	@Override
	protected void startUp() throws Exception
	{
		log.info("uwuScape started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("uwuScape stopped!");
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded e) {
		if(e.getGroupId() == WidgetID.DIALOG_NPC_GROUP_ID) {
			Widget widget = client.getWidget(WidgetInfo.DIALOG_NPC_TEXT.getPackedId());
			clientThread.invokeLater(() -> {
				//System.out.println("Raw input: [" + widget.getText() + "]");

				String text = Owoify.convert(widget.getText(), config);
				widget.setText(text);
			});
		}
		else if(e.getGroupId() == WidgetID.DIALOG_PLAYER_GROUP_ID) {
			Widget widget = client.getWidget(WidgetInfo.DIALOG_PLAYER_TEXT.getPackedId());
			clientThread.invokeLater(() -> {
				String text = Owoify.convert(widget.getText(), config);
				widget.setText(text);
			});
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage e) throws IOException {
		if(e.getType().equals(ChatMessageType.DIALOG)) {
			return;
		}
		else if (config.convertChat()){
			e.getMessageNode().setValue(Owoify.convert(e.getMessage(), config));
		}
	}

	@Subscribe
	public void onOverheadTextChanged(OverheadTextChanged e) {
		String text = Owoify.convert(e.getOverheadText(), config);
		e.getActor().setOverheadText(text);
		e.getActor().setOverheadCycle(30);
	}

	@Provides
	uwuScapeConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(uwuScapeConfig.class);
	}
}
