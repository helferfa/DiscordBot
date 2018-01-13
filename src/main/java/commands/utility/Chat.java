package commands.utility;

import commands.Command;
import core.permsCore;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;


public class Chat implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    private void deletePerms(TextChannel t) {
        for (Role r : t.getGuild().getRoles()) {
            if (r.getName().equals("Mitglied") || r.getName().equals("Mod") || r.getName().equals("Admin")) {
                try {
                    if (t.getPermissionOverride(r) != null) {
                        t.getPermissionOverride(r).delete().queue();
                    }
                }
                catch (Exception e)
                {
                    t.sendMessage("Could'nt delete PermissionOverride \nReason:" + e.toString()).queue();
                }
            }
        }
        //t.sendMessage("Deleted All PermissionOverrides").queue();
    }

    private void disableWritePerm(TextChannel t) {
        for (Role r : t.getGuild().getRoles()) {
            if (r.getName().equals("Mitglied") || r.getName().equals("Mod") || r.getName().equals("Admin")) {
                t.createPermissionOverride(r).setDeny(Permission.MESSAGE_WRITE).queue();
                }
            }
        //t.sendMessage("Created All PermissionOverrides").queue();

        }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (permsCore.checkmore(event) < 2)
                return;
            if(!event.getTextChannel().getName().equals("meme") && !event.getTextChannel().getName().equals("nsfw") && !event.getTextChannel().getName().equals("testing")) {
                event.getTextChannel().sendMessage(":warning: Sorry this command is only for Usage in 'meme' & 'nsfw' Channel").queue();
                return;
            }
            if (event.getTextChannel().getName().equals("bot-spam")) {
                event.getTextChannel().sendMessage(":warning: Sorry, this Command cannot be used in this chat!\n:warning: I cant let you interfere with Permissions here!").queue();
            } else {
                if (args[0].equals("ban")) {
                    boolean permsOverride = false;
                    for (Role r : event.getGuild().getRoles()) {
                        if (r.getName().equals("Mitglied") || r.getName().equals("Mod") || r.getName().equals("Admin")) {
                            if (event.getTextChannel().getPermissionOverride(r) != null) {
                                permsOverride = true;
                                //event.getTextChannel().sendMessage("PermsOverride = true").queue();
                            }
                        }
                    }
                        if(permsOverride) {
                            deletePerms(event.getTextChannel());
                            event.getTextChannel().sendMessage("!chat ban").queue();
                        }
                        else
                        {
                            disableWritePerm(event.getTextChannel());
                            event.getTextChannel().sendMessage("Chat Restricted").queue();
                        }

                } else if (args[0].equals("free")) {
                    deletePerms(event.getTextChannel());
                    event.getTextChannel().sendMessage("Chat free").queue();

                }
                else {
                    event.getTextChannel().sendMessage("No Operation executed. --> args[0]: " + args[0]).queue();

                }
            }
        }
        catch (Exception e) {
            event.getTextChannel().sendMessage("There was an error: \n" + e.toString()).queue();
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
