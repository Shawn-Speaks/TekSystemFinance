package shawn.c4q.nyc.chasemusic;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public class GetLyrics extends AsyncTask<String, Void, String> {
    private static final String TAG = "DEBUG TOOL";

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        String resultString = "";
        try{
            URL url = new URL("http://lyrics.wikia.com/api.php?func=getSong&artist=Tom+Waits&song=new+coat+of+paint&fmt=json");
            connection = (HttpURLConnection) url.openConnection();
            int connectionCode = connection.getResponseCode();

            if(connectionCode == 200){
                InputStream in = new BufferedInputStream(connection.getInputStream());
                if(in != null){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line = "";

                    while((line = reader.readLine()) != null){
                        resultString += line;
                    }
                }
                in.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(connection != null) {
            connection.disconnect();
        }

        return resultString;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, s);
        super.onPostExecute(s);
    }
}
