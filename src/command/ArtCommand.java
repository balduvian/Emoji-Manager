package command;

import static discordapi.BotUtils.*;

public class ArtCommand extends Command{

	public ArtCommand() {
		super("art",true);
	}
	
	public void subRun() throws NumberFormatException, CommandArgumentException{
		String build = "";
		int nam = Integer.parseInt(getArgument(0));
		if(nam<1) {
			tempSay("Give me a number greater than that!");
		}else if(nam>27){
			tempSay(":b: don't expect me to do that!");
		}else {
			for(int i=0;i<nam;i++) {
				int emn = (int)(Math.random()*shard.numEmotes);
				build += (getPrintedEmote(shard.emotes.get(emn)));
			}
			say(build);
		}	
	}
}
