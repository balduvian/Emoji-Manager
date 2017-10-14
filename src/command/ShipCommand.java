package command;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import discordapi.Bot;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.User;

import static discordapi.BotUtils.*;

public class ShipCommand extends Command{

	public ShipCommand() {
		super("ship", KEEPCOMMAND);
	}
	
	public void subRun() throws Exception {
		ArrayList<User> ps = new ArrayList<User>(event.getMessage().getMentionedUsers());
		if(ps.size()!=2) {
			say("p, two people");
		}else {
			String jars = "";
			
			jars += ps.get(0).getName().subSequence(0,ps.get(0).getName().length()/2);
			jars += ps.get(1).getName().subSequence(ps.get(1).getName().length()/2,ps.get(1).getName().length());
			
			BufferedImage b = new BufferedImage(256,128,BufferedImage.TYPE_INT_ARGB);
			BufferedImage p0 = getFromUrl(new URL(ps.get(0).getAvatarUrl()));
			System.out.println(ps.get(0).getAvatarUrl());
			BufferedImage p1 = getFromUrl(new URL(ps.get(1).getAvatarUrl()));
			BufferedImage heart = ImageIO.read(getFile("res/ship/heart.png"));
			Graphics g = b.getGraphics();
			g.drawImage(p0,0,0,128,128,null);
			g.drawImage(p1,128,0,128,128,null);
			g.drawImage(heart, 64, 0, 128, 128, null);
			
			File f = getFile("res/saved/_ship_.png");
			ImageIO.write(b, "PNG", f);
			event.getChannel().sendFile(f, new MessageBuilder().append("ship name: "+jars).build()).complete();
		}
	}
}
