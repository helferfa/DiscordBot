

package core;

import commands.Entertainment.*;
import commands.NSFW.boobs;
import commands.NSFW.butts;
import commands.utility.*;
import listeners.messageListener;
import listeners.readyListener;
import listeners.voiceListener;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import util.SECRETS;
import java.io.*;
import java.util.Date;

import javax.security.auth.login.LoginException;


public class Main {

    private static JDABuilder builder;

    public static void main(String[] Args)
    {

        builder = new JDABuilder(AccountType.BOT);
        String s = "null";
        try {
            s = getToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        builder.setToken(s);
        builder.setAutoReconnect(true);

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setGame(Game.of(Game.GameType.DEFAULT, "!help"));
        /*builder.setGame(new Game() {
            @Override
            public String getName() {
                return "v." + STATIC.VERSION ;
            }

            @Override
            public String getUrl() {
                return null;
            }

            @Override
            public GameType getType() {
                return GameType.TWITCH;
            }
        });*/
        builder.setAudioEnabled(true);


        addListeners();
        addCommands();

        try {
            JDA jda = builder.buildBlocking();
        } catch (LoginException e) {
            System.out.print("[" + new Date().getHours() + ":" + new Date().getMinutes() + "] ");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.print("[" + new Date().getHours() + ":" + new Date().getMinutes() + "] ");
            e.printStackTrace();
        } /*catch (RateLimitedException e) {
            System.out.print("[" + new Date().getHours() + ":" + new Date().getMinutes() + "] ");
            e.printStackTrace();
        }*/

    }

    public static String getToken() throws IOException {
        FileReader fr = new FileReader("C:/Users/Fabian Helfer/OneDrive/Dokumente/FDiscord_Bot2/Token.txt");
        BufferedReader br = new BufferedReader(fr);
        String zeile1 = br.readLine();
        br.close();
        return zeile1;
    }

    /**
     * Fügt Commands mit Ref.String hinzu
     */
    private static void addCommands()
    {
        /*Utility*/
        commandHandler.commands.put("ping", new cmdPing());
        commandHandler.commands.put("h", new cmdHelp());
        commandHandler.commands.put("help", new cmdHelp());
        commandHandler.commands.put("list", new memberList());
        commandHandler.commands.put("poke", new poke());
        commandHandler.commands.put("clear", new Clear());
        commandHandler.commands.put("chat", new Chat());

        /*Music*/
        commandHandler.commands.put("music", new Music());
        commandHandler.commands.put("m", new Music());

        /*Entertainment*/
        commandHandler.commands.put("image", new cmd_getImageAPI());
        commandHandler.commands.put("img", new cmd_getImageAPI());
        commandHandler.commands.put("img2", new cmd_ImgMerge());
        commandHandler.commands.put("LoL", new LeagueProfile());
        commandHandler.commands.put("lol", new LoLCommand());

        /*NSFW*/
        commandHandler.commands.put("boobs", new boobs());
        commandHandler.commands.put("butts", new butts());

    }

    private static void addListeners() {
        builder.addEventListener(new messageListener());
        builder.addEventListener(new voiceListener());
        builder.addEventListener(new readyListener());
    }
}