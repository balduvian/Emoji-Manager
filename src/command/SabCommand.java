package command;

public class SabCommand extends Command{
	
	public SabCommand() {
		super("rip473", false);
	}
	
	public void subRun() {
		event.getChannel().sendMessage("*ｒｉｐ 568").complete();
	}
}
