package commands.Entertainment;

import AudioCore.AudioInfo;
import AudioCore.PlayerSendHandler;
import AudioCore.TrackManager;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.STATIC;

import java.awt.Color;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zekro on 18.06.2017 / 11:47
 * supremeBot.commands
 * dev.zekro.de - github.zekro.de
 * © zekro 2017
 */

public class Music implements Command {


    private static final int PLAYLIST_LIMIT = 1000;
    private static Guild guild;
    private static final AudioPlayerManager MANAGER = new DefaultAudioPlayerManager();
    private static final Map<Guild, Map.Entry<AudioPlayer, TrackManager>> PLAYERS = new HashMap<>();


    /**
     * Audio Manager als Audio-Stream-Recource deklarieren.
     */
    public Music() {
        AudioSourceManagers.registerRemoteSources(MANAGER);
    }

    /**
     * Erstellt einen Audioplayer und fügt diesen in die PLAYERS-Map ein.
     * @param g Guild
     * @return AudioPlayer
     */
    private AudioPlayer createPlayer(Guild g) {
        AudioPlayer p = MANAGER.createPlayer();
        TrackManager m = new TrackManager(p);
        p.addListener(m);

        guild.getAudioManager().setSendingHandler(new PlayerSendHandler(p));

        PLAYERS.put(g, new AbstractMap.SimpleEntry<>(p, m));

        return p;
    }

    /**
     * Returnt, ob die Guild einen Eintrag in der PLAYERS-Map hat.
     * @param g Guild
     * @return Boolean
     */
    private boolean hasPlayer(Guild g) {
        return PLAYERS.containsKey(g);
    }

    /**
     * Returnt den momentanen Player der Guild aus der PLAYERS-Map,
     * oder erstellt einen neuen Player für die Guild.
     * @param g Guild
     * @return AudioPlayer
     */
    private AudioPlayer getPlayer(Guild g) {
        if (hasPlayer(g))
            return PLAYERS.get(g).getKey();
        else
            return createPlayer(g);
    }

    /**
     * Returnt den momentanen TrackManager der Guild aus der PLAYERS-Map.
     * @param g Guild
     * @return TrackManager
     */
    private TrackManager getManager(Guild g) {
        return PLAYERS.get(g).getValue();
    }

    /**
     * Returnt, ob die Guild einen Player hat oder ob der momentane Player
     * gerade einen Track spielt.
     * @param g Guild
     * @return Boolean
     */
    private boolean isIdle(Guild g) {
        return !hasPlayer(g) || getPlayer(g).getPlayingTrack() == null;
    }

    /**
     * Läd aus der URL oder dem Search String einen Track oder eine Playlist
     * in die Queue.
     * @param identifier URL oder Search String
     * @param author Member, der den Track / die Playlist eingereiht hat
     * @param msg Message des Contents
     */
    private void loadTrack(String identifier, Member author, Message msg) {

        Guild guild = author.getGuild();
        getPlayer(guild);

        MANAGER.setFrameBufferDuration(5000);
        MANAGER.loadItemOrdered(guild, identifier, new AudioLoadResultHandler() {

            @Override
            public void trackLoaded(AudioTrack track) {
                getManager(guild).queue(track, author);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                for (int i = 0; i < (playlist.getTracks().size() > PLAYLIST_LIMIT ? PLAYLIST_LIMIT : playlist.getTracks().size()); i++) {
                    getManager(guild).queue(playlist.getTracks().get(i), author);
                }
            }

            @Override
            public void noMatches() {
                guild.getTextChannelsByName("bot-spam", true).get(0).sendMessage("No matches found").queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                guild.getTextChannelsByName("bot-spam", true).get(0).sendMessage("load failed").queue();
                exception.printStackTrace();
            }
        });

    }

    /**
     * Stoppt den momentanen Track, worauf der nächste Track gespielt wird.
     * @param g Guild
     */
    private void skip(Guild g) {
        getPlayer(g).stopTrack();
    }

    /**
     * Erzeugt aus dem Timestamp in Millisekunden ein hh:mm:ss - Zeitformat.
     * @param milis Timestamp
     * @return Zeitformat
     */
    private String getTimestamp(long milis) {
        long seconds = milis / 1000;
        long hours = Math.floorDiv(seconds, 3600);
        seconds = seconds - (hours * 3600);
        long mins = Math.floorDiv(seconds, 60);
        seconds = seconds - (mins * 60);
        return (hours == 0 ? "" : hours + ":") + String.format("%02d", mins) + ":" + String.format("%02d", seconds);
    }

    /**
     * Returnt aus der AudioInfo eines Tracks die Informationen als String.
     * @param info AudioInfo
     * @return Informationen als String
     */
    private String buildQueueMessage(AudioInfo info) {
        AudioTrackInfo trackInfo = info.getTrack().getInfo();
        String title = trackInfo.title;
        long length = trackInfo.length;
        return "`[ " + getTimestamp(length) + " ]` " + title + "\n";
    }

