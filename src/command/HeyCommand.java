package command;

import static discordapi.Bot.*;

public class HeyCommand extends Command{
	
	public HeyCommand() {
		super("hey",false);
	}
	
	public void subRun(){
		char[] message = event.getMessage().getContent().toCharArray();
		int mesLength = message.length;
		int yad = 0;
		boolean wut = false;
		for(int i = COMMAND_START_LENGTH + 3;i < mesLength; ++i) {
			if(message[i]=='y' || message[i]=='!') {
				yad++;
			}else {
				wut = true;
				break;
			}
		}
		if(wut) {
			say("hey?");
		}else {
			StringBuilder build = new StringBuilder(3 + yad);
			build.append(new char[] {'h','e','y'});
			for(int i=0;i<yad;i++) {
				build.append('y');
			}
			build.append('!');
			say(build.toString());
		}
	}
}
