

package core;

import commands.Entertainment.LeagueProfile;
import commands.Entertainment.Music;
import commands.Entertainment.cmd_ImgMerge;
import commands.Entertainment.cmd_getImageAPI;
import commands.NSFW.boobs;
import commands.NSFW.butts;
import commands.utility.*;
import listeners.messageListener;
import listeners.readyListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import util.SECRETS;
import

import javax.security.auth.login.LoginException;


public class Main {

    private static JDABuilder builder;

    public static void main(String[] Args)
    {

        builder = new JDABuilder(AccountType.BOT);

        builder.setToken(.TOKEN);
        builder.setAutoReconnect(true);

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setGame(Game.of("!help"));
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
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        }

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

        /*NSFW*/
        commandHandler.commands.put("boobs", new boobs());
        commandHandler.commands.put("butts", new butts());

    }

    private static void addListeners() {
        builder.addEventListener(new messageListener());
        //builder.addEventListener(new voiceListener());
        builder.addEventListener(new readyListener());
    }
}