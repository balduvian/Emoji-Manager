package command;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import net.dv8tion.jda.core.MessageBuilder;

import static discordapi.Bot.*;
import static discordapi.BotUtils.*;

public class RipCommand extends Command{

	public RipCommand() {
		super("rip", false);
	}

	@Override
	public void subRun() throws Exception {
		String ripID = getArgument(0);
		int ripLength = ripID.length();
		System.out.println(ripID);
		if(ripLength==3) {
			BufferedImage baseTomb = ImageIO.read(getFile("res/rip/tombstone.png"));
			BufferedImage numberRef = ImageIO.read(getFile("res/rip/numbers.png"));
			
			Graphics g = baseTomb.getGraphics();
			
			char[] glyphs = ripID.toCharArray();
			for(int i = 0; i < 3; ++i) {
				glyphs[i] = (char)(glyphs[i]-48);
				if(glyphs[i]>10) {
					throw new NumberFormatException();
				}
				for(int x = 0; x < 22; ++x) {
					for(int y = 0; y < 22; ++y) {
						
						int c0 = baseTomb.getRGB(17+(i*25)+x, 65+y);
						int alp0 = ((c0 >>>   24) & 0xff);
						int red0 = ((c0 >>>  16) & 0xff);
						int gre0 = ((c0 >>> 8) & 0xff);
						int blu0 = ((c0 >>>  0) & 0xff);
						
						int c1 = numberRef.getRGB(x+(glyphs[i]*22), y);
						int alp1 = ((c1    >>>   24) & 0xff);
						int red1 = ((c1 >>>  16) & 0xff);
						int gre1 = ((c1 >>> 8) & 0xff);
						int blu1 = ((c1 >>> 0) & 0xff);
						
						int alp2 = alp0+alp1;
						if(alp2 > 255) {
							alp2 = 255;
						}
						double mod0 = alp1/255.0;
						double mod1 = 1 - mod0;
						
						int red2 = (int)(red0 * mod1 + red1 * mod0);
						int gre2 = (int)(red0 * mod1 + gre1 * mod0);
						int blu2 = (int)(blu0 * mod1 + blu1 * mod0);
						
						int nColor = (blu2) | (gre2 << 8) | (red2 << 16) | (alp2 << 24);
								
						baseTomb.setRGB(17+(i*25)+x, 65+y, nColor);
					}
				}
			}
			
			if(glyphs[0]==4 && glyphs[1]==7 && glyphs[2]==3 ) {
				say("nah");
			}else {
				File sav = getFile("res/rip/gen/rip"+ripID+".png");
				ImageIO.write(baseTomb, "PNG", sav);
				event.getChannel().sendFile(sav, new MessageBuilder().append(" ").build()).complete();
			}
			
		}else {
			say("I need a 3 digit number!");
		}
	}
}
