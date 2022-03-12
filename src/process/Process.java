package process;

import response.Response;
import sendable.Sendable;

public interface Process {
    Response getResponse(Sendable sendable );
}
