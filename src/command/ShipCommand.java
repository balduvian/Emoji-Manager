package command;

import java.util.LinkedList;

import net.dv8tion.jda.core.entities.User;

public class ShipCommand extends Command{

	public ShipCommand() {
		super("ship", KEEPCOMMAND);
	}
	
	public void subRun() throws Exception {
		LinkedList<User> ps = new LinkedList<User>(event.getMessage().getMentionedUsers());
		if(ps.size()!=2) {
			say("p nah");
		}else {
			
		}
	}
}
