package command;

import static discordapi.BotUtils.*;
import static discordapi.Bot.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Emote;

public class GetCommand extends Command{
	
	public GetCommand() {
		super("get",KEEPCOMMAND);
	}
	
	public void subRun() throws Exception {
		
		String eName = parseEmote(getArgument(0));
		
		Emote found = findActualEmote(eName);
		
		BufferedImage b = getFromUrl( new URL(found.getImageUrl()));
		
		File f = getFile("res/saved/"+found.getName()+".png");
		ImageIO.write(b, "png", f);
		event.getChannel().sendFile(f, new MessageBuilder().append(' ').build()).complete();
		
	}
}
