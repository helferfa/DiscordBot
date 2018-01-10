package core;

import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.STATIC;

import java.util.Arrays;

public class permsCore {

    public static int check(MessageReceivedEvent event)
    {
        for ( Role r : event.getGuild().getMember(event.getAuthor()).getRoles() ) {
             if (Arrays.stream(STATIC.FULLPERMS).parallel().anyMatch(r.getName()::contains))
                return 3;
            else if (Arrays.stream(STATIC.MOREPERMS).parallel().anyMatch(r.getName()::contains))
                return 2;
            else if (Arrays.stream(STATIC.PERMS).parallel().anyMatch(r.getName()::contains))
                return 1;
            else
                event.getTextChannel().sendMessage(":warning:  Sorry, " + event.getAuthor().getAsMention() + ", you don't have the permissions to use this command!").queue();
        }
        return 0;
    }

    public static int checkmore(MessageReceivedEvent event)
    {
        for ( Role r : event.getGuild().getMember(event.getAuthor()).getRoles() ) {
            if (Arrays.stream(STATIC.FULLPERMS).parallel().anyMatch(r.getName()::contains))
                return 3;
            else if (Arrays.stream(STATIC.MOREPERMS).parallel().anyMatch(r.getName()::contains))
                return 2;
            else if (Arrays.stream(STATIC.PERMS).parallel().anyMatch(r.getName()::contains))
                event.getTextChannel().sendMessage(":warning:  Sorry, " + event.getAuthor().getAsMention() + ", you don't have the permissions to use this command!").queue();
            else
                event.getTextChannel().sendMessage(":warning:  Sorry, " + event.getAuthor().getAsMention() + ", you don't have the permissions to use this command!").queue();
        }
        return 0;
    }
}
