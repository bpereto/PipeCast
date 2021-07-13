package protocols.chromecast;

import org.junit.jupiter.api.Test;
import org.schabi.newpipe.cast.*;
import org.schabi.newpipe.cast.exceptions.ProtocolNotFoundException;
import org.schabi.newpipe.cast.exceptions.XmlWriterException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class ChromeCastTest {

    static final Logger LOGGER = Logger.getLogger(ChromeCastTest.class.getName());

    @Test
    public void testChromeCastDiscovery() throws ProtocolNotFoundException, IOException, ExecutionException, InterruptedException, XmlWriterException {
        CastingProtocol protocol = PipeCast.getProtocol(1);
        LOGGER.info(String.valueOf(protocol.getProtocolId()));

        Discoverer discoverer = protocol.getDiscoverer();
        List<Device> devices = discoverer.discoverDevices();
        LOGGER.info(devices.toString());

        devices.get(0).play("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "buck bunny", "test", MediaFormat.MOVIE_MPEG);
    }
}