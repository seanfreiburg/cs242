package network;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ClientMock {


    @Mock
    Client clientMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNetClient() throws Exception {
        Client t = Mockito.mock(Client.class);
        t.setPlayerFromNetwork();

        Mockito.verify(t).setPlayerFromNetwork();
    }




}



