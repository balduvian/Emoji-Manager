package command;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class RipCommand extends Command{

	public RipCommand() {
		super("rip", false);
	}

	@Override
	public void subRun() throws Exception {
		String ripID = getArgument(0);
		int ripLength = ripID.length();
		if(ripLength==3) {
			BufferedImage baseTomb = ImageIO.read(new File("C:\\Users\\Emmett\\Desktop\\JAVA\\emojidata\\rip\\tombstone.png"));
			BufferedImage numberReference = ImageIO.read(new File("C:\\Users\\Emmett\\Desktop\\JAVA\\emojidata\\rip\\numbers.png"));
			char[] glyphs = ripID.toCharArray();
			
		}else {
			say("I need a 3 digit number!");
		}
	}
}
