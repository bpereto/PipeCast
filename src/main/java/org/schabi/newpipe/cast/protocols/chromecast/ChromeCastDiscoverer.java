package org.schabi.newpipe.cast.protocols.chromecast;

import org.schabi.newpipe.cast.Device;
import org.schabi.newpipe.cast.Discoverer;
import su.litvak.chromecast.api.v2.ChromeCast;
import su.litvak.chromecast.api.v2.ChromeCasts;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;

public class ChromeCastDiscoverer extends Discoverer {
    private final static Logger LOGGER = Logger.getLogger(ChromeCastDiscoverer.class.getName());
    private static final ChromeCastDiscoverer instance = new ChromeCastDiscoverer();

    public static ChromeCastDiscoverer getInstance() {
        return instance;
    }
    private List<Device> devices;

    @Override
    public List<Device> discoverDevices() throws IOException, InterruptedException {

        devices = new ArrayList<>();
        Set<String> addresses = new HashSet<>();

        LOGGER.info("start ChromeCast discovery");

        // get all site-local IPs to scan from
        Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
        while (ifaces.hasMoreElements()) {
            NetworkInterface ni = ifaces.nextElement();
            Enumeration<InetAddress> addrs = ni.getInetAddresses();
            while (addrs.hasMoreElements()) {
                InetAddress current = addrs.nextElement();
                if (current.isSiteLocalAddress()) {
                    addresses.add(current.getHostAddress());
                    LOGGER.fine("found host address: " + current.getHostAddress());
                }
            }
        }

        ChromeCasts.startDiscovery(InetAddress.getByName(addresses.iterator().next()));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for( ChromeCast cc : ChromeCasts.get()) {
            String description = "test";
            InetAddress address = InetAddress.getByName(cc.getAddress());
            LOGGER.info("found chromecast: name=" + cc.getName() + " address=" + address);
            Device castDevice = new ChromeCastDevice(description, address, cc);
            devices.add(castDevice);
        }
        return devices;
    }
}
