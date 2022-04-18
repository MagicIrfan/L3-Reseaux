package process.request;

import Tools.MapUtils;
import client.User;
import response.FamousUserResponse;
import response.MostRepublishedResponse;
import response.Response;
import sendable.Sendable;
import server.data.Database;

import java.util.Map;

public class ProcessMostRepublishMsg extends ProcessRequest{

    public ProcessMostRepublishMsg(Database database) {
        super(database);
    }

    @Override
    public Response getResponse(Sendable receiver) {
        StringBuilder builder = new StringBuilder();
        Map<Long,Integer> ranking = MapUtils.sortRepublishedByValue(database.getNbOfRepublished());
        int index = 1;
        for(Map.Entry<Long, Integer> entry : ranking.entrySet()){
            builder.append(index)
                    .append(" : id ")
                    .append(entry.getKey())
                    .append(" ")
                    .append("avec ")
                    .append(entry.getValue())
                    .append(" republications")
                    .append("\r\n");
            index += 1;
        }
        return new MostRepublishedResponse(builder.toString());
    }
}
