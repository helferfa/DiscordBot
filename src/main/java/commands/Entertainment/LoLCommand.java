package commands.Entertainment;

import ImgCore.ImgMerge;
import api.LeagueApi;
import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.File;

public class LoLCommand implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        LeagueApi lol = new LeagueApi(event);
        String name = "";
        for (int i = 1; i < args.length; i++) {
            name = name + args[i];
        }

        try {
            switch (args[0]) {

                /**
                 *
                 */
                case "match":
                    ImgMerge img = new ImgMerge("ocmbine");
                    img.getChampionSkins(new LeagueApi(event).getChampionsFromGame(name));
                    img.createLeague();
                    event.getTextChannel().sendFile(new File("C:/Users/Fabian Helfer/OneDrive/Dokumente/DiscordBot2/src/assets/imgs/merged.jpg")).queue();
                    break;

                /**
                 *
                 */
                case "info":

                    /**Info String that contains all the Informations about the Summoner*/
                    String[] infos = lol.profileMessage(name);

                    /**
                     *  Sets the Best Champion Picture link
                     */
                    String urlchampPic;
                    if (infos[4].equals("ratelimitexceeded")) {
                        urlchampPic = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/1.jpg";
                    } else {
                        infos[4] = infos[4].replaceAll(" ", "").replaceAll("'", "");
                        urlchampPic = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + infos[4] + "_0.jpg";
                    }
                    System.out.println("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/splash/" + infos[4] + "_0.png");

                    /**
                     * Building the Message with the Rest of Informations
                     */
                    MessageEmbed m = new EmbedBuilder()
                            .setTitle("Profile: \"" + name + "\"", "https://discordapp.com")
                            .setDescription("Current Rank: " + infos[1] + " " + event.getGuild().getEmotesByName(infos[2], true).get(0).getAsMention() + " \nBest Champion: ")
                            .setColor(new Color(203556))
                            .setThumbnail("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/" + infos[3] + ".png")
                            .setImage(urlchampPic)
                            .build();
                    System.out.println(m.getThumbnail().toString());

                    event.getTextChannel().sendMessage(new MessageBuilder().setEmbed(m).build()).queue();

                    break;
            }

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
