package command;

import discordapi.Bot;

public class HelpCommand extends Command{
	
	public HelpCommand(){
		super("help",true);
	}
	
	public void subRun() {
		String build = "Command list:\n";
		String start = "";
		for(int i=0;i<Bot.COMMAND_START_LENGTH;i++) {
			start+=Bot.COMMAND_START[i];
		}
		for(int c=0;c<shard.numcommands;c++) {
			build+=(start+shard.commands[c].trigger+" ");
		}
		say(build);
	}
}
