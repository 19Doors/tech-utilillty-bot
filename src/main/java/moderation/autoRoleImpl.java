package moderation;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class autoRoleImpl extends ListenerAdapter{
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent eve)
    {
        DB db = new DB("guildSettings");
        db.openDatabase();
            
            
        String sql =  "SELECT * FROM GuildSettings WHERE gId='"+eve.getGuild().getId()+"' AND BRoles IS NOT NULL";
        
        ResultSet rs = db.executeQuery(sql);
        
        try{
            while ( rs.next() ) {
                Role role = eve.getGuild().getRoleById(rs.getString("BRoles"));
                Member mem = eve.getGuild().getMemberById(eve.getMember().getId());
                eve.getGuild().addRoleToMember(mem, role).complete();
             } 
           
        }catch(SQLException e){
            
        }

        db.closeAll();
    }
}