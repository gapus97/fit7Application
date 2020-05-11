package unitTests;

import fun7App.features.CustumerSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CustomerSupportTest {

    @Test
    public void testCheckCustomerSupportEnabled() {
        assertEquals("enabled", setFixedTimeForCustomerSupport("2020-05-11T10:15:30.00Z"));
        assertEquals("enabled", setFixedTimeForCustomerSupport("2020-05-11T12:15:30.00Z"));
        assertEquals("enabled", setFixedTimeForCustomerSupport("2020-05-11T11:15:30.00Z"));
    }

    @Test
    public void testCheckCustomerSupportDisabled() {
        assertEquals("disabled", setFixedTimeForCustomerSupport("2020-05-11T06:15:30.00Z"));
        assertEquals("disabled", setFixedTimeForCustomerSupport("2020-05-11T02:15:30.00Z"));
        assertEquals("disabled", setFixedTimeForCustomerSupport("2020-05-11T01:15:30.00Z"));
    }

    @Test
    public void testCheckCustomerSupportOnWeekend() {
        assertEquals("disabled", setFixedTimeForCustomerSupport("2020-05-10T10:15:30.00Z"));
        assertEquals("disabled", setFixedTimeForCustomerSupport("2020-05-09T10:15:30.00Z"));
    }

    public String setFixedTimeForCustomerSupport(String fixedTime) {
        Clock clock = Clock.fixed(Instant.parse(fixedTime), ZoneId.of("Europe/Ljubljana"));
        LocalDateTime dateTime = LocalDateTime.now(clock);
        CustumerSupport support = new CustumerSupport(dateTime);
        return support.getCustomerSupportResponse();
    }
}
