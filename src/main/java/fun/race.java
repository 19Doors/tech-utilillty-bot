package fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.function.Consumer;

import CommandManager.Comm;

public class race extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String al[] = {"reactionRace"};
        Comm com = new Comm("race", al, "This is for conduction reaction race", event);
        if(com.checkConditionNoArg()){
            event.getChannel().sendMessage("React here!!").queue(react12 -> {
                if(react12.getEmotes().isEmpty()) {
                    react12.getJDA().addEventListener(new racereactionmsg(react12));
                }
            });
        }
    }



}
