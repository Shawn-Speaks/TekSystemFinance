package shawn.c4q.nyc.chasemusic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

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
@Config(constants = BuildConfig.class)

@RunWith(RobolectricTestRunner.class)
public class FunkyJsonTest {


    private String resultString = "";

    @Before
    public void setup(){

        HttpURLConnection connection = null;

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
    }



    @Test
    public void shouldNotBeNull(){

    }

}
