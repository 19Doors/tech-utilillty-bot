package general;

import java.util.*;
import CommandManager.Comm;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import prefix.prefix;

public class ping extends ListenerAdapter{
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String al[] = {"ping"};
        Comm com = new Comm("ping", al, "It will return you pong!", event);
        
        if(com.checkConditionNoArg()){
            MessageChannel channel = event.getChannel();
            channel.sendMessage("pong!").queue();
        }
    }
    

}
