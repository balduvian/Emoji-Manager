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
			"phteven would lik it, ew",
			"prob emt's fav",
			"rate it how you choose",
			"pret gud",
			"almos as gud as :b:",
			"bes emoj evr",
			"i hav no opinion on this emoj",
			"ask ebmbt"
	};
	private int numemopinions = emopinions.length;
	
	private String[][][] things = {
		{{"emmett","emet","emmet","emett","emt","emmt","folderbeast","balduviandead","volderbeast","elderbeast","livethan","concern","molderbeast","folderbest"},{"He did a bad...","Bot.GOD","Heyyyyy is he onlin?","He has no friend but me","He spek franch","he protect, he attac, but most importantly he instruc","I hear hes in to weird shit"}},
		{{"clayton","clatab","clabtab","claytob","cltn","rewind","sploing","sploing1","mcrewind"},{"He a scrub","He creat the cod lan","He not as gud as Emmett","I'll 1v1 him","cut ur hair :b:","at least he didn creat sam:b:y"}},
		{{"java"},{"What i'm made of","Best programming language","infinitely better than swift"}},
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
		{{"kira","kb","kbbb","volderneast","vikinihil","thevolder"},{"she cam, and she lef","what a tragic story","she fears volders","sad that it caused the downfall of embebt","rip 473, rip emmet"}},
		{{"sarahbella","cerebella","emojimanager","emojibot","emmettsrobotgirlfriend","emmettswaifu"},{"that's me!"}},
		{{"samby","sambybot","sambot","sabbot","babbot","sabot"},{"my rival who i crush","I'm so much better than him","i'm soooooo much better than that piece of junk","kick off server now plz","rip 568","the war is on","better shut him off","i like it when he's oflin","i outpower him 50x","throw in the trash","made out of bad cod"}},
		{{"b"},{"best non custom emoji","i don't track that one",":b:"}},
		{{"bunny"},{" "}},
		{{"kek"},{"kek","get kek'd","*rip 473"}},
		{{"bot","bots","discord bots"},{"what bot?","i am one","i am the best on","do u mean that samby on?"}},
		{{"test"},{":b: don't test me"}},
		{{"568","rip568"},{"*473"}},
		{{"eclipse","eclipseneon","eclipseoxygen"},{"my factory","pret gud, better than that intellij shit"}},
		{{"intellij"},{"nop","pret shit, don use"}},
		{{"473","rip473"},{"rip"}},
		{{"gradle","maven","madle","graven","gravdle","mavden","gravlen"},{"nop","sux"}},
		{{"monstercat"},{"the best music","the only gud music","the onl tru music"}},
		{{"squigly"},{"i don't know, but i'm worried about what emmt thinks"}},
		{{"paintnet"},{"stil not as gud as photoshop","nah","only good for bevel and edge detec filter"}},
		{{"photoshop","ps","adobephotoshop","photshop","phtshp"},{"on par with ms paint","has some nice filters","used to create mor thonkers"}},
		{{"paint","mspaint","microsoftpaint"},{"best art program","has been used to creat great art","don't try and paint me in that..."}},
		{{"codeland","phtevenscodeland","codlan","codeserver","thecodeland"},{"best server","Emt's only server... give him some help","best place for cod"}},
		{{"r34","rule34","rulethirtyfour","nsfw","notsafeforwork","notsafe4work"},{"savior of mankind","what would i do without it","you'll prob find me in there..."}},
		{{"bluefemales","bluefemale","blufemale","bluefemlel","femlel","femlels","blufemlels"},{"inferior"}},
		{{"whitemale","whitemales","whitmle","whtml"},{"superior","btr than blu femlels","tolerable","em:b:t is on"}},
		{{"camelcase","canalcas","camalcas","camelcas","camlecas"},{"seems annoying to write..."}},
	};
	private int numthings = things.length;
	
	public OpinionCommand() {
		super("opinion",false);
	}
	
	public void subRun() throws CommandArgumentException{
		try {
			String potentialName = getArgument(0);
			if(contemoji(potentialName)) {
				Emote ji = shard.emotes.get(findEmote(parseemoji(potentialName)));
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
