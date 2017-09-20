package discordapi;

import java.util.LinkedList;

import net.dv8tion.jda.core.entities.Emote;

abstract public class BotUtils {
	
	public static boolean hasin(char[] c0, char[] c1) {
		int cl = c0.length;
		int tln = c1.length;
		int cindex = 0;

		for(int i=0;i<tln;i++) {
			if(c1[i]==c0[cindex]) {
				cindex++;
				if(cindex==cl) {
					return true;
				}
			}else {
				cindex=0;
			}
		}
		
		return false;
	}
	
	public static char[] toLowerCase(char[] string) {
		int length = string.length;
		for(int i=0;i<length;i++) {
			if(string[i]>64 && string[i]<91) {
				string[i]+=32;
			}
		}
		return string;
	}
	
	public static char[] purify(char[] ad) {
		int l=ad.length;
		String ret = "";
		for(int i=0;i<l;i++) {
			char cn = ad[i];
			if(cn>96 && cn<123) {
				ret+=cn;
			}
		}
		return ret.toCharArray();
	}
	
	public static String numberPurify(String origin) {
		char[] chars = origin.toCharArray();
		int length=chars.length;
		String ret = "";
		for(int i=0;i<length;i++) {
			char now = chars[i];
			if((now>96 && now<123) || (now>47 && now<58)) {
				ret+=now;
			}
		}
		return ret;
	}
	
	public static char[] numberPurify(char[] origin) {
		int length=origin.length;
		StringBuilder build = new StringBuilder(length);
		for(int i=0;i<length;i++) {
			char now = origin[i];
			if((now>96 && now<123) || (now>47 && now<58)) {
				build.append(now);
			}
		}
		return build.toString().toCharArray();
	}
	
	public static String purify(String ad) {
		char[] c = ad.toCharArray();
		int l=c.length;
		String ret = "";
		for(int i=0;i<l;i++) {
			char cn = c[i];
			if(cn>96 && cn<123) {
				ret+=cn;
			}
		}
		return ret;
	}
	
	public static String parseEmote(String ad) {
		char[] c = ad.toCharArray();
		int l=c.length;
		String ret = "";
		for(int i=0;i<l;i++) {
			char cn = c[i];
			if(cn!=':') {
				ret+=cn;
			}
		}
		return ret;
	}
	
	public static boolean contemoji(String ad) {
		char[] c = ad.toCharArray();
		int l=c.length;
		int count = 0;
		for(int i=0;i<l;i++) {
			if(c[i]==':') {
				count++;
			}
		}
		return (count==2);
	}
	
	public static String getPrintedEmote(Emote ji) {
		return "<:"+ji.getName()+":"+ji.getIdLong()+">";
	}
	
	public static double getvalue(String ad) {
		char[] c = ad.toCharArray();
		int l=c.length;
		double v = 0;
		for(int i=0;i<l;i++) {
			v += (c[i]-97.0+1) * Math.pow(1.6143811024741887, i);
		}
		//System.out.println(v%1);
		return v%1;
	}
	
	public static int rando(int l, int h) {
		return (int)(Math.random()*(h-l+1)+l);
	}
	
}

/*ApplicationManager am = new ApplicationManager(new ApplicationImpl(jda, new JSONObject("{  \r\n" + 
"   \"bot_public\":false,\r\n" + 
"   \"bot_require_code_grant\":false,\r\n" + 
"   \"description\":\"keeps track of ur emojis... and maybe other stuff...\",\r\n" + 
"   \"flags\":2,\r\n" + 
"   \"id\":288556438576431114,\r\n" + 
"   \"name\":\"Emoji Manager\",\r\n" + 
"   \"redirect_uris\":[  \r\n" + 
"\r\n" + 
"   ],\r\n" + 
"   \"rpc_application_state\":0,\r\n" + 
"   \"secret\":\"qWbF9svui9X28C_dzb0zayJICLNHaLOU\"\r\n" + 
"}") ));
am.setDescription("generated description by robots seeking world destruction");
try {
am.setIcon(Icon.from(new File("C:\\Users\\Emmett\\Desktop\\dump\\383215.png")));
}catch(Exception ex) {
ex.printStackTrace();
}
am.setName("FFFFFFFFFF");
System.out.println(am.getApplication().getName());*/