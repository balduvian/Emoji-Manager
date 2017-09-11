package command;

import static discordapi.BotUtils.*;

public class ListCommand extends Command{
	
	public ListCommand() {
		super("list",true);
	}
	
	public void subRun(){
		String build = "";
		int el = shard.emotes.size();
		for(int e=0;e<el;e++) {
			build += (getPrintedEmote(shard.emotes.get(e)));
		}
		say(build);
	}
}
