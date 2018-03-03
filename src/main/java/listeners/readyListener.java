package listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATIC;

import java.util.Date;

/**
 * © zekro 2017
 *
 * @author zekro
 */

public class readyListener extends ListenerAdapter {

    public void onReady(ReadyEvent event) {
        System.out.printf("\n Bot-Version; " + STATIC.VERSION + "\n");
        String out = "\nThis bot is running on following servers: \n";

        for (Guild g : event.getJDA().getGuilds() ) {
            out += g.getName() + " (" + g.getId() + ") \n";
        }
        System.out.print(new Date().toString());
        System.out.println(out);

        /**
         * Log for Discord Bot-Süam Channel
         * content:
         */
        for (Guild g : event.getJDA().getGuilds() ) {
            g.getTextChannelsByName("bot-spam", false).get(0).sendMessage(new MessageBuilder()

                    .setEmbed(new EmbedBuilder()
                            .setTitle("New Sessions Started")
                            //.setDescription("```[main] INFO net.dv8tion.jda.core.JDA - Login Successful! \n[JDA MainWS-WriteThread] INFO net.dv8tion.jda.core.requests.WebSocketClient - Connected to WebSocket[JDA MainWS-ReadThread] INFO net.dv8tion.jda.core.JDA - Finished Loading! \nBot-Version; 0.62```")
                            .setDescription("Compiled")
                            .setFooter("\uD83D\uDD51 " + new Date().toString(), "https://images-na.ssl-images-amazon.com/images/I/312lQlQGbdL._SX355_.jpg")
                            //.setThumbnail("https://images-na.ssl-images-amazon.com/images/I/312lQlQGbdL._SX355_.jpg")
                            .build())
                    .build()).queue();
        }
        for (TextChannel t : event.getJDA().getGuilds().get(0).getTextChannels()
             ) {
            System.out.println(t.toString());
        }




    }

}