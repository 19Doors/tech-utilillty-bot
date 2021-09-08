package moderation.mute;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class muteOrNot {

    public static boolean checkMute(MessageReceivedEvent event, Member member){

        //Checking if user has Muted role
        int size = event.getGuild().getRolesByName("Muted", true).size();
        int f = 0;
        for(int i = 0; i<size; i++){
            Role mute = event.getGuild().getRolesByName("Muted", true).get(i);
            List<Member> members = event.getGuild().getMembersWithRoles(mute);
            int sizem = members.size();
            for(int j = 0; j<sizem; j++) {
                if(members.get(j).getId().equals(member.getId())){
                    f = 1;
                }
            }
        }
        if(f==1){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {

    }
}
