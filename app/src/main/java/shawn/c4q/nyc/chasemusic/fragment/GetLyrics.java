package shawn.c4q.nyc.chasemusic.fragment;

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
    private LyricsRecievedCallback callback;
    private String artistName;
    private String songName;

    public GetLyrics(LyricsRecievedCallback callback, String artistName, String songName) {
        this.callback = callback;
        this.artistName = artistName;
        this.songName = songName;
    }


    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        final String BASE_URL = "http://lyrics.wikia.com/api.php?";
        String resultString = "";
        try{
            URL url = new URL(BASE_URL + buildQuery());
            Log.d("DEBUG TOOL", BASE_URL + buildQuery());
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
        s = removeUpToAndAfter(s, "'lyrics'", ",'url'");
        s = replaceBeg(s, "'lyrics':");
        s = formatLyrics(s);
        callback.lyricsRecieved(s);
        super.onPostExecute(s);
    }

    private String buildQuery(){
        StringBuilder sb = new StringBuilder("func=getSong&artist=");
        sb.append(artistName.replaceAll(" ", "+"));
        sb.append("&song=");
        sb.append(songName.replace(" ", "+"));
        sb.append("&fmt=json");
        return sb.toString();
    }
    private String removeUpToAndAfter(String input, String removeBeg, String removeEnd){
        return input.substring(input.indexOf(removeBeg), input.indexOf(removeEnd));
    }
    private String replaceBeg(String input, String remove){
        return input.replace(remove, "");
    }

    private String formatLyrics(String input){
        String temp = android.text.Html.fromHtml(input).toString();
        String formatted = temp.replace("[", "");
        formatted = formatted.replace("]", "");
        formatted = formatted.replace("\"", "");
        formatted = formatted.replace("-", "");
        formatted = formatted.replace("\\n", System.getProperty("line.separator"));
        return formatted;
    }

}
