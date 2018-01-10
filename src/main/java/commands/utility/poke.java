package commands.utility;

import commands.Command;
import core.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import util.STATIC;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;

import java.awt.*;
import java.util.Arrays;

public class poke implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if (permsCore.check(event) < 1)
            return;

        String message = String.join(" ", args).split("@")[0];
        User memb = event.getMessage().getMentionedUsers().size() > 0 ? event.getMessage().getMentionedUsers().get(0) : null;
        User author = event.getAuthor();
        TextChannel chan = event.getTextChannel();

        if (args.length < 2 || memb == null) {
            chan.sendMessage(new EmbedBuilder().setColor(Color.red).setDescription(help()).build()).queue();
            return;
        }

        if (args.length > 0) {
            if (args[0].startsWith("@")) {
                if (!event.getGuild().isMember(event.getMessage().getMentionedUsers().get(0))) {
                    event.getTextChannel().sendMessage(
                            ":warning:   This user is not a member of this guild!"
                    ).queue();
                }
                if (args.length > 1) {
                    message += "\n\n:love_letter:  Nachicht: \"";
                    for (String s : Arrays.asList(args).subList(1, args.length)) {
                        message += s + " ";
                    }
                    message += "\"";
                }
                PrivateChannel pc = event.getMessage().getMentionedUsers().get(0).openPrivateChannel().complete();
                pc.sendMessage(
                        ":point_right:  **You got poked by " + event.getAuthor().getAsMention() + ")" + message
                ).queue();
                event.getMessage().delete().queue();
            }
        } else
            event.getTextChannel().sendMessage(
                    ":warning:   Please mention a user from this guild you want to send a nudge!"
            ).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "something went wrong";
    }
}
