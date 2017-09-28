package command;

import net.dv8tion.jda.core.managers.RoleManager;
import static discordapi.BotUtils.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;

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
			
			if(getArgument(1).equals("pick")) {
				
				BufferedImage b = getFromUrl(new URL(event.getAuthor().getAvatarUrl()));
				
				int kekTime = 0;
				
				int re;
				int gr;
				int bl;
				int al;
				
				do {
					int x = (int)(Math.random()*b.getWidth());
					int y = (int)(Math.random()*b.getHeight());
					int c = b.getRGB(x, y);
					System.out.println(x +" "+y);
					
					al = ((c>>24) & 0xff);
					re = ((c>>16) & 0xff);
					gr = ((c>> 8) & 0xff);
					bl = ((c    ) & 0xff);
					
					++kekTime;
				} while(al==0 && kekTime < 100);
				
				Color c = new Color(re,gr,bl);
				rm.setColor(c).complete();
				
			}
			else if(getArgument(1).equals("match")) {
				
				BufferedImage b = getFromUrl(new URL(event.getAuthor().getAvatarUrl()));
				
				int w = b.getWidth();
				int h = b.getHeight();
				
				double re = 0;
				double gr = 0;
				double bl = 0;
				
				double t = 0;
				for(int i = 0; i < w; ++i) {
					for(int j = 0; j < h; ++j) {
						int c = b.getRGB(i, j);
						System.out.println(c);
						double mod = (double)((c>>24) & 0xff)/255d;
						re += ((c>>16) & 0xff)*mod;
						gr += ((c>> 8) & 0xff)*mod;
						bl += ((c    ) & 0xff)*mod;
						t += mod;
					}
				}
				
				System.out.println(t);
				
				if(t==0) {
					re = 0;
					gr = 0;
					bl = 0;
				}else {
					re /= t;
					gr /= t;
					bl /= t;
				}
				
				System.out.println(re + " " + gr + " " + bl);
				
				Color c = new Color((int)re,(int)gr,(int)bl);
				rm.setColor(c).complete();
				
			}else if(getArgument(1).equals("random")) {
				
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
