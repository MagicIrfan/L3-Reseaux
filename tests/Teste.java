
import client.MicroblogAMU;
import org.junit.*;
import server.MicroblogAMUCentral;

import java.io.IOException;
import java.net.Socket;

public class Teste {

    MicroblogAMUCentral server;
    MicroblogAMU amu;

    @Before
    public void setup() throws IOException, ClassNotFoundException {
        server = new MicroblogAMUCentral();
        amu = new MicroblogAMU();
    }
    @Test
    public void firstTest() {
    }
    @After
    public void teardown() {
    }
}
