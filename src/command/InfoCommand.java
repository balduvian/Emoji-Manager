package command;

import static discordapi.BotUtils.*;

public class InfoCommand extends Command{
	
	public InfoCommand() {
		super("info",true);
	}
	
	public void subRun() throws EmoteNotFoundException, CommandArgumentException {
		String name = parseemoji(getArgument(0));
		int index = findEmote(name);
		say(getPrintedEmote(shard.emotes.get(index))+" has been used "+shard.data.get(index)+" times");
	}
}
