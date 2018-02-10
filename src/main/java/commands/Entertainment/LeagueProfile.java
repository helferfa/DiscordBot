package commands.Entertainment;

import api.JsonSimple;
import com.sun.xml.internal.bind.v2.TODO;
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
                switch(args[0]) {
                    case "GameStat":
                        String jsongame = JsonSimple.call_me("GameStat", args[1]);
                        System.out.println(jsongame);

                        break;
                    case "LoL":
                        String json = JsonSimple.call_me("LoL", args[1]);
                        String[] s = json.split(" ");
                        //event.getTextChannel().getMessage().delete();
                        event.getTextChannel().sendMessage(new EmbedBuilder()
                                .setTitle("LoL Profile")
                                .setImage("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/" + s[0] + ".png")
                                .setTitle("Profile: freshddumb")
                                .build()
                        ).queue();
                        break;
                }
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
