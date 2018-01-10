package commands.utility;

import commands.Command;
import core.permsCore;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class memberList implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if (permsCore.check(event) < 1)
            return;

        List memberlist = event.getTextChannel().getMembers();
        for (int i = 0; i<memberlist.size(); i++)
        {
            event.getTextChannel().sendMessage(memberlist.get(i).toString()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
