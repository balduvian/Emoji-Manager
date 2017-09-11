package discordapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import command.ArtCommand;
import command.AskCommand;
import command.Command;
import command.EmoteCommand;
import command.FileCommand;
import command.GoodCommand;
import command.HelpCommand;
import command.HeyCommand;
import command.HighCommand;
import command.InfoCommand;
import command.ListCommand;
import command.LowCommand;
import command.OpinionCommand;
import command.SayCommand;
import command.StatsCommand;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static discordapi.Bot.*;
import static discordapi.BotUtils.*;

public class Shard {
	
	public MessageListener listener;
	public Guild guild;
	public long id;
	
	public ArrayList<Emote> emotes;
	public ArrayList<Integer> data;
	public int numEmotes;
	
	public boolean shouldDelete;
	
	public Command[] commands = {
			new GoodCommand(),	
			new HelpCommand(),
			new InfoCommand(),
			new StatsCommand(),
			new HighCommand(),
			new LowCommand(),
			new ListCommand(),
			new ArtCommand(),
			new HeyCommand(),
			new EmoteCommand(),
			new OpinionCommand(),
			new AskCommand(),
			new SayCommand(),
			new FileCommand(),
	};
	public int numcommands = commands.length;
		
	public Shard(Guild g) {
		guild = g;
		id = g.getIdLong();
		
		listener = new MessageListener(this);
		Bot.jda.addEventListener(listener);
		
		emotes = new ArrayList<Emote>(g.getEmotes());
		numEmotes = emotes.size();
		data = new ArrayList<Integer>(numEmotes);
		for(int i=0;i<numEmotes;i++) {
			data.add(0);
		}
		loaddata();
		
		//shouldDelete = true;
		//g.getTextChannels().get(0).sendMessage("onlin!").complete();
		
	}
	
	public void destruct() {
		jda.removeEventListener(listener);
	}
	
