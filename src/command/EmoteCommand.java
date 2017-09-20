package command;

import java.io.File;
import java.io.IOException;

import discordapi.Bot;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Icon;
import net.dv8tion.jda.core.entities.Message;

import static discordapi.BotUtils.*;

public class EmoteCommand extends Command{
	
	//TEMP DISABLED//
	
	public EmoteCommand() {
		super("emote",true);
	}
	
	public String genKek() {
		StringBuilder build = new StringBuilder(32);
		for(int i = 0; i < 32; ++i) {
			build.append((char)(rando(97,122)));
		}
		return build.toString();
	}
	
	public void subRun(){
		
		if(shard.numEmotes != 50) {
			try {
				File[] f = new File(Bot.ROOT+"\\emotions").listFiles();
				int emn = (int)(Math.random()*f.length);

				shard.guild.getJDA().getAccountType();
				
				Emote temp = shard.guild.getController().createEmote(genKek(), Icon.from(f[emn])).complete();
				
				event.getChannel().sendMessage(getPrintedEmote(temp)).complete();
				
				temp.delete().complete();
			} catch (IOException ex) {
				System.out.println("dun kek'd");
				ex.printStackTrace();
			}
		}else {
			tempSay("i can't do that rn");
		}
	}
}
