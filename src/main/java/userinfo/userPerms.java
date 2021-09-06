package userinfo;

import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class userPerms {

    public boolean userHasPerms(List<Permission> perms, String id, MessageReceivedEvent event){
        int f = 1;
        for(int i = 0; i<perms.size(); i++){
            if(!event.getGuild().getMemberById(id).hasPermission(perms.get(i))){
                f = 0;
                break;
            }
        }

        if(f==1){
            return true;
        }else{
            return false;
        }
    }

    public boolean userHasPerms(Permission perm, String id, MessageReceivedEvent event)
    {
        if(event.getGuild().getMemberById(id).hasPermission(perm))
        {
            return true;
        }else{
            return false;
        }
    }
    
}
