package pingers;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ICMPPingerTest {

    @Test
    public void given_accessible_host_when_ping_should_return_success()
            throws IOException, InterruptedException {

        // Arrange
        Pinger pinger = new ICMPPinger();

        // Act
        PingResponse response = pinger.getResponse("localhost");

        // Assert
        assertTrue(response.getSuccess());
        assertNotEquals("", response.getResultMessage());
    }

    @Test
    public void given_inaccessible_host_when_ping_should_return_unsuccess()
            throws IOException, InterruptedException {

        // Arrange
        Pinger pinger = new ICMPPinger();

        // Act
        PingResponse response = pinger.getResponse("inaccessible");

        // Assert
        assertFalse(response.getSuccess());
        assertNotEquals("", response.getResultMessage());
    }

    @Test
    public void given_text_with_packet_lost_when_verify_should_return_true() {

        // Arrange
        ICMPPinger pinger = new ICMPPinger();

        // Act
        boolean result = pinger.hasPacketLost("10% packet loss");

        // Assert
        assertTrue(result);
    }

    @Test
    public void given_text_without_packet_lost_when_verify_should_return_true() {

        // Arrange
        ICMPPinger pinger = new ICMPPinger();

        // Act
        boolean result = pinger.hasPacketLost("0% packet loss");

        // Assert
        assertFalse(result);
    }
}
