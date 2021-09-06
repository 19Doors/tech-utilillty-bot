package moderation;

import java.util.ArrayList;
import java.util.List;

import CommandManager.Comm;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class createChannel extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
            String al[] = {"createch"};
            Comm com = new Comm("createChannel", al, "creates channel for you", event);
            List<Permission> ls = new ArrayList<Permission>();
            ls.add(Permission.MANAGE_CHANNEL);
            if(com.checkCondition(ls)){
                    String ab[] = event.getMessage().getContentRaw().split(" ");
                    Member member = event.getMember();
                    Guild guild = member.getGuild();
                    guild.createTextChannel(ab[1]).queue();
                    event.getChannel().sendMessage("Channel **"+ab[1]+"** created").queue();
            }
        
        }
    }


