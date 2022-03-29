package action;

import java.io.IOException;

public interface Actionable {
    void doAction() throws IOException, ClassNotFoundException, InterruptedException;
}
