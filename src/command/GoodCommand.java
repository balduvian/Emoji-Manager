package command;

public class GoodCommand extends Command{
	
	public GoodCommand() {
		super("goodjob",false);
	}
	
	public void subRun() {
		say("thanks!");
	}
}
