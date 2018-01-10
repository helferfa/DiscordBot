package commands.utility;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.OffsetDateTime;

public class cmdHelp implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        /*event.getTextChannel().sendMessage( new EmbedBuilder().setColor(Color.RED).setDescription("")
                .addField("!ping", "!ping : Cmd zum Testen der Funktion des Bots", true).setTitle("Commands:")
                .addField("!list","!list : (Admin-use-only) Spammt den Channel zu mit allen Mitgliedern des Channels",false)
                .addField("!poke","!poke : Lässt sie jemanden aus dem Channel im Privat-Chat anstupsen -- Usage !poke @[User] [Text]",true)
                .addField("!music","!m/!music      Music Commands: \n \b     " +
                                                "p/play : Fügt einen Song der Playlist hinzu -- Usage !m p [Url/YT Search Terms] \n \b     " +
                                                "q/queue : Gibt aus welche Songs sich in der Queue befinden \n \b     " +
                                                "now/info : Gibt den aktuellen ong wieder \n \b     " +
                                                "s/stop/quit : Stoppt die Wiedergabe und verlässt den Channel, wenn dies nicht funktioniert benutzt quit, falls dies auch nicht funktioniert ist der Bot in einen Kritischen Fehler gelaufen und muss neu gestartet werden \\n \\b     \" +\n" +
                                                "shuffle : Shuffled die Songs in der Queue (might not work) \n \b     " +
                                                "[User Short] Startet die Persönliche Soundboard Wiedergabe -- Usage !m [User Short] [Sound Short] für die bessere/einfachere Nutzung: https://helferfa.bplaced.net/Discord/index.html", true)
                .addField("!clear","!clear : (Admin-use-only) Löscht eine bestimmte Menge an Nachrichten -- Usage !clear [Int: 1-100]",true)
                .addField("!image","!image : Postet ein Bild einer Website in den Channel -- Usage !image [Website] [Query]  Implementierte Websiten: 500px/Pinterest/oboobs  Bsp: !image 500px LoL",true)
                .addField("NSFW","Category NSFW: \n \b     " +
                                                "!boobs : Schickt ein Boob image von http://oboobs.ru in den Channel \n \b     " +
                                                "!butts : Shickt ein Butt image von http://obutts.ru in den Channel ",true)
                .build()).queue();*/


        event.getTextChannel().sendMessage(new MessageBuilder()
                .append("this `supports` __a__ **subset** *of* ~~markdown~~ 😃 ```js\nfunction foo(bar) {\n  console.log(bar);\n}\n\nfoo(1);```")
                .setEmbed(new EmbedBuilder()
                        .setTitle("title ~~(did you know you can have markdown here too?)~~")
                        .setColor(new Color(2213575))
                        .setTimestamp(OffsetDateTime.parse("2017-10-06T06:57:58.281Z").toLocalDateTime())
                        .setFooter("footer text", "https://cdn.discordapp.com/embed/avatars/0.png")
                        .setThumbnail("https://cdn.discordapp.com/embed/avatars/0.png")
                        .setImage("https://cdn.discordapp.com/embed/avatars/0.png")
                        .setAuthor("author name", "https://discordapp.com", "https://cdn.discordapp.com/embed/avatars/0.png")
                        .addField("🤔", "some of these properties have certain limits...", false)
                        .addField("😱", "try exceeding some of them!", false)
                        .addField("🙄", "an informative error should show up, and this view will remain as-is until all issues are fixed", false)



                        .addField("!ping", "!ping : Cmd zum Testen der Funktion des Bots", false).setTitle("Commands:")
                        .addField("!list","!list : (Admin-use-only) Spammt den Channel zu mit allen Mitgliedern des Channels",false)
                        .addField("!poke","!poke : Lässt sie jemanden aus dem Channel im Privat-Chat anstupsen -- Usage !poke @[User] [Text]",false)
                        .addField("!music","!m/!music      Music Commands: \n \b     " +
                                "p/play : Fügt einen Song der Playlist hinzu -- Usage !m p [Url/YT Search Terms] \n \b     " +
                                "q/queue : Gibt aus welche Songs sich in der Queue befinden \n \b     " +
                                "now/info : Gibt den aktuellen ong wieder \n \b     " +
                                "s/stop/quit : Stoppt die Wiedergabe und verlässt den Channel, wenn dies nicht funktioniert benutzt quit, falls dies auch nicht funktioniert ist der Bot in einen Kritischen Fehler gelaufen und muss neu gestartet werden \\n \\b     \" +\n" +
                                "shuffle : Shuffled die Songs in der Queue (might not work) \n \b     " +
                                "[User Short] Startet die Persönliche Soundboard Wiedergabe -- Usage !m [User Short] [Sound Short] für die bessere/einfachere Nutzung: https://helferfa.bplaced.net/Discord/index.html", true)
                        .addField("!clear","!clear : (Admin-use-only) Löscht eine bestimmte Menge an Nachrichten -- Usage !clear [Int: 1-100]",false)
                        .addField("!image","!image : Postet ein Bild einer Website in den Channel -- Usage !image [Website] [Query]  Implementierte Websiten: 500px/Pinterest/oboobs  Bsp: !image 500px LoL",true)
                        .addField("NSFW","Category NSFW: \n \b     " +
                                "!boobs : Schickt ein Boob image von http://oboobs.ru in den Channel \n \b     " +
                                "!butts : Shickt ein Butt image von http://obutts.ru in den Channel ",true)



                        .addField("<:thonkang:219069250692841473>", "these last two", true)
                        .addField("<:thonkang:219069250692841473>", "are inline fields", true)
                        .build())
                .build()).queue();
                //.setDescription("Mein Präfix ist '!',\n Befehle:   ping: testet ob der Bot da ist \n m: p/play um ein Lied hinzuzufügen \n\b stop um die Wiedergabe zu stoppen \n quit falls der Bot nicht mehr den Channel verlässt \n usw.").build()).queue();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
