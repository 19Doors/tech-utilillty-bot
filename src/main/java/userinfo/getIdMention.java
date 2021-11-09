package userinfo;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class getIdMention {
    public String idFromMention(MessageReceivedEvent event, User user){
        String id = user.getId();
        return id;
    }
}
