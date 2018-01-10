package commands.Entertainment;

import api.JsonSimple;
import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class LeagueProfile implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(args.length > 1) {
            try {
                String json = JsonSimple.call_me(args[0], args[1]);
                String[] s = json.split(" ");
                //event.getTextChannel().getMessage().delete();
                event.getTextChannel().sendMessage(new EmbedBuilder()
                        .setTitle("LoL Profile")
                        .setImage("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/"+ s[0] +".png")
                        .setTitle("Profile: freshddumb")
                        .build()
                ).queue();
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
