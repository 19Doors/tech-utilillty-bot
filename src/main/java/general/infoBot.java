package general;

import CommandManager.Comm;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class infoBot extends ListenerAdapter{

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"botinfo"};
        Comm com = new Comm("info", al, "It gives you info about the bot", event);
        if(com.checkConditionNoArg())
        {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Tech Utility Helps!!!");
            
            eb.setDescription("🔥**Thanks for requesting for Information!!** \n"+
            "🛑**Owner Commands \n**"+
            "1.Set Prefix for server with command prefix <urPrefix> \n"+
            "👮‍♂️**Moderation Commands** \n"+
            "1.Slowmode \n"+
            "2.Clear \n"+
            "3.CreateChannel \n"+
            "4.DeleteChannel \n"+
            "😎**General Commands** \n"+
            "1.Ping command \n"+
            "2.BotInvite command \n"+
            "😂**Fun Commands** \n"+
            "1.ReactionRace with race command \n"+
            "2.Percentage Tester with % command % <PercentageOF> \n");
            
            event.getAuthor().openPrivateChannel().queue(ene -> {ene.sendMessage(eb.build()).queue();});
        }
    }
}