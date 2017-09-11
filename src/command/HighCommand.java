package command;

import static discordapi.BotUtils.*;

public class HighCommand extends Command{
	
	public HighCommand() {
		super("high",true);
	}
	
	public void subRun() throws EmoteNotFoundException {
		say(getPrintedEmote(shard.emotes.get(0))+" is winning with "+shard.data.get(0)+" uses");
	}
}
