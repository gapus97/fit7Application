package unitTests;

import fun7App.entity.Users;
import fun7App.features.Multiplayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiPlayerTest {
    public Users user;

    @Before
    public void setUp() {
        user = new Users();
    }

    @Test
    public void testMultiplayerUserNull() {
        assertEquals("disabled", new Multiplayer(user).getMultiplayerResponse());
    }

    @Test
    public void testMultiplayerEnabled() {
        user.setTimesLogged(6);
        user.setCountryCode("US");
        assertEquals("enabled", new Multiplayer(user).getMultiplayerResponse());
        user.setTimesLogged(100);
        assertEquals("enabled", new Multiplayer(user).getMultiplayerResponse());
        user.setTimesLogged(1000);
        assertEquals("enabled", new Multiplayer(user).getMultiplayerResponse());
    }

    @Test
    public void testMultiplayerDisabled() {
        user.setTimesLogged(5);
        user.setCountryCode("US");
        assertEquals("disabled", new Multiplayer(user).getMultiplayerResponse());
        user.setTimesLogged(6);
        user.setCountryCode("SLo");
        assertEquals("disabled", new Multiplayer(user).getMultiplayerResponse());
    }
}
