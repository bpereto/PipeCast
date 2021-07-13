package org.schabi.newpipe.cast.protocols.chromecast;

import org.schabi.newpipe.cast.*;
import org.schabi.newpipe.cast.exceptions.XmlWriterException;
import org.schabi.newpipe.cast.protocols.upnp.GenaServer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import su.litvak.chromecast.api.v2.Application;
import su.litvak.chromecast.api.v2.ChromeCast;
import su.litvak.chromecast.api.v2.MediaStatus;
import su.litvak.chromecast.api.v2.Status;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class ChromeCastDevice extends Device {

    private final static Logger LOGGER = Logger.getLogger(ChromeCastDevice.class.getName());

    /*
     * Cast SDK
     *
     * CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID
     * https://developers.google.com/android/reference/com/google/android/gms/cast/CastMediaControlIntent?hl=de#public-static-final-string-default_media_receiver_application_id
     */
    private static final String MediaPlayer = "CC1AD845";

    private ChromeCast device;
    private Application app;

    public ChromeCastDevice(String location, InetAddress inetAddress, ChromeCast cc) {
        super(location, inetAddress);
        this.device = cc;
    }

    @Override
    public String getName() {
        return device.getName();
    }

    @Override
    public void play(String url, String title, String creator, MediaFormat mediaFormat) throws IOException {
        Status status = this.device.getStatus();
        LOGGER.info(status.toString());

        if (this.device.isAppAvailable(MediaPlayer) && !status.isAppRunning(MediaPlayer)) {
            this.app = this.device.launchApp(MediaPlayer);
        }

        this.device.load(url);
    }

    @Override
    public void pause() throws IOException {
        this.device.pause();
    }

    @Override
    public void addToQueue(String url, String title, String creator, MediaFormat mediaFormat) throws IOException, XmlWriterException {

    }

    @Override
    public void playPause() throws IOException, XmlWriterException, ParserConfigurationException, SAXException {
        if (this.device.getMediaStatus().playerState.equals(MediaStatus.PlayerState.PAUSED)) {
            this.device.play();
        } else {
            this.device.pause();
        }
    }

    @Override
    public List<MediaFormat> getSupportedFormats() throws IOException, ParserConfigurationException, SAXException, XmlWriterException {
        return null;
    }

    @Override
    public Stoppable startBackgroundWork() throws IOException {
        return null;
    }
}