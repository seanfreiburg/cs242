package network;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * User: seanfreiburg
 * Date: 11/4/13
 * Time: 5:08 PM
 */
public class ServerMock {

    @Mock
    Server serverMock;

    @Test
    public void testNetServer() throws Exception {
        Server t = Mockito.mock(Server.class);

    }
}
