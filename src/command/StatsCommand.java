package command;

import static discordapi.BotUtils.*;

public class StatsCommand extends Command{
	
	public StatsCommand() {
		super("stats",true);
	}
	
	public void subRun(){
		String build = "Emoji use statistics:\n";
		for(int e=0;e<shard.numEmotes;e++) {
			build += (getPrintedEmote(shard.emotes.get(e))+" "+shard.data.get(e)+"  ");
		}
		say(build);
	}
}
