package fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

import CommandManager.Comm;

public class perTest extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String al[] = {"%"};
        Comm com = new Comm("%", al, "bro, write something in front of it!!", event);
        if(com.checkCondition()){
            
            String content = event.getMessage().getContentRaw();
            int calc = (int)(Math.random()*100);
            String a[] = content.split(" ");
            EmbedBuilder em = new EmbedBuilder();
            em.setTitle("How much you are??");
            String id = event.getAuthor().getId();
            em.setDescription("<@"+id+"> is "+calc+"% "+a[1]);
            em.setColor(025555);
            event.getChannel().sendMessage(em.build()).queue();
        }
    }
}
