package prefix;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class prefix extends ListenerAdapter {
    @Override
    public void onGuildJoin(GuildJoinEvent event)
    {
        DB db = new DB("guildSettings");
        db.openDatabase();

        // Creating Table(Checks if it exists or not)
        String sql = "CREATE TABLE IF NOT EXISTS GuildSettings (" + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "gId TEXT," + "prefix TEXT);";

        db.executeUpdate(sql);

        sql = "INSERT INTO GuildSettings(gId, prefix) VALUES('" + event.getGuild().getId() + "', '$')";

        db.executeUpdate(sql);
        db.closeAll();
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event)
    {
        DB db = new DB("guildSettings");
        db.openDatabase();

        String sql = "DELETE FROM GuildSettings WHERE gId='" + event.getGuild().getId() + "';";

        db.executeUpdate(sql);

        sql = "DELETE FROM sqlite_sequence WHERE name='GuildSettings';";

        db.executeUpdate(sql);

        db.closeAll();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {   
        if(event.getMessage().getEmbeds().isEmpty())
        {
            

        if(getPrefix(event.getGuild()).equals(Character.toString(event.getMessage().getContentRaw().charAt(0))))
        {
            String wrds[] = event.getMessage().getContentRaw().substring(1).split(" ");
            if(wrds[0].equalsIgnoreCase("prefix"))
            {
                if(event.getMessage().getContentRaw().substring(1).equalsIgnoreCase("prefix"))
                {
                    event.getChannel().sendMessage(new EmbedBuilder().setTitle("Bot Settings").setDescription("Prefix is "+getPrefix(event.getGuild())).build()).queue();
                }
                else
                {
                    if(event.getGuild().getMemberById(event.getAuthor().getId()).hasPermission(net.dv8tion.jda.api.Permission.ADMINISTRATOR))
                    {

                    DB db = new DB("guildSettings");
                    db.openDatabase();
                    setPrefix(event.getGuild(), db,  wrds[1]);
                    event.getChannel().sendMessage(new EmbedBuilder().setTitle("Bot Settings").setDescription("Prefix Updated to: "+getPrefix(event.getGuild())).build()).queue();
                    db.closeAll();
                    }
                }
                
            }
        }
        }else{}
        }
        
    


    public String getPrefix(MessageReceivedEvent event, DB db) {

        Guild guild = event.getGuild();

        String sql = "SELECT prefix FROM GuildSettings WHERE gId='" + guild.getId() + "' AND prefix IS NOT NULL;";
        ResultSet rs;

        rs = db.executeQuery(sql);

        try {
            String r = rs.getString("prefix");
            return r;
            
        } catch (SQLException e) {
            System.out.println("Error creating resultSet");
        }

        return "";

        
    }

    public String getPrefix(Guild guild) {
        DB db = new DB("guildSettings");
        db.openDatabase();
        String sql = "SELECT prefix FROM GuildSettings WHERE gId='" + guild.getId() + "' AND prefix IS NOT NULL;";
        ResultSet rs;

        rs = db.executeQuery(sql);
        try {
            String prefixx = rs.getString("prefix");
            String prefix = prefixx;
            db.closeAll();
            return prefix;
        } catch (SQLException e) {
            System.out.println("Error creating resultSet");
        }

        

        return "lol";

    }

    public void setPrefix(Guild guild, DB db, String prefix) {

        String sql = "UPDATE GuildSettings SET prefix='" + prefix + "' WHERE gId='" + guild.getId() + "';";

        db.executeUpdate(sql);

    }
}
