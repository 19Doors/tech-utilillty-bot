package userinfo;

import java.nio.channels.Channel;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.requests.Route.Channels;

public class getIdMention {
    public String idFromMention(MessageReceivedEvent event, User user){
        String id = user.getId();
        return id;
    }
}
