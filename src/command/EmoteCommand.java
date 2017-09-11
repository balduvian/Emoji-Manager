package command;

import java.io.File;

import discordapi.Bot;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

public class EmoteCommand extends Command{

	public EmoteCommand() {
		super("emote",true);
	}
	
	public void subRun(){
		File[] f = new File(Bot.ROOT+"\\emotions").listFiles();
		int emn = (int)(Math.random()*f.length);
		Message message = new MessageBuilder().append(" ").build();
		event.getChannel().sendFile(f[emn], message).complete();
	}
}
