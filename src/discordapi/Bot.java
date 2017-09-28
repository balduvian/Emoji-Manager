package discordapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import command.*;
import net.dv8tion.jda.client.JDAClient;
import net.dv8tion.jda.client.entities.impl.JDAClientImpl;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.managers.GuildManager;

public class Bot extends JFrame{
	
	//https://discordapp.com/oauth2/authorize?client_id=343876542536876032&scope=bot&permissions=0
	
	private static final long serialVersionUID = 6386217590514626536L;

	public static final String ROOT = "P:\\Programming\\emojiManager\\";
	
	static JDA jda;
	int numGuilds;
	
	public static final String INITIAL_TOKEN = "HrMzJ<_2ILMyILI2J<_2H<Iy)<Cke^s.g=rZIk5O3H06mj1Bsg.HTM23=<M";
	
	public static final long GOD = 258485243038662657L;
	
	public static long selfID;
	
	public static final char[] COMMAND_START = {'='};
	public static final int COMMAND_START_LENGTH = COMMAND_START.length;
	
	public static ArrayList<Integer> delstats;
	
	public static Command[] flags = {
		new BunnyCommand(),
		new SabCommand(),
	};
	public static int numflags = flags.length;
	
	public static ArrayList<Shard> shards;
	
	public String changeString(String string, byte[] modifs) {
		int length = string.length();
		int cycle = modifs.length;
		int modIndex = 0;
		StringBuilder build = new StringBuilder(length); 
		for(int i = 0; i < length; ++i) {
			build.append((char)(string.charAt(i)+modifs[modIndex]));
			++modIndex;
			modIndex %= cycle;
		}
		return build.toString();
	}
	
	//start the bot up
	public Bot(){
		
		System.out.println("Entry Key:");
		
		Scanner s = new Scanner(System.in);
		char[] password = s.nextLine().toCharArray();
		
		byte[] pass = new byte[4];
		int length = password.length;
		int passIndex = 0;
		
		StringBuilder numBuild = new StringBuilder(4);
		for(int i = 0; i < length; ++i) {
			char now = password[i];
			if(now == ' ') {
				pass[passIndex] = Byte.parseByte(numBuild.toString());
				++passIndex;
				numBuild = new StringBuilder(4);
			}else {
				numBuild.append(now);
			}
		}
		s.close();
		
		String token = changeString(INITIAL_TOKEN, pass);
		
		//setup JDA
		try{
			JDABuilder jBuild = new JDABuilder(AccountType.BOT);
			jBuild.setGame(Game.of("kek 3.0"));
			jda = jBuild.setToken(token).buildBlocking();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
		selfID = jda.getSelfUser().getIdLong();
		setVisible(true);
		setSize(160,90);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//setup
		botSetup();
		
		/*GuildController g = new GuildController(shards.get(1).guild);
		for(int i=0;i<g.getGuild().getRoles().size();i++) {
			try {
				g.addRolesToMember(g.getGuild().getMemberById(GOD), g.getGuild().getRoles().get(i));
			}catch(Exception ex) {
				
			}
		}
		
		System.out.println(shards.get(1).guild.getMemberById(GOD).getRoles().size());
		for(int i=0;i<shards.get(1).guild.getMemberById(GOD).getRoles().size();i++) {
			shards.get(1).guild.getMemberById(GOD).getRoles().get(i);
		}
		
		Scanner s = new Scanner(System.in);
		boolean talk = true;
		while(talk) {
			shards.get(1).guild.getTextChannels().get(0).sendMessage(s.nextLine()).queue();
		}
		s.close();*/
	}
	
	public void botSetup() {
		
		shards = new ArrayList<Shard>();
		
		ArrayList<Guild> guildList = new ArrayList<Guild>(jda.getGuilds());
		numGuilds = guildList.size();
		
		for(int i = 0; i < numGuilds; ++i){
			Guild g = guildList.get(i);
			shards.add(new Shard(g));
		}
	}
	
	//manage servers coming and going
	public class ServerListener extends ListenerAdapter{
		public void onGuildJoined(GuildJoinEvent e){
			Guild g = e.getGuild();
			++numGuilds;
			shards.add(new Shard(g));
		}
		public void onGuildLeft(GuildJoinEvent e){
			long gID = e.getGuild().getIdLong();
			for(int i = 0; i < numGuilds; ++i) {
				if(gID == shards.get(i).id) {
					shards.get(i).destruct();
					shards.remove(i);
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Bot();
	}
}