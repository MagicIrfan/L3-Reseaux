package server;
import requests.Message;
import java.util.*;

public class Database {
    private List<Message> messages;

    public Database(){
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public List<Long> getIds(int n){
        List<Long> listIds = new ArrayList<>();
        int length = messages.size();
        for(int index = length; index > length-n; index--){
            long id = messages.get(index).getId();
            listIds.add(id);
        }
        return listIds;
    }

    public Message getMessageWithId(long id){
        for(Message message : messages) {
            if(message.getId() == id) {
                return message;
            }
        }
        return null;
    }


}
