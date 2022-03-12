package server;
import message.Message;
import java.util.*;

public class Database {
    private List<Message> messages;

    public Database(){
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public List<Long> getIds(String author, String tag, long since, long limit){

        List<Message> listMessages = messages.stream()
                .filter(m -> (m.getAuthor().equals(author) || m.getTags().contains(tag)) && since < m.getId())
                .limit(limit)
                .toList();

        List<Long> listIds = new ArrayList<>();

        for(Message message : listMessages)
            listIds.add(message.getId());

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

    public boolean idExists(long id){
        for(Message message : messages){
            if (message.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public int size(){
        return messages.size();
    }



}
