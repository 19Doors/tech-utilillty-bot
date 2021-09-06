package fun;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class racereactionmsg extends ListenerAdapter {
    int x = 1;
    Message msg1;
    public racereactionmsg(Message msg) {
        msg1 = msg;
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event){
        if(x==1){
            x = 0;
            msg1.editMessage("Congrats "+event.getUser().getAsMention()).queue();
        }else{
            return;
        }
    }

}