    /**
     * Sendet eine Embed-Message in der Farbe Rot mit eingegebenen Content.
     * @param event MessageReceivedEvent
     * @param content Error Message Content
     */
    private void sendErrorMsg(MessageReceivedEvent event, String content) {
        event.getTextChannel().sendMessage(
                new EmbedBuilder()
                        .setColor(Color.red)
                        .setDescription(content)
                        .build()
        ).queue();
    }


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {


        List memberlist = event.getGuild().getTextChannelsByName("bot-chat", true).get(0).getMembers();
        List ownerlist = event.getGuild().getTextChannelsByName("bot-spam", true).get(0).getMembers();
        /*for (int i = 0; i<memberlist.size(); i++)
        {
            event.getTextChannel().sendMessage(memberlist.get(i).toString()).queue();
        }*/



        guild = event.getGuild();

        if (args.length < 1) {
            sendErrorMsg(event, help());
            return;
        }

        switch (args[0].toLowerCase()) {

            case "play":
            case "p":

                if (args.length < 2) {
                    sendErrorMsg(event, "Please enter a valid source!");
                    return;
                }

                String input = Arrays.stream(args).skip(1).map(s -> " " + s).collect(Collectors.joining()).substring(1);

                if (!(input.startsWith("http://") || input.startsWith("https://")))
                    input = "ytsearch: " + input;


                loadTrack(input, event.getMember(), event.getMessage());


                break;

               //soundboard

            case "w": soundboard(args[1], event.getMember(), event); break;
            case "f":
               soundboard(args[1], (Member) ownerlist.get(STATIC.FRESHDUMBLEDORE), event); break;
            case "d":
                soundboard(args[1], (Member) ownerlist.get(STATIC.DREAD), event); break;
            case "i":
                soundboard(args[1], (Member) ownerlist.get(STATIC.INDERTAT), event); break;
            case "h":
                soundboard(args[1], (Member) ownerlist.get(STATIC.HERAIM), event); break;
            case "e":
                soundboard(args[1], (Member) ownerlist.get(STATIC.DOCTORICAN), event); break;



            case "skip":
            case "s":

                if (isIdle(guild)) return;
                for (int i = (args.length > 1 ? Integer.parseInt(args[1]) : 1); i == 1; i--) {
                    skip(guild);
                }

                break;


            case "stop":

                if (isIdle(guild)) return;

                getManager(guild).purgeQueue();
                skip(guild);
                guild.getAudioManager().closeAudioConnection();

                break;

            case "quit":
                guild.getAudioManager().closeAudioConnection();
                break;


            case "shuffle":

                if (isIdle(guild)) return;
                getManager(guild).shuffleQueue();

                break;


            case "now":
            case "info":

                if (isIdle(guild)) return;

                AudioTrack track = getPlayer(guild).getPlayingTrack();
                AudioTrackInfo info = track.getInfo();

                event.getTextChannel().sendMessage(
                        new EmbedBuilder()
                                .setDescription("**CURRENT TRACK INFO:**")
                                .addField("Title", info.title, false)
                                .addField("Duration", "`[ " + getTimestamp(track.getPosition()) + "/ " + getTimestamp(track.getDuration()) + " ]`", false)
                                .addField("Author", info.author, false)
                                .build()
                ).queue();

                break;



            case "queue":

                if (isIdle(guild)) return;

                int sideNumb = args.length > 1 ? Integer.parseInt(args[1]) : 1;

                List<String> tracks = new ArrayList<>();
                List<String> trackSublist;

                getManager(guild).getQueue().forEach(audioInfo -> tracks.add(buildQueueMessage(audioInfo)));

                if (tracks.size() > 20)
                    trackSublist = tracks.subList((sideNumb-1)*20, (sideNumb-1)*20+20);
                else
                    trackSublist = tracks;

                String out = trackSublist.stream().collect(Collectors.joining("\n"));
                int sideNumbAll = tracks.size() >= 20 ? tracks.size() / 20 : 1;

                event.getTextChannel().sendMessage(
                        new EmbedBuilder()
                                .setDescription(
                                        "**CURRENT QUEUE:**\n" +
                                                "*[" + getManager(guild).getQueue().stream() + " Tracks | Side " + sideNumb + " / " + sideNumbAll + "]*" +
                                                out
                                )
                                .build()
                ).queue();


                break;
        }


    }

