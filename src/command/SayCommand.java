package command;

import discordapi.Bot;

public class SayCommand extends Command{
	
	public SayCommand() {
		super("say",true,Bot.GOD);
	}
	
	public void subRun() throws CommandArgumentException {
		int acarg = args.size();
		if(acarg>0) {
			String thing = "";
			for(int i=0;i<acarg;i++) {
				thing+= (getArgument(i));
				if(i<acarg-1) {
					thing += ' ';
				}
			}
			say(thing);
		}
	}
}
