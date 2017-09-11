package command;

import static discordapi.BotUtils.*;

public class AskCommand extends Command{
	
	public AskCommand() {
		super("ask",false);
	}
	
	String[] resps = {
		"y",
		"ye",
		"yes",
		"yeah",
		"hell yeah!",
		"yep",
		"n",
		"no",
		"nah",
		"def nop",
		"nop",
		"nope",
		"yep",
		"yee",
		"nep",
		"hell no!",
		"prob ye",
		"prob not",
		"who knows?"
	};
	int numresps = resps.length;
			
	public void subRun() throws CommandArgumentException{
		int acarg = args.size();
		String thing = purify(event.getAuthor().getName());
		for(int i=0;i<acarg;i++) {
			thing+= purify((getArgument(i).toLowerCase()));
		}
		int one = (int)(getvalue(thing)*numresps);
		say(resps[one]);
	}
}