    public void soundboard(String command, Member cmdmember, MessageReceivedEvent e) {
        if(cmdmember.getVoiceState().getChannel() == null)
        {
            e.getTextChannel().sendMessage("No VoiceChannel Accessable for: " + cmdmember.toString()).queue();
        }
        else {
            switch (command) {
                case "earrape":
                    loadTrack("https://www.youtube.com/watch?v=6Joyj0dmkug", cmdmember, e.getMessage());
                    break;
                case "meddl":
                    loadTrack("https://www.youtube.com/watch?v=OKeEH2u3RGM", cmdmember, e.getMessage());
                    break;
                case "1":
                    loadTrack("https://www.youtube.com/watch?v=stlZEKoJg10", cmdmember, e.getMessage());
                    break;
                case "eiken":
                    loadTrack("https://www.youtube.com/watch?v=aY5vsMwJTYo", cmdmember, e.getMessage());
                    break;
                case "omae":
                    loadTrack("https://www.youtube.com/watch?v=8CJO29wXTwo", cmdmember, e.getMessage());
                    break;
                case "what":
                    loadTrack("https://youtu.be/ESuWnNW5H7o?list=PLPbYmF3GfbKhKZKdu551Lq776n9-Qsnlc", cmdmember, e.getMessage());
                    break;
                case "pussy":
                    loadTrack("https://youtu.be/JpaiLUq59r0?list=PLPbYmF3GfbKhKZKdu551Lq776n9-Qsnlc", cmdmember, e.getMessage());
                    break;
                case "allahu":
                    loadTrack("https://youtu.be/X-vUceJNQc8?list=PLPbYmF3GfbKhKZKdu551Lq776n9-Qsnlc", cmdmember, e.getMessage());
                    break;
                case "running":
                    loadTrack("https://youtu.be/MbvCS6GSA6Y?list=PLPbYmF3GfbKhKZKdu551Lq776n9-Qsnlc", cmdmember, e.getMessage());
                    break;
                case "heya":
                    loadTrack("https://youtu.be/ZZ5LpwO-An4?list=PLPbYmF3GfbKhKZKdu551Lq776n9-Qsnlc", cmdmember, e.getMessage());
                    break;
                case "porn":
                    loadTrack("https://youtu.be/NiFD6EFVsTg", cmdmember, e.getMessage());
                    break;
                case "wtf":
                    loadTrack("https://youtu.be/AXzEcwYs8Eo?list=PLPbYmF3GfbKhKZKdu551Lq776n9-Qsnlc", cmdmember, e.getMessage());
                    break;
                case "prettygood":
                    loadTrack("https://youtu.be/vjUqUVrXclE?list=PLPbYmF3GfbKhKZKdu551Lq776n9-Qsnlc", cmdmember, e.getMessage());
                    break;
                case "wtfparrot":
                    loadTrack("https://www.youtube.com/watch?v=fbfzahtZsN8", cmdmember, e.getMessage());
                    break;
                case "JohnCena":
                    loadTrack("https://youtu.be/Uufq_PFXbpA?list=PLTpBrFldxpIp8i-uODEZwW1Pej-BPvC2c", cmdmember, e.getMessage());
                    break;
                case "tunak":
                    loadTrack("https://youtu.be/vTIIMJ9tUc8?list=PLqrtG-YCnduTTHf_YnLjuWbtBpas8UiAp", cmdmember, e.getMessage());
                    break;
                case "allstar":
                    loadTrack("https://youtu.be/L_jWHffIx5E?list=PLqrtG-YCnduTTHf_YnLjuWbtBpas8UiAp", cmdmember, e.getMessage());
                    break;
                case "familiarfaces":
                    loadTrack("https://youtu.be/4N3N1MlvVc4?list=PLqrtG-YCnduTTHf_YnLjuWbtBpas8UiAp", cmdmember, e.getMessage());
                    break;
                case "ok":
                    loadTrack("https://youtu.be/oYtldnVOHc0", cmdmember, e.getMessage());
                    break;
                case "soka":
                    loadTrack("https://youtu.be/mcgIS0jHhEM", cmdmember, e.getMessage());
                    break;
                case "local":
                    loadTrack("C:\\Users\\helfe\\Music\\My Chemical Romance\\Three Cheers for Sweet Revenge\\02 Give 'Em Hell, Kid.mp3", cmdmember, e.getMessage());
                    break;
                /*case "5":
                    loadTrack("", cmdmember, e.getMessage());
                    break;
                case "5":
                    loadTrack("", cmdmember, e.getMessage());
                    break;
                case "5":
                    loadTrack("", cmdmember, e.getMessage());
                    break;
                case "5":
                    loadTrack("", cmdmember, e.getMessage());
                    break;
                case "5":
                    loadTrack("", cmdmember, e.getMessage());
                    break;
                case "5":
                    loadTrack("", cmdmember, e.getMessage());
                    break;*/
                case "stop":

                    if (isIdle(guild)) return;

                    getManager(guild).purgeQueue();
                    skip(guild);
                    guild.getAudioManager().closeAudioConnection();

                    break;
            }
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}