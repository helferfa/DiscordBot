package listeners;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATIC;

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

        System.out.println(out);

        for (Guild g : event.getJDA().getGuilds() ) {
            g.getTextChannelsByName("bot-spam", true).get(0).sendMessage(
                    "Compiled"
            ).queue();
        }
        for (TextChannel t : event.getJDA().getGuilds().get(0).getTextChannels()
             ) {
            System.out.println(t.toString());
        }


    }

}