package moderation;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import CommandManager.Comm;


public class clear extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"clearMessages", "clearmsg", "purge"};
        Comm com = new Comm("clear", al, "It will clear Messages for you.", event);
        List<Permission> ls = new ArrayList<Permission>(1);
        ls.add(Permission.MESSAGE_MANAGE);
        if(com.checkCondition(ls))
        {
            String a[] = event.getMessage().getContentRaw().split(" ");
            TextChannel txtchannel = event.getTextChannel();
            List<Message> messagesall = txtchannel.getHistory().retrievePast(Integer.parseInt(a[1])).complete();

            int lim = (messagesall.size() - Integer.parseInt(a[1]));
            int val = Integer.parseInt(a[1]);
                        
            if(val>0 && val<100){
                if(lim>0 || lim==0)
                {
                   
                   event.getTextChannel().deleteMessages(messagesall).queue();
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("Cleaning Job");
                    eb.setDescription("Cleared "+val+" messages!!");
                    eb.setFooter("Tech Utilities");
                    Color color = new Color(66,252,128);
                    eb.setColor(color);
                    event.getChannel().sendMessage(eb.build()).queue();
                    return;
                }
            }else{
                event.getChannel().sendMessage("Bruh, you cannot clear messages over 100 or messages less than  equal to 0");
            }
        }
    }
        
}
