
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.github.jreddit.entity.Submission;
import com.github.jreddit.entity.User;
import com.github.jreddit.retrieval.Submissions;
import com.github.jreddit.retrieval.params.SubmissionSort;
import com.github.jreddit.utils.restclient.HttpRestClient;
import com.github.jreddit.utils.restclient.RestClient;
import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.PaginatedIterator;
import com.kttdevelopment.mal4j.anime.AnimePreview;

import jdk.nio.Channels;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Commands extends ListenerAdapter {

	@SuppressWarnings("unchecked")
	public void onMessageReceived( MessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();
		String UserSent  = event.getAuthor().getAsMention();
		String[] messageSent1 = event.getMessage().getContentRaw().split("\\s+");

		if(messageSent.equalsIgnoreCase(main.prefix + "random gif")){
			try {
				ArrayList<String> dasioj = new ArrayList<String>();
				int NUMOFGIFS = 0;
				File iodjas = new File("C:\\Users\\Administrator\\Downloads\\DiscordBot\\DiscordBot\\src\\main\\java\\ListOfGifs.txt");
				Scanner obj = new Scanner(iodjas);
				while (obj.hasNextLine()) {
					String line = obj.nextLine();
					dasioj.add(line);
					NUMOFGIFS++;
				}
				obj.close();
				int a = (int)(Math.random() * NUMOFGIFS);
				String output = dasioj.get(a);
				event.getChannel().sendMessage(output).queue();

			} catch (FileNotFoundException e){
				event.getChannel().sendMessage("Ahmed Fix your code").queue();
			}
			return;



		}
		if(messageSent.equalsIgnoreCase(main.prefix + "help")) {
			event.getChannel().sendMessage("PREFIX: ~\r\n"
					+ "\r\n"
					+ "~random gif --> generates a random gif and uploads it to the given chat\r\n"
					+ "\r\n"
					+ "~who is king --> tells User who is king\r\n"
					+ "\r\n"
					+ "~ping --> play ping pong :)\r\n"
					+ "\r\n"
					+ "~pong --> play ping pong :)\r\n"
					+ "\r\n"
					+ "~rockpaperscissors --> generates a random selection from rock, paper and scissors\r\n"
					+ "\r\n"
					+ "~truth (QUESTION HERE) --> answers any given yes or no question\r\n"
					+ "\r\n"
					+ "~schedule (NUMBER) (UNIT(IN PLURAL FORM)) (NAME OF TASK) --> sets a reminder to be displayed of given task once the time given has been elapsed\r\n"
					+ "\r\n"
					+ "~anime (NAME OF ANIME) --> searchs given anime and returns it\r\n"
					+ "\r\n"
					+"~roll (MIN) (MAX)  --> rolls a number between the min and max number indicated\r\n"
					+ "\r\n"
					+"~cookie (MENTION USER) --> gives cookie to the mentioned user\r\n"
					+"\r\n"
					+"~ship (MENTION USER1) (MENTION USER2) --> gives the ship percentage of the two users\r\n"
					+"\r\n"
					+"~pfp --> returns the pfp of the user\r\n"
					+"\r\n"
					+"~flip --> flips a coin\r\n"
					+"\r\n"
					+"~confused --> returns a man being confused meme\r\n"
					+"\r\n"
					+"~greeting (MENTION USER) --> returns a greeting from author to user mentions\r\n"
					+"\r\n"
					+"~reddit (SUBREDDIT) --> returns the top post of the given subreddit: subreddit name given must be exact to desired subreddit\r\n"
					+"\r\n"
					+"~delete (AMOUNT) --> deletes the (AMOUNT) of most recent messages. Must be between 1-100 messages\r\n"
					+"\r\n"
					+"~meme --> Sends a meme in the chat\r\n"
					).queue();
			return;
		}
		if(messageSent.equalsIgnoreCase(main.prefix + "Who is king")) {
			event.getChannel().sendMessage( UserSent + " is!").queue();
			return;
		}
		if(messageSent1[0].equalsIgnoreCase(main.prefix + "random") && event.getAuthor().getId().compareTo("743094279488012369") == 0){
			try {
				int x = 0;
				String y = messageSent1[1];
				int input = Integer.parseInt(y);
				while (x < input) {
					int z = (int) (Math.random() * 100);
					event.getChannel().sendMessage("" + z).queue();
					x++;
				}
			} catch (ArrayIndexOutOfBoundsException E){
				event.getChannel().sendMessage("Wrong format of random command invoked, please type is this format: ~random NUM_OF_TIMES_TO_INVOKE").queue();
			} finally {
				return;
			}
		}
		

		if(messageSent1[0].equalsIgnoreCase(main.prefix+ "delete")) {
			if (messageSent1.length < 2) {
				EmbedBuilder error1 = new EmbedBuilder();
				error1.setColor(Color.RED);
				error1.setTitle("Specify # of messages to delete!");
				error1.setDescription("Usage: " + main.prefix + "delete [# of messages]");
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessageEmbeds(error1.build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
			}

			else if(Integer.parseInt(messageSent1[1]) > 100 || Integer.parseInt(messageSent1[1]) < 1) {
				EmbedBuilder error2 = new EmbedBuilder();
				error2.setColor(Color.RED);
				error2.setTitle( "Only 1-100 messages can be deleted!");
				error2.setDescription("Usage: " + main.prefix + "delete [1-100]");
				event.getChannel().sendMessageEmbeds(error2.build()).queue(m -> 
				m.delete().queueAfter(5, TimeUnit.SECONDS));
			}

			else if(Integer.parseInt(messageSent1[1]) <= 100 && Integer.parseInt(messageSent1[1]) >= 1){
				int values = Integer.parseInt(messageSent1[1]) + 1 ;
				event.getMessage().delete();
				List<Message> messages = event.getChannel().getHistory().retrievePast(values).complete();
				event.getTextChannel().deleteMessages(messages).queue();
				event.getChannel().sendMessage( messageSent1[1].toString() + " messages deleted!").queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
			}

		}


		if(messageSent.equalsIgnoreCase(main.prefix + "ping")) {
			event.getChannel().sendMessage("pong").queue();
			return; 

		}

		if(messageSent.equalsIgnoreCase(main.prefix + "pong")) {
			event.getChannel().sendMessage("ping").queue();
			return; 


		}
		

		if(messageSent1[0].equalsIgnoreCase(main.prefix + "anime")){
			if(messageSent1.length >= 2) {
				String a = "";
				for(int  counter = 1;counter < messageSent1.length;counter++) {
					a+= messageSent1[counter] +" ";
				}
				MyAnimeList mal = MyAnimeList.withClientID("e41c3970fe73a7475e9f7ea0cd2c335f");
				PaginatedIterator<AnimePreview> search =
						mal.getAnime()
						.withQuery(a)
						.withLimit(10)
						.withOffset(20)
						.includeNSFW(false)
						.searchAll();
				long z = search.next().getID();
				event.getChannel().sendMessage("https://myanimelist.net/anime/" + z).queue();
			}
			return;
		}
		
		
		
		if(messageSent.equalsIgnoreCase(main.prefix + "rockpaperscissors")) {
			double x = Math.random();
			if(x > 0.0 && x < 0.33) {
				event.getChannel().sendMessage("rock").queue();
			}else if(x > 0.33 && x < 0.66) {
				event.getChannel().sendMessage("paper").queue();
			} else if(x > 0.66 && x < 1.0) {
				event.getChannel().sendMessage("scissors").queue();
			}
			return;
		}
		if(messageSent1[0].equalsIgnoreCase(main.prefix + "roll")) {
			if(messageSent1.length < 2) {
				event.getChannel().sendMessage("Please enter the max and min roll. In this format: ~roll MIN MAX");
				return;
			}
			int x = (int) (Math.random() * (Integer.parseInt(messageSent1[2]) + 1)) + Integer.parseInt(messageSent1[1]);
			event.getChannel().sendMessage("" + x).queue();
			return;
		}
		if(messageSent.equalsIgnoreCase(main.prefix + "who is our savior")) {
			event.getChannel().sendMessage("pinkguy" + "\n" + "https://tenor.com/view/thumbs-up-okay-good-job-gif-9207424").queue();
			return;
		}
		if(messageSent1[0].equalsIgnoreCase(main.prefix + "cookie")) {
			if(messageSent1.length == 2) {
				event.getChannel().sendMessage(UserSent + " gave " + messageSent1[1] + " a cookie :cookie:").queue();
				return;
			}else {
				event.getChannel().sendMessage("Wrong format: ~cookie PING_USER").queue();
				return;
			}}

		if(messageSent1[0].equalsIgnoreCase(main.prefix + "truth")) {
			String [] x = {"That appears to be likely", "Defintely", "Ask again later", "That will never happen", "Not the right time to ask", "Yes", "No"};
			int y = (int) (Math.random() * x.length);
			event.getChannel().sendMessage("" + x[y]).queue();
			return;
		}
		 if(messageSent.equalsIgnoreCase(main.prefix + "meme")) {
				RestClient restClient = new HttpRestClient();
				restClient.setUserAgent("bot/1.0 by name");
				User user = new User(restClient, "username", "password");
				try {
					user.connect();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Submissions subms = new Submissions(restClient, user);

				List<Submission> submissionsSubreddit = subms.ofSubreddit("memes", SubmissionSort.TOP, -1, 100, null, null, true);
				String x  = submissionsSubreddit.get((int)(Math.random() * 99)).getURL();
				event.getChannel().sendMessage(x).queue();
				return;
			} 
		if(messageSent1[0].equalsIgnoreCase(main.prefix + "pfp")) {
			if(messageSent1.length == 1)
				event.getChannel().sendMessage("" + event.getAuthor().getAvatarUrl()).queue();
			return; 
		}
		if(messageSent.equalsIgnoreCase(main.prefix + "flip")) {
			int x = (int)(Math.random() * 2) + 1;
			if(x == 1) {
				event.getChannel().sendMessage("Tails was rolled").queue();
				return; 
			}
			event.getChannel().sendMessage("Heads was rolled").queue();
			return; 
		}
		if(messageSent1[0].equalsIgnoreCase(main.prefix + "ship")) {
			if(messageSent1.length == 3) {
				int x = (int)((Math.random()* 100)+ 1);
				event.getChannel().sendMessage(":heart_on_fire: " + messageSent1[1] + " has a " + x + "% chance of being with " + messageSent1[2] + ":heart_on_fire:" ).queue();
				return;
			}
		}
		if(messageSent.equalsIgnoreCase(main.prefix + "confused")) {
			event.getChannel().sendMessage("https://tenor.com/view/what-nick-young-meme-huh-confused-gif-16147126").queue();
			return;
		}
		if(messageSent1[0].equalsIgnoreCase(main.prefix + "greeting")) {
			if(messageSent1.length == 2)
				event.getChannel().sendMessage(UserSent + "greets you " + messageSent1[1] +  " :wave:").queue();
			return; 
		}
		 if(messageSent1[0].equalsIgnoreCase(main.prefix + "reddit")) {
			if(messageSent1.length  == 2) {
				RestClient restClient = new HttpRestClient();
				restClient.setUserAgent("bot/1.0 by name");
				User user = new User(restClient, "username", "password");
				try {
					user.connect();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Submissions subms = new Submissions(restClient, user);

				List<Submission> submissionsSubreddit = subms.ofSubreddit(messageSent1[1], SubmissionSort.TOP, -1, 100, null, null, true);
				String x  = submissionsSubreddit.get(0).getPermalink();
				event.getChannel().sendMessage("https://reddit.com" + x).queue();
			}} 


		if (messageSent1[0].equalsIgnoreCase(main.prefix + "schedule"))
			if (messageSent1.length >= 4){
				String h = messageSent1[1];
				String f = messageSent1[2];
				String q = " ";
				for(int z = 3; z < messageSent1.length; z++) {
					q += messageSent1[z] + " ";
				}
				final String hjj = q;
				int x = Integer.parseInt(h);
				Timer thetime = new Timer();
				TimerTask thetask = new TimerTask() {
					public void run() {
						event.getChannel().sendMessage("TIMER:" + hjj).queue();
						return;
					}
				};
				event.getChannel().sendMessage("Event has been scheduled").queue();
				if (f.equalsIgnoreCase("seconds") || f.equalsIgnoreCase("second")) {

					thetime.schedule(thetask, TimeUnit.SECONDS.toMillis(x));


				} else if (f.equalsIgnoreCase("minutes") || f.equalsIgnoreCase("minute")) {
					thetime.schedule(thetask, TimeUnit.MINUTES.toMillis(x));
				} else if (f.equalsIgnoreCase("hours")|| f.equalsIgnoreCase("hour")) {
					thetime.schedule(thetask, TimeUnit.HOURS.toMillis(x));
				} else if (f.equalsIgnoreCase("days")||f.equalsIgnoreCase("day")) {
					thetime.schedule(thetask, TimeUnit.DAYS.toMillis(x));

				} else {
					event.getChannel().sendMessage("Please envoke method in proper format. FORMAT: ~schedule QUANTITY UNIT EVENTNAME." + "\n" + "Example: ~schedule 10 days Party!");
					return;
				}


			}}}







