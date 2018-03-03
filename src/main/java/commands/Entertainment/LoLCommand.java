package commands.Entertainment;

import api.LeagueApi;
import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class LoLCommand implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        LeagueApi lol = new LeagueApi();
        String name = "";
        for(int i = 0; i<args.length; i++) {
            name = name + args[i];
        }

        try {
            String[] infos = lol.profileMessage(name);
            event.getTextChannel().sendMessage(new MessageBuilder().setEmbed(new EmbedBuilder()
                    .setTitle("Profile: \"" + name + "\"", "https://discordapp.com")
                    .setDescription("Current Rank: " + infos[1] + " " + event.getGuild().getEmotesByName(infos[2], true).get(0).getAsMention() + " \nBest Champion: ")
                    .setColor(new Color(203556))
                    .setThumbnail("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/" + infos[3] + ".png")
                    .setImage("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg")
                    .build()).build()).queue();
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
