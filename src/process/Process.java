package process;

import response.Response;
import sendable.Sendable;

import java.io.IOException;

public interface Process {
    Response getResponse(Sendable sendable ) throws IOException, InterruptedException;
}
