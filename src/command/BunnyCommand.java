package command;

import java.io.File;

import discordapi.Bot;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

public class BunnyCommand extends Command{
	
	public BunnyCommand() {
		super("bunny", false);
	}
	
	public void subRun() {
		File[] f = new File(Bot.ROOT+"\\bunny").listFiles();
		int emn = (int)(Math.random()*f.length);
		System.out.println(f[emn].getName());
		Message message = new MessageBuilder().append("Did someone say bunny?").build();
		event.getChannel().sendFile(f[emn], message).complete();
	}
}
