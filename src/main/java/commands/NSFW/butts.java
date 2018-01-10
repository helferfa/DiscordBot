package commands.NSFW;

import api.JsonSimple;
import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class butts implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        try {
            String json = JsonSimple.call_me("oButts", "default");
            event.getTextChannel().sendMessage(json).queue();
        } catch (Exception e) {
            e.printStackTrace();
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
