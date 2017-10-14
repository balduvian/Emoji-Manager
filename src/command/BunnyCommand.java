package command;

import java.io.File;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

import static discordapi.BotUtils.*;

public class BunnyCommand extends Command{
	
	public BunnyCommand() {
		super("bunny", false);
	}
	
	public void subRun() {
		File[] f = getFile("res/bunny").listFiles();
		int emn = (int)(Math.random()*f.length);
		
		Message message = new MessageBuilder().append("did someone say bunny?").build();
		
		event.getChannel().sendFile(f[emn], message).complete();
	}
}
