package listeners;

import net.dv8tion.jda.core.events.StatusChangeEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * © zekro 2017
 *
 * @author zekro
 */

public class voiceListener extends ListenerAdapter {

    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        event.getGuild().getTextChannelsByName("server-log", true).get(0).sendMessage(
                "Member " + event.getVoiceState().getMember().getUser().getName() + " joined voice channel " + event.getChannelJoined().getName() + "."
        ).queue();
    }

    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        event.getGuild().getTextChannelsByName("server-log", true).get(0).sendMessage(
                "Member " + event.getVoiceState().getMember().getUser().getName() + " moved to voice channel " + event.getChannelJoined().getName() + ".").queue();

    }

    /*public void  onStatusChange(StatusChangeEvent e) {
        e.getJDA().getGuilds().get(0).getTextChannelsByName("bot-spam", true).get(0).sendMessage("Status Change: " + e.getStatus().name() + "from: " + e.getOldStatus().name()).queue();
    }*/

    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {

        event.getGuild().getTextChannelsByName("server-log", true).get(0).sendMessage("Status Change: " + "User: " + event.getMember().getUser().getName() + " is now " + event.getCurrentOnlineStatus()).queue();
    }

}