	public void loaddata() {
		File f = new File(ROOT + "data\\" + guild.getIdLong());
		try {
			FileInputStream o = new FileInputStream(f);
			int av = o.available();
			int loadedEmotes = av/36;
			
			for(int e=0;e<loadedEmotes;e++) {
				StringBuilder build = new StringBuilder();
				for(int n=0;n<32;n++) {
					char c = (char)o.read();
					if(c!=0) {
						build.append(c);
					}
				}
				String compare = build.toString();
				
				int n0 = o.read();
				int n1 = o.read();
				int n2 = o.read();
				int n3 = o.read();
				int number = ((n0 >> 0) | (n1 >> 8) | (n2 >> 16) | (n3 >> 24));
				
				int lk = loadedEmotes;
				for(int k = 0; k < lk; ++k) {
					if(emotes.get(k).getName().equals(compare)){
						data.set(k,number);
						break;
					}
				}
			}
			
			o.close();
			
		}catch(Exception ex) {
			try {
				FileOutputStream o = new FileOutputStream(f);
				o.close();
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		}
	}
	
	public class MessageListener extends ListenerAdapter{
		
		Shard shard;
		
		public MessageListener(Shard s) {
			shard = s;
		}
		
		public void onMessageReceived(MessageReceivedEvent e){
			
			Guild guildNow = e.getGuild();
			//gate for this server
			if(guildNow == guild) {
				System.out.println(e.getChannel().getName()+" | "+e.getAuthor().getName()+": "+e.getMessage().getContent());
				//gate off to bots
				if(!e.getAuthor().isBot()) {
					
					//update emoji data
					ArrayList<Emote> newEmotes = new ArrayList<Emote>(guild.getEmotes());
					ArrayList<Integer> newData = new ArrayList<Integer>();
					
					numEmotes = newEmotes.size();
					int oldNumEmoji = emotes.size();
							
					for(int i = 0; i < numEmotes; ++i) {
						int num = 0;
						for(int o = 0; o < oldNumEmoji; ++o) {
							if(emotes.get(o).getName().equals(newEmotes.get(i).getName())) {
								num = data.get(o);
								break;
							}
						}
						newData.add(num);
					}
					emotes = newEmotes;
					data = newData;
					
					//sort
					boolean need;
					do{
						need = false;
						for(int i = 1; i < numEmotes; ++i) {
							if(data.get(i)>data.get(i-1)) {
								int temp0 = data.get(i);
								Emote temp1 = emotes.get(i);
								data.set(i, data.get(i-1));
								emotes.set(i, emotes.get(i-1));
								data.set(i-1, temp0);
								emotes.set(i-1, temp1);
								need = true;
							}
						}
					} while(need);
					
					//detect emotes in message and update data accordingly
					char[] found = new char[0]; //the fragment of emoji name found in message
					int foundLength = 0; //how big the fragment is
					char[] message = e.getMessage().getContent().toCharArray();
					int mesLength = message.length;
					
					boolean emoteMode = false;
					
					for(int i = 0; i < mesLength; ++i) {
						if(emoteMode) {
							
							if(message[i] == ':') {
								
								emoteMode = false;
								for(int j = 0; j < numEmotes; ++j) {
									char[] compare = emotes.get(j).getName().toCharArray();
									if(foundLength == compare.length) {
										boolean passed = true;
										for(int v = 0; v < foundLength; ++v) {
											if(found[v] != compare[v]) {
												passed = false;
												break;
											}
										}	
										if(passed) {
											data.set(j,data.get(j)+1);
											break;
										}
									}
								}
								
							}else {
								
								found[foundLength] = message[i];
								foundLength++;
								
							}
							
						}else {
							if(message[i]==':') {
								emoteMode = true;
								foundLength = 0;
								found = new char[32];
							}
						}
					}
					
					//save emoji data
					File f = new File(Bot.ROOT + "data\\" + guild.getIdLong());
					try {
						FileOutputStream o = new FileOutputStream(f);//start output stream
						for(int i = 0; i < numEmotes; ++i) {//for each emoji
							char[] name = emotes.get(i).getName().toCharArray();//turn name into char array
							for(int c = 0; c < 32; ++c) {//32 bytes deticated to each name
								try {
									o.write(name[c]);
								}catch(ArrayIndexOutOfBoundsException ex) {
									o.write(0);
								}
							}//now write the number to 32 bits
							int fac = data.get(i);
							o.write((fac      ) & 0xff);
							o.write((fac <<  8) & 0xff);
							o.write((fac << 16) & 0xff);
							o.write((fac << 24) & 0xff);
						}
						o.close();
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					
					//check command
					boolean command = true;
					if(mesLength<COMMAND_START_LENGTH) {
						command = false;
					}else {
						for(int i=0;i<COMMAND_START_LENGTH;i++) {
							if(message[i]!=COMMAND_START[i]) {
								command = false;
							}
						}
					}
					
					if(command) {
						ArrayList<String> args = new ArrayList<String>();
						String ou = "";
						for(int i=COMMAND_START_LENGTH;i<mesLength;i++) {//collect args
							if(message[i] != ' ') {
								ou += message[i];
							}
							if(message[i] == ' ' || i==mesLength-1) {
								args.add(ou);
								ou = "";
							}
						}
						
						if(!args.isEmpty()) {
							String tw = args.get(0);
							args.remove(0);
							for(int c=0;c<numcommands;c++) {
								if(tw.startsWith(commands[c].trigger)) {
									try {
										commands[c].run(shard, e, args);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
									break;
								}
							}
						}
					}
					
					//check flags
					char[] puremessage = purify(toLowerCase(message));
					for(int i=0;i<numflags;i++) {
						if(hasin(flags[i].trigger.toCharArray(), puremessage)) {
							try {
								flags[i].run(shard, e, new ArrayList<String>(0));
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
				
				//delete check
				else if(e.getAuthor().getIdLong()==Bot.selfID) {
					
					if(shouldDelete) {
						long IDNow = e.getMessage().getIdLong();
						e.getChannel().deleteMessageById(IDNow).queueAfter(2000, TimeUnit.MILLISECONDS);
						shouldDelete = false;
					}
					/*long IDNow = e.getMessage().getIdLong();
					
					for(int i=0;i<toDelete.size();i++) {
						if(IDNow == toDelete.get(i)) {
							e.getChannel().deleteMessageById(IDNow).queueAfter(2000, TimeUnit.MILLISECONDS);
							toDelete.remove(i);
							break;
						}
					}*/
				}
				
			}
		}
	}
}
