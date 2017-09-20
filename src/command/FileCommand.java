package command;

import java.io.File;

import discordapi.Bot;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

public class FileCommand extends Command{
	
	String root = "P:\\Pictures\\PHOTOSHOP";
	
	public FileCommand() {
		super("file",true,Bot.GOD);
	}
	
	public File finddir(String rot) {
		File f = null;
		superloop: while(true) {
			f = new File(rot);
			File[] poten = f.listFiles();
			while(true) {
				f = poten[(int)(Math.random()*poten.length)];
				System.out.println(f.getPath());
				if(f.isDirectory()) {
					poten = f.listFiles();
					if(poten.length==0 || f.isHidden()) {
						break;
					}else {
						break superloop;
					}
				}else {
					break;
				}
			}
		}
		return f;
	}
	
	public File findfil(String rot) {
		File f = null;
		superloop: while(true) {
			f = new File(rot);
			File[] poten = f.listFiles();
			while(true) {
				f = poten[(int)(Math.random()*poten.length)];
				System.out.println(f.getPath());
				if(f.isDirectory()) {
					poten = f.listFiles();
					if(poten.length==0 || f.isHidden()) {
						break;
					}
				}else {
					if(f.isHidden() || !isimage(f.getPath())) {
						break;
					}else {
						break superloop;
					}
				}
			}
		}
		return f;
	}
	
	public boolean isimage(String s) {
		int l = s.length();
		String bild = "";
		for(int i =l-1;i>-1;i--) {
			char c = s.charAt(i);
			if(c=='.') {
				break;
			}else {
				bild+= c;
			}
		}
		bild = bild.toLowerCase();
		if(bild.equals("gnp") || bild.equals("gpj") || bild.equals("fig")) {
			return true;
		}
		return false;
	}
	
	public void subRun(){
		File f = findfil(root);
		Message message = new MessageBuilder().append(f.getPath()).build();
		event.getChannel().sendFile(f, message).complete();
	}
}
