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
		
		URL url = new URL(found.getImageUrl());
		
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		connection.connect();
		BufferedImage b = ImageIO.read(connection.getInputStream());
		
		File f = new File(ROOT+"saved\\"+found.getName()+".png");
		ImageIO.write(b, "png", f);
		
		event.getChannel().sendFile(f, new MessageBuilder().append(' ').build()).complete();
		
	}
}
