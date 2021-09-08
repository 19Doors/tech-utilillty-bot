package tools;

import java.awt.*;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdhelp {

    //For Wrong Command
    public void wrongCommand(MessageReceivedEvent event, List<Field> ls)
    {
        EmbedBuilder em = new EmbedBuilder();
        em.setTitle("Command Helper");
        //Add Fields
        for(int i = 0; i<ls.size(); i++)
        {
            em.addField(ls.get(i));
        }
        em.setColor(new Color(74, 255, 225));
        em.setFooter("Tech Utilities");

        event.getChannel().sendMessage(em.build()).queue();
    }

}
