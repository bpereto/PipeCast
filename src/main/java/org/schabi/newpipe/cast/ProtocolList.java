package org.schabi.newpipe.cast;

import org.schabi.newpipe.cast.protocols.chromecast.ChromeCastProtocol;
import org.schabi.newpipe.cast.protocols.upnp.UpnpProtocol;

public enum ProtocolList {
    Upnp(new UpnpProtocol(0, "UPnP")),
    Cast(new ChromeCastProtocol(1, "Cast v2"));

    private final CastingProtocol protocol;

    ProtocolList(CastingProtocol protocol) {
        this.protocol = protocol;
    }

    public CastingProtocol getProtocol() {
        return protocol;
    }

    public CastingProtocol.ProtocolInfo getProtocolInfo() {
        return protocol.getProtocolInfo();
    }

    public int getId() {
        return protocol.getProtocolId();
    }

    @Override
    public String toString() {
        return protocol.getProtocolInfo().name;
    }
}
