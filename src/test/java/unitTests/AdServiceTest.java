package unitTests;

import fun7App.features.AdsService;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class AdServiceTest {

    @Test
    public void testConnectionNull() throws MalformedURLException {
        assertEquals(null, new AdsService("", "", "", "").getAdServieceResponse());
    }

    @Test
    public void testAdServiceWrongUrl() throws UnknownHostException {
        assertEquals(null, new AdsService("https://us-central1-o7tools.cloudfunctions.neASDDSt/DASDADAW", "fun7user", "fun7pass", "US").getAdServieceResponse());
    }

    @Test
    public void testAdServiceWrongUsername() {
        assertEquals(401, new AdsService("https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner/", "fun7dsadasdauser", "fun7pass", "US").getAdServieceResponse().getStatusCodeValue());
    }

    @Test
    public void testAdServiceWrongPassword() {
        assertEquals(401, new AdsService("https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner/", "fun7user", "fun7passsasaa", "US").getAdServieceResponse().getStatusCodeValue());
    }

    @Test
    public void testAdServiceWrongWrongCountryCode() {
        assertEquals("disabled", new AdsService("https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner/", "fun7user", "fun7pass", "GGGGGGGGGGGGG").getAdServieceResponse().getBody().toString());
    }
}
