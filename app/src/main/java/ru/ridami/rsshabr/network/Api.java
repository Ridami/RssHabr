package ru.ridami.rsshabr.network;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.ridami.rsshabr.BuildConfig;
import ru.ridami.rsshabr.pojo.Rss;

/**
 * Created by Forest on 04.03.2018.
 */

public class Api {

    private static final String TAG = "API";

    public static Rss loadRSS() {
        HttpURLConnection urlConnection = null;
        Rss rss = null;
        try {
          URL url = new URL(BuildConfig.SERVER_URL);
          urlConnection = (HttpURLConnection) url.openConnection();
          if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
              InputStream in = new BufferedInputStream(urlConnection.getInputStream());
              rss = readStream(in);
          }
        }
        catch (IOException e) {
            Log.e(TAG, "Error", e);
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
        }
        finally {
            urlConnection.disconnect();
        }
        return rss;
    }

    private static Rss readStream(InputStream in) {
        Serializer ser = new Persister();
        Rss rss  = null;
        try {
            rss = ser.read(Rss.class, in);
        } catch (Exception e) {
            Log.e(TAG, "readStream:  " + e.toString(), e);
        }
        return rss;
    }
}
