package commands.Entertainment;

import api.JsonSimple;
//import net.dv8tion.jda.core.EmbedBuilder;
import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

//import java.awt.*;

public class cmd_getImageAPI implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args[0].equals("help")) {
            event.getTextChannel().sendMessage(new MessageBuilder().setEmbed(new EmbedBuilder()
                    .setTitle("!img help")
                    .setDescription("Image Command Options")
                    .setColor(new Color(15779914))
                    .setThumbnail("https://images-na.ssl-images-amazon.com/images/I/312lQlQGbdL._SX355_.jpg")
                    .setAuthor("GLaDOS", null, null)
                    .addField("500px", "-help for Categories \n Options: [1-30]/{28}", false)
                    .addField("Pinterest", "only gives JSON Text at the moment", false)
                    .addField("LoL", "Should give you Profile and name of a League Profile", false)
                    .build())
                    .build()).queue();
        }
        else {
            if (args.length > 1) {
                try {
                    String json;
                    if(args[0].equals("500px") && (args[1].equals("x") || args[1].equals("random")))
                    {
                        int randomNum = 1 + (int)(Math.random() * 30);
                        json = JsonSimple.call_me(args[0], String.valueOf(randomNum));
                    } else {

                        json = JsonSimple.call_me(args[0], args[1]);
                        //event.getTextChannel().getMessage().delete();
                    }
                    event.getTextChannel().sendMessage(json).queue();
                } catch (Exception e) {
                    event.getTextChannel().sendMessage(":warning: Oops Something went wrong").queue();
                    e.printStackTrace();
                }
            } else {
                switch(args[0])
                {
                    case "500px": event.getTextChannel().sendMessage(new MessageBuilder().setEmbed(new EmbedBuilder()
                            .setTitle("!img 500px help")
                            .setDescription("500px Topic \n You can use both int and String for Topic Selection")
                            .setColor(new Color(15779914))
                            .setThumbnail("https://images-na.ssl-images-amazon.com/images/I/312lQlQGbdL._SX355_.jpg")
                            .setAuthor("GLaDOS", null, null)
                            .addField("1", "Celebrities", true)
                            .addField("2", "Film", true)
                            .addField("3", "Journalism", true)
                            .addField("4", "Nude", true)
                            .addField("5", "Black and White", true)
                            .addField("6", "Still Life", true)
                            .addField("7", "People", true)
                            .addField("8", "Landscapes", true)
                            .addField("9", "City and Architecture", true)
                            .addField("10", "Abstract", true)
                            .addField("11", "Animals", true)
                            .addField("12", "Macro", true)
                            .addField("13", "Travel", true)
                            .addField("14", "Fashion", true)
                            .addField("15", "Commercial", true)
                            .addField("16", "Concert", true)
                            .addField("17", "Sport", true)
                            .addField("18", "Nature", true)
                            .addField("19", "Performing Arts", true)
                            .addField("20", "Family", true)
                            .addField("21", "Street", true)
                            .addField("22", "Underwater", true)
                            .addField("23", "Food", true)
                            .addField("24", "Fine Art", true)
                            .addField("25", "Wedding", true)
                            .build())
                            .build()).queue(); break;
                    case "oBoobs": try {
                        String json = JsonSimple.call_me("oBoobs", "default");
                        event.getTextChannel().sendMessage(json).queue();
                    } catch (Exception e) {
                        event.getTextChannel().sendMessage(":warning: Oops Something went wrong").queue();
                        e.printStackTrace();
                    }break;
                    case "oButts": try {
                        String json = JsonSimple.call_me("oButts", "default");
                        event.getTextChannel().sendMessage(json).queue();
                    } catch (Exception e) {
                        event.getTextChannel().sendMessage(":warning: Oops Something went wrong").queue();
                        e.printStackTrace();
                    }break;
                }
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
