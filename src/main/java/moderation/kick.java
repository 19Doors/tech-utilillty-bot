package moderation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import CommandManager.Comm;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import userinfo.getIdMention;

public class kick extends ListenerAdapter{

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"kick"};
        List<Permission> ls = new ArrayList<Permission>();
        ls.add(Permission.KICK_MEMBERS);
        Comm com = new Comm("kick", al, "It will kick players!!", event);
        if(com.checkCondition(ls) && event.getMessage().isFromType(ChannelType.TEXT))
        {
            try
            {
                String wrds[] = event.getMessage().getContentRaw().split(" ");
            
            getIdMention gi = new getIdMention();
            if(event.getMessage().getMentionedMembers().isEmpty())
            {
                String id = wrds[1];
                String reason = "";

                if(event.getMessage().getContentRaw().split(" ").length==3)
                {
                    reason = wrds[2];
                }else{
                    reason = "No reason Provided";
                }

                final String reason1 = reason;

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Moderation Commands");
                eb.setDescription("Kicked "+event.getJDA().getUserById(id).getAsMention());
                eb.setFooter("Tech Utilities");
                Color color = new Color(66,252,128);
                eb.setColor(color);
                event.getChannel().sendMessage(eb.build()).queue();

                try{
                    User user = event.getJDA().getUserById(id);
                    user.openPrivateChannel().queue(ok -> {
                        ok.sendMessage("You were kicked out from "+event.getGuild().getName()+". Reason: "+reason1).queue();
                    });
                    event.getGuild().kick(event.getGuild().getMemberById(id), reason).queue();
                }catch(Exception e){
                    event.getChannel().sendMessage(new EmbedBuilder().setTitle("Moderation Commands").setDescription("That player does'nt exist!!").setColor(new Color(247, 45, 61)).setFooter("Tech Utilities").build()).queue();
                }
            }
            else
            {
                String id = gi.idFromMention(event, event.getMessage().getMentionedUsers().get(0));

                String reason = "";

                if(event.getMessage().getContentRaw().split(" ").length==3)
                {
                    reason = wrds[2];
                }else{
                    reason = "No reason Provided";
                }

                final String reason1 = reason;

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Moderation Commands");
                eb.setDescription("Kicked "+event.getJDA().getUserById(id).getAsMention());
                eb.setFooter("Tech Utilities");
                Color color = new Color(66,252,128);
                eb.setColor(color);
                event.getChannel().sendMessage(eb.build()).queue();

                try{
                    User user = event.getJDA().getUserById(id);
                    user.openPrivateChannel().queue(ok -> {
                        ok.sendMessage("You were kicked out from "+event.getGuild().getName()+". Reason: "+reason1).queue();
                    });
                    event.getGuild().kick(event.getGuild().getMemberById(id), reason).queue();
                }catch(Exception e){
                    event.getChannel().sendMessage(new EmbedBuilder().setTitle("Moderation Commands").setDescription("That player does'nt exist!!").setColor(new Color(247, 45, 61)).setFooter("Tech Utilities").build()).queue();
                }

            }
            }catch(Exception e){
                event.getChannel().sendMessage(new EmbedBuilder().setTitle("Moderation Commands").setDescription("You have entered the command in wrong way.Get help from the bot to know the command").setColor(new Color(247, 45, 61)).setFooter("Tech Utilities").build()).queue();
            }
            
        }
    }
    
}
