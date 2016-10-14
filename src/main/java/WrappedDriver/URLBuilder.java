package WrappedDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class URLBuilder {

    public static URL getRemoteServerUrl(String hubUrl, String port) {
        try {
            return new URL(String.format("http://"+ hubUrl +":"+port+"/wd/hub"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
