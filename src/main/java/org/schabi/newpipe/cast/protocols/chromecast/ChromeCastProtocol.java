package org.schabi.newpipe.cast.protocols.chromecast;

import org.schabi.newpipe.cast.CastingProtocol;
import org.schabi.newpipe.cast.Discoverer;
import java.util.logging.Logger;

public class ChromeCastProtocol extends CastingProtocol {
    private final static Logger LOGGER = Logger.getLogger(ChromeCastProtocol.class.getName());

    public ChromeCastProtocol(int id, String name) {
        super(id, name);
    }

    @Override
    public Discoverer getDiscoverer() {
        LOGGER.fine("get chromecast discoverer instance");
        return ChromeCastDiscoverer.getInstance();
    }
}
