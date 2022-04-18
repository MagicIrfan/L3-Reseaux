package server.data;
import Tools.MapUtils;
import client.User;
import message.Message;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListMap;

//CLASSE REPRESENTANT LA BASE DE DONNEES
public class Database {
    private Deque<Message> messages;
    private Deque<User> listUsers;
    private ConcurrentSkipListMap<User, Deque<User>> subscribers;
    private ConcurrentHashMap<User, Deque<Message>> messagesMap;
    private ConcurrentHashMap<User, Deque<Message>> messagesToSend;
    private ConcurrentHashMap<Long,Integer> nbOfRepublished;


    public Database(){
        this.messages = new ConcurrentLinkedDeque<>();
        this.listUsers = new ConcurrentLinkedDeque<>();
        this.subscribers = new ConcurrentSkipListMap<>();
        this.messagesMap = new ConcurrentHashMap<>();
        this.messagesToSend = new ConcurrentHashMap<>();
        this.nbOfRepublished = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<Long,Integer> getNbOfRepublished(){
        return nbOfRepublished;
    }

    public ConcurrentHashMap<User, Deque<Message>> getReceivedMessages(){
        return messagesToSend;
    }

    public void setReceivedMessages (User user){

        User connectedUser = getConnectedUser(user);
        ConcurrentHashMap<User, Deque<Message>> messages = messagesMap;
        ConcurrentSkipListMap<User,Deque<User>> listUsers = subscribers;

        for(Map.Entry<User,Deque<User>> entry : listUsers.entrySet()){
            User connectedSubscriber = getConnectedUser(entry.getKey());
            Deque<User> oui = entry.getValue();
            if(oui.contains(connectedUser)){
                Deque<Message> allMessages = messages.get(connectedUser);
                messagesToSend.get(connectedSubscriber).addAll(allMessages);
            }
        }

    }

    public Map<User,Integer> getNbSubscribers(){
        Map<User,Integer> ranking = new ConcurrentSkipListMap<>();

        for(Map.Entry<User,Deque<User>> entry : subscribers.entrySet()){
            ranking.put(entry.getKey(),0);
        }

        for(Map.Entry<User,Deque<User>> entry : subscribers.entrySet()){
            for(Map.Entry<User,Deque<User>> entry2 : subscribers.entrySet()) {
                Deque<User> subscribers = entry2.getValue();
                for (User subscriber : subscribers) {
                    if (entry.getKey().getName().equals(subscriber.getName())) {
                        ranking.put(entry.getKey(), ranking.get(entry.getKey()) + 1);
                    }
                }
            }
        }
        return MapUtils.sortFamousByValue(ranking);
    }

    public void addMessage(Message message){
        messages.add(message);
        nbOfRepublished.put(message.getId(),0);
    }

    public ConcurrentHashMap<User, Deque<Message>> getMessagesMap(){
        return messagesMap;
    }

    public ConcurrentSkipListMap<User, Deque<User>> getSubscribers() {
        return subscribers;
    }

    public void addMessageToUser(User user,Message message){
        messagesMap.get(user).add(message);
    }

    public void connectUser(User user){
        subscribers.put(user,new ConcurrentLinkedDeque<>());
        messagesMap.put(user,new ConcurrentLinkedDeque<>());
        messagesToSend.put(user,new ConcurrentLinkedDeque<>());
    }

    public User getConnectedUser(User client){
        for(Map.Entry<User,Deque<User>> entry : subscribers.entrySet()){
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
