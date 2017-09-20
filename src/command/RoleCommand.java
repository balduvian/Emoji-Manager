package command;

import net.dv8tion.jda.core.managers.RoleManager;
import static discordapi.BotUtils.*;

import java.awt.Color;

public class RoleCommand extends Command{
	public RoleCommand() {
		super("role",false);
	}
	
	public void subRun() throws Exception {
		int numArgs = args.size();
		
		RoleManager rm = new RoleManager(shard.guild.getMember(event.getAuthor()).getRoles().get(0));
		
		String mode = getArgument(0);
		
		if(mode.equals("name")) {
			
			if(getArgument(1).equals("random")) {
				
				int nameLength = rando(6,12);
				StringBuilder build = new StringBuilder(nameLength);
	            for(int i = 0; i < nameLength; ++i) {
	                build.append((char)rando(20,127));
	            }
	            String newName = build.toString();
	            
	            rm.setName(newName).complete();
	            
			}else {
				
				StringBuilder build = new StringBuilder(32);
	            for(int i = 1; i < numArgs;) {
	                build.append(getArgument(i));
	                ++i;
	                if(i!=numArgs) {
	                    build.append(' ');
	                }
	            }
	            String newName = build.toString();
	            
	            rm.setName(newName).complete();
	            
			}
            
		}else if(mode.equals("color")) {
			
			if(getArgument(1).equals("random")) {
				
				int hexColor = (int)(Math.random()*0xffffff);
				Color c = new Color(hexColor);
				rm.setColor(c).complete();
				
			}else {
				
				Color c;
				
				if(getArgument(1).startsWith("0x")) {
					
					int rgb = Integer.parseInt(getArgument(1).split("x")[1],16);
					
					c = new Color(rgb);
					
				}else {
					
					int r = Integer.parseInt(getArgument(1));
					int g = Integer.parseInt(getArgument(2));
					int b = Integer.parseInt(getArgument(3));
					
					c = new Color(r, g, b);
					
				}
				
				rm.setColor(c).complete();
				
			}
			
		}else {
			
			say("not a valid role change mode!\nvalid modes: name, color");
			
		}
	}
}
