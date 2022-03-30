package process.request;

import client.User;
import message.Message;
import response.FamousUserResponse;
import response.Response;
import sendable.Sendable;
import sendable.requests.Request;
import server.data.Database;

import java.util.Deque;
import java.util.Map;

public class ProcessFamousUser extends ProcessRequest {
    public ProcessFamousUser(Database database) {
        super(database);
    }

    @Override
    public Response getResponse(Sendable receiver) {
        StringBuilder builder = new StringBuilder();
        Map<User,Integer> ranking = database.getNbSubscribers();
        int index = 1;
        for(Map.Entry<User, Integer> entry : ranking.entrySet()){
            builder.append(index)
                    .append(" : ")
                    .append(entry.getKey().getName())
                    .append(" ")
                    .append("avec ")
                    .append(entry.getValue())
                    .append(" abonnés")
                    .append("\r\n");
            index += 1;
        }
        return new FamousUserResponse(builder.toString());
    }
}
