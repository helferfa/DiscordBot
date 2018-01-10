package commands.Entertainment;

import api.JsonSimple;
//import net.dv8tion.jda.core.EmbedBuilder;
import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

//import java.awt.*;

public class cmdImage implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(args.length > 1) {
            try {
                String json = JsonSimple.call_me(args[0], args[1]);
                //event.getTextChannel().getMessage().delete();
                event.getTextChannel().sendMessage(json).queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
