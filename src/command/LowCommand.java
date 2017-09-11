package command;

import static discordapi.BotUtils.*;

public class LowCommand extends Command{

	public LowCommand() {
		super("low",true);
	}
	
	public void subRun() throws EmoteNotFoundException {
		int last = shard.emotes.size()-1;
		say("rip "+getPrintedEmote(shard.emotes.get(last))+", with only "+shard.data.get(last)+" uses");
	}
}
