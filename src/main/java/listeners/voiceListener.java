package listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.OnlineStatus;

import net.dv8tion.jda.core.events.StatusChangeEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;


import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Date;

/**
 * © zekro 2017
 *
 * @author zekro
 */

public class voiceListener extends ListenerAdapter {

    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        event.getGuild().getTextChannelsByName("server-log", true).get(0).sendMessage(
                "``` Member " + event.getVoiceState().getMember().getUser().getName() + " joined voice channel " + event.getChannelJoined().getName() + ". ```"
        ).queue();
    }

    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        event.getGuild().getTextChannelsByName("server-log", true).get(0).sendMessage(
                "``` Member " + event.getVoiceState().getMember().getUser().getName() + " moved to voice channel " + event.getChannelJoined().getName() + ". ```").queue();

    }

    /*public void  onStatusChange(StatusChangeEvent e) {
        e.getJDA().getGuilds().get(0).getTextChannelsByName("bot-spam", true).get(0).sendMessage("Status Change: " + e.getNewStatus().name() + "from: " + e.getOldStatus().name()).queue();
    }*/


    //public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event)

    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {

        if(event.getNewOnlineStatus().equals(OnlineStatus.ONLINE)  /*&&  event.getOldOnlineStatus().equals(OnlineStatus.OFFLINE)*/) {

            event.getGuild().getTextChannelsByName("server-log", true).get(0).sendMessage(new MessageBuilder().setEmbed(new EmbedBuilder()
                    .setTitle("Status Change : ")
                    .setDescription("User " + event.getMember().getUser().getName() + " is now Online")
                    //.setThumbnail(event.getMember().getUser().getAvatarUrl())
                    .setFooter("\uD83D\uDD51 " + new Date().toString(), event.getMember().getUser().getAvatarUrl())
                    .build()).build()).queue();
        }
    }


}