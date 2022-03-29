package server.data;
import client.User;
import message.Message;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Database {
    private List<Message> messages;
    private List<User> listUsers;
    private ConcurrentSkipListMap<User, List<User>> subscribers;
    private ConcurrentHashMap<User, List<Message>> messagesMap;
    private ConcurrentHashMap<User, List<Message>> messagesToSend;


    public Database(){
        this.messages = new ArrayList<>();
        this.listUsers = new ArrayList<>();
        this.subscribers = new ConcurrentSkipListMap<>();
        this.messagesMap = new ConcurrentHashMap<>();
        this.messagesToSend = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<User, List<Message>> getReceivedMessages(){
        return messagesToSend;
    }

    public void setReceivedMessages (User user){

        User connectedUser = getConnectedUser(user);
        ConcurrentHashMap<User, List<Message>> messages = messagesMap;
        ConcurrentSkipListMap<User,List<User>> listUsers = subscribers;
        List<User> subscribers = listUsers.get(connectedUser);
        boolean sent = false;

        for(Map.Entry<User,List<User>> entry : listUsers.entrySet()){
            User connectedSubscriber = getConnectedUser(entry.getKey());
            List<User> oui = entry.getValue();
            if(oui.contains(connectedUser)){
                List<Message> allMessages = messages.get(connectedUser);
                messagesToSend.get(connectedSubscriber).addAll(allMessages);
            }
        }

    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public ConcurrentHashMap<User, List<Message>> getMessagesMap(){
        return messagesMap;
    }

    public ConcurrentSkipListMap<User, List<User>> getSubscribers() {
        return subscribers;
    }

    public void addMessageToUser(User user,Message message){
        messagesMap.get(user).add(message);
    }

    public void connectUser(User user){
        subscribers.put(user,new ArrayList<>());
        messagesMap.put(user,new ArrayList<>());
        messagesToSend.put(user,new ArrayList<>());
    }

    public User getConnectedUser(User client){
        for(Map.Entry<User,List<User>> entry : subscribers.entrySet()){
            User user = entry.getKey();
            if(user.getName().equals(client.getName()))
                return user;
        }
        return null;
    }

    public boolean containsConnectedUser(User user){
        User connected = getConnectedUser(user);
        if(connected == null)
            return false;
        return subscribers.containsKey(connected);
    }

    public void subscribe(User user,User subscriber){
        subscribers.get(getConnectedUser(user)).add(getConnectedUser(subscriber));
    }

    public boolean subscriberExists(User user,User subscriber){
        return subscribers.get(getConnectedUser(user)).contains(getConnectedUser(subscriber));
    }

    public void unsubscribe(User user, User subscriber){
        subscribers.get(getConnectedUser(user)).remove(getConnectedUser(subscriber));
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

    public void disconnectClient(User user){
        User connectedClient = getConnectedUser(user);
        listUsers.remove(connectedClient);
    }

    public boolean userExists(User user){
        User connectedUser = getConnectedUser(user);
        return listUsers.contains(connectedUser);
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

    public void addUser(User user){
        listUsers.add(user);
    }

    public Socket getSocketWithName(String userName){
        for(User user: listUsers){
            boolean namesAreEqual = user.getName().equals(userName);
            if(namesAreEqual){
                return user.getSocket();
            }
        }
        return null;
    }




}
