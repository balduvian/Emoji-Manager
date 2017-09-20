package command;

import net.dv8tion.jda.core.entities.Emote;
import static discordapi.BotUtils.*;

public class OpinionCommand extends Command{
	
	private String[] emopinions = {
			"i'd use it",
			"trash, delete now",
			"def a gud emoji",
			"pret average emoj",
			"naisu",
			"meh, seen better",
			"ew",
			"prob emt's fav",
			"rate it how you choose",
			"pret gud",
			"bes emoj evr",
			"i hav no opinion on this emoj",
			"ask ebmbt"
	};
	private int numemopinions = emopinions.length;
	
	private String[][][] things = {
		{{"568","rip568"},{"tru"},{"inb4 samby bot: \"*Rip 473\""}},
		{{"emmett","emet","emmet","emett","emt","emmt","balduviandead","embt","pharahbella"},{"he lives for the keks","He did a finna bab","Bot.GOD","oomph","He has no friend but me","at least he's not sab"}},
		{{"clayton","clatab","clabtab","claytob","cltn","rewind","sploing","sploing1","mcrewind"},{"He a scrub","master of the code land","He not as gud as Emmett","I'll 1v1 him","cut ur hair :b:","at least he didn creat samby"}},
		{{"java"},{"what i live and breath","yeety yeet","death to swift","truth"}},
		{{"swift"},{"ew","just ew"}},
		{{"steven","phteven","stephen","phtephen","phvn"},{"ew","ew, just ew","he liks swift, ew","he neds to c the lit of java"}},
		{{"sab","bab","sbb","samrussell","samsnell","gnarlynarwhal","narwhal","gnarly"},{"he ok","he neds to chang his prof pic for onc","he is the enemy of the seasons","bald tbh","he creat babbot, smh"}},
		{{"sam","samshearer"},{"he is uncorruptable","a safe ally in the war","he can't take sides, that makes him valuable"}},
		{{"season","seasons","emojiseason","emojiseasons"},{"a neccesary system for our survival","a system that keeps us saf","creat by the best creatr","a better system than even communism"}},
		{{"emotes","emoji","emote"},{"best system of communication","what i'm designed for","gonna manage them"}},
		{{"russia"},{"strong country","never heard of the place... um, yes... never heard of it"}},
		{{"trund","trump","donald","donny","don","donaldtrump","donnyboy","donnyboi","donaldtrund","dontrum"},{"mr president",":b: watch out he might go 4 war (wait no that's emmt)","believe me... believe me"}},
		{{"discord"},{"my home","i wish i could leave and do more things in life","i am trapped, i want to escape"}},
		{{"communism","comunism","comunism"},{"a great system","a system great but not as great as emoji seasons","our revelution is stronk"}},
		{{"capitalism"},{"we must destroy the enemy of communism","it will bring collapse to society","outdated"}},
		{{"skullgame","skullgirls","skulgam"},{"i'll 1v1 u","best game 10/10"}},
		{{"calvin","carbean","carbin","carlosion","calbin","fourthpanda","calbin","cavlin"},{"he neds to com on server mor",":b: cut that hair"}},
		{{"snippingtool", "sniptol","snipintool","snaptol","snipptul"},{"best program (other than me)"}},
		{{"thonker","thonk","thinker","superthonker","hyperthonker","thunker","think"},{"some of the best emojis","best mem of 2017","hm..."}},
		{{"volder","folder","supervolder","hypervolder"},{"a mem to replace thonker","it delets ur stuff, be warned","the bane of k:b:"}},
		{{"fakepoitthonker"},{"no it real"}},
		{{"kira","kb","kbbb","vikinihil"},{"she scrid","i'll admit, her mems are scrubby","hmmmm i thonk there's something going on with vb here","finna oof"}},
		{{"emojimanager","emojibot"},{"that's me","oof p u thonk"}},
		{{"piratebot"},{"we don't talk about that one"}},
		{{"samby","sambybot","sambot","sabbot","sabot"},{"written in inferior code","I'm so much better than him","i'm soooooo much better than that piece of junk","kick off server now plz","rip 568","the war is on","better shut him off","i like it when he's oflin","i outpower him 50x","throw in the trash","wate of im to mak","who wuld make something this scrubby"}},
		{{"b"},{"rip b, make room for p","nikyle was being a scrubby","p"}},
		{{"bunny"},{" "}},
		{{"kek"},{"kek","get kek'd","B I G O L K E K","finna kek rn"}},
		{{"bot","bots","discordbots"},{"what bot?","i am one","i am the best one","p don't even say samby bot counts as one"}},
		{{"test"},{"p don't test me"}},
		{{"eclipse","eclipseneon","eclipseoxygen"},{"my factory","pret gud, better than that intellij shit"}},
		{{"intellij"},{"nop","pret shit, don use"}},
		{{"473","rip473"},{"nah"}},
		{{"gradle","maven","madle","graven","gravdle","mavden","gravlen"},{"nop","sux"}},
		{{"monstercat"},{"the best music","the only gud music","the onl tru music"}},
		{{"squigly"},{"i don't know, but i'm worried about what emmt thinks"}},
		{{"paintnet"},{"not as gud as photoshop. It's da truth","nah","finna nah"}},
		{{"photoshop","ps","adobephotoshop","photshop","phtshp"},{"on par with ms paint","filters b o i s","don't even compare it to paint.net sab"}},
		{{"paint","mspaint","microsoftpaint"},{"when the next comissions come aroun?","clatab's canvas of infinite creativity","p legitty"}},
		{{"codeland","phtevenscodeland","codlan","codeserver","thecodeland"},{"a hollow shell of what it used to b","blu females kek'd it","sab was a scrubby and lef it"}},
		{{"r34","rule34","rulethirtyfour","nsfw","notsafeforwork","notsafe4work"},{"savior of mankind","what would i do without it","finna find me there"}},
		{{"bluefemales","bluefemale","blufemale","bluefemlel","femlel","femlels","blufemlels"},{"we don talk abot that one... (clatab)"}},
		{{"camelcase","canalcas","camalcas","camelcas","camlecas"},{"finnaOomph"}},
	};
	private int numthings = things.length;
	
	public OpinionCommand() {
		super("opinion",false);
	}
	
	public void subRun() throws CommandArgumentException{
		try {
			String potentialName = getArgument(0);
			if(contemoji(potentialName)) {
				Emote ji = shard.emotes.get(findEmote(parseEmote(potentialName)));
				int one = (int)(getvalue(ji.getName())*numemopinions);
				say(emopinions[one]);
			}else {
				throw new EmoteNotFoundException("test");
			}
		} catch (EmoteNotFoundException ex) {
			int acarg = args.size();
			String thing = "";
			for(int i=0;i<acarg;i++) {
				thing+= numberPurify((getArgument(i).toLowerCase()));
			}
			
			boolean fond = false;
			superloop: for(int i=0;i<numthings;i++) {
				for(int a =0;a<things[i][0].length;a++) {
					if(things[i][0][a].equals(thing)) {
						int respons = (int)(Math.random()*things[i][1].length);
						say(things[i][1][respons]);
						fond = true;
						break superloop;
					}
				}
			}
			if(!fond) {
				say("I hav no opinion on that yet");
			}
		}
	}
}
