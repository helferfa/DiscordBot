package listeners;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import core.commandHandler;
import util.STATIC;

public class messageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getMessage().getContent().startsWith("!") /*&& event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()*/) {
            commandHandler.handleCommand(commandHandler.parser.parse(event.getMessage().getContent(), event));
        }

    }

}