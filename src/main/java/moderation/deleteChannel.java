package moderation;

import java.util.ArrayList;
import java.util.List;

import CommandManager.Comm;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class deleteChannel extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String aliases[] = {"deletech"};
        List<Permission> ls = new ArrayList<Permission>();
        ls.add(Permission.MANAGE_CHANNEL);
        Comm com = new Comm("deleteChannel", aliases, "deletes the channel for you", event);
        if(com.checkCondition(ls)){
            if (event.getMessage().getMentionedChannels().isEmpty()) {
                String a[] = event.getMessage().getContentRaw().split(" ");
                GuildChannel gc = event.getGuild().getGuildChannelById(a[1]);
                gc.delete().queue();
                event.getChannel().sendMessage("Channel deleted").queue();
            } else {
                TextChannel tcc = event.getMessage().getMentionedChannels().get(0);
                String ide = tcc.getId();
                GuildChannel gc = event.getGuild().getGuildChannelById(ide);
                gc.delete().queue();
                event.getChannel().sendMessage("Channel deleted").queue();
            }
        }
    }
}
