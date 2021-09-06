package moderation;

import java.util.ArrayList;
import java.util.List;


import CommandManager.Comm;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.managers.ChannelManagerImpl;

public class slowmode extends ListenerAdapter{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"setsm"};
        List<Permission> ls = new ArrayList<Permission>();
        ls.add(Permission.MANAGE_CHANNEL);
        Comm com = new Comm("setslowmode", al, "```You can use <Prefix>setsm or setslowmode <numberInSeconds>```", event);
        String a[] = event.getMessage().getContentRaw().split(" ");
        if(com.checkCondition(ls))
        { 
            TextChannel tc = event.getMessage().getTextChannel();
            GuildChannel gc = event.getGuild().getGuildChannelById(tc.getId());
            ChannelManagerImpl cm = new ChannelManagerImpl(gc);
            cm.setSlowmode(Integer.parseInt(a[1])).queue();
            tc.sendMessage("Slowmode set to " + a[1] + " seconds").queue();;
        }
    }
}
