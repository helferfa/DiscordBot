package commands.utility;

import commands.Command;
import core.permsCore;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdPing implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if (permsCore.check(event) < 1)
            return;

        event.getTextChannel().sendMessage("Pong!").queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFO] Command '-ping' wurde ausgefuehrt!");
    }

    @Override
    public String help() {
        return null;
    }
}
