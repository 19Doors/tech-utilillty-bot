package moderation;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;

import CommandManager.Comm;
import database.DB;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class autoRole extends ListenerAdapter{

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String al[] = {"ar"};
        List<Permission> ls = new ArrayList<Permission>();
        ls.add(Permission.MANAGE_ROLES);
        Comm com = new Comm("autorole", al, "It will assign roles to a player that joins the server!!", event);
        if(com.checkCondition(ls))
        {
            DB db = new DB("guildSettings");
            db.openDatabase();
            
            
            String sql =  "SELECT BRoles FROM GuildSettings";
        
            ResultSet rs = db.executeQuery(sql);
            try
            {
                rs.findColumn("BRoles");
            }catch(Exception e){
                sql = "ALTER TABLE GuildSettings ADD BRoles TEXT";
                db.executeUpdate(sql);
            }

            String wrds[] = event.getMessage().getContentRaw().split(" ");

            int fla = 0;
            if(wrds[1].equalsIgnoreCase("add"))
            {
                try{sql = "SELECT * FROM GuildSettings WHERE BRoles='"+event.getGuild().getRoleById(wrds[2])+"';";}catch(Exception e123){
                    event.getChannel().sendMessage(new EmbedBuilder().setTitle("AutoRoles Section").setDescription("🔥Thanks for requesting \n But the input was wrong").setColor(new Color(247, 45, 61)).build()).queue();
                    return;
                }
                
                ResultSet rs2 = db.executeQuery(sql);
                int shame = 0;
                try
                {
                    rs2.wasNull();
                }catch(SQLException e){
                    shame = 1;
                }
                
                if(shame==0)
                {
                try
                {
                    if(event.getGuild().getRoleById(wrds[2]).equals(null)); 
                }catch(Exception e12){
                    event.getChannel().sendMessage(new EmbedBuilder().setTitle("AutoRoles Section").setDescription("🔥Thanks for requesting \n But the role ID was wrong").setColor(new Color(247, 45, 61)).build()).queue();
                    fla = 1;
                }
                if(fla!=1)
                {
                    sql  = "INSERT INTO GuildSettings(gId, BRoles) VALUES('"+event.getGuild().getId()+"', '"+wrds[2]+"')";
                    db.executeUpdate(sql);

                    event.getChannel().sendMessage(new EmbedBuilder().setTitle("AutoRoles Section").setDescription("🔥Thanks for requesting \n We have added the Role you requested for, Name: "+event.getGuild().getRoleById(wrds[2]).getName()).setColor(new Color(66, 158, 245)).build()).queue();
                }
                }else{
                    event.getChannel().sendMessage(new EmbedBuilder().setTitle("AutoRoles Section").setDescription("🔥Thanks for requesting \n But the role is already there").setColor(new Color(247, 45, 61)).build()).queue();
                }
                
            }else{
                if(wrds[1].equalsIgnoreCase("remove"))
                {
                    try{sql = "SELECT * FROM GuildSettings WHERE BRoles='"+event.getGuild().getRoleById(wrds[2])+"';";}catch(Exception e123){
                        event.getChannel().sendMessage(new EmbedBuilder().setTitle("AutoRoles Section").setDescription("🔥Thanks for requesting \n But the input was wrong").setColor(new Color(247, 45, 61)).build()).queue();
                        return;
                    }
                    
                    ResultSet rs2 = db.executeQuery(sql);
                    int shame = 0;

                    try
                    {
                        rs2.wasNull();
                    }catch(SQLException e){
                        shame = 1;
                    }

                    if(shame==1){
                        event.getChannel().sendMessage(new EmbedBuilder().setTitle("AutoRoles Section").setDescription("🔥Thanks for requesting \n But the role already doesn't exist").setColor(new Color(247, 45, 61)).build()).queue();
                        return;
                    }

                    int fla1 = 0;
                    try{
                        if(event.getGuild().getRoleById(wrds[2]).equals(null));
                    }catch(Exception e)
                    {
                        event.getChannel().sendMessage(new EmbedBuilder().setTitle("AutoRoles Section").setDescription("🔥Thanks for requesting \n \n But the RoleId was wrong").setColor(new Color(247, 45, 61)).setAuthor("TechUtilites").build()).queue();
                        fla1 = 1;
                        
                    }
                    if(fla1!=1)
                    {
                        try{
                        sql = "DELETE FROM GuildSettings WHERE BRoles='"+event.getGuild().getRoleById(wrds[2]).getId()+"';";
                        }catch(Exception e123123){event.getChannel().sendMessage(new EmbedBuilder().setTitle("AutoRoles Section").setDescription("🔥Thanks for requesting \n But the input was wrong").setColor(new Color(247, 45, 61)).build()).queue();
                        return;}
                        db.executeUpdate(sql);
                        sql = "DELETE FROM sqlite_sequence WHERE name='GuildSettings';";
                        db.executeUpdate(sql);

                        event.getChannel().sendMessage(new EmbedBuilder().setTitle("AutoRoles Section").setDescription("🔥Thanks for requesting \n \n We have Removed the Role you requested for, Name: "+event.getGuild().getRoleById(wrds[2]).getName()).setColor(new Color(66, 158, 245)).build()).queue();
                    }
                }
            }
            db.closeAll();
        }
    }

    
}
