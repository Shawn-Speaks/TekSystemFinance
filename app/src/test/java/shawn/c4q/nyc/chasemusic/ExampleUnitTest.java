package shawn.c4q.nyc.chasemusic;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private String resultString = "";

    @Before
    public void setup() throws Exception {
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
    public void resultString (){
        assertEquals(resultString, "song = {" + "'artist':'Tom Waits'," + "'song':'New Coat Of Paint'," + "'lyrics':'Let\\'s put a new coat of paint on this lonesome old town\\nSet \\'em up, we\\'ll be knockin\\' em [...]'," + "'url':'http://lyrics.wikia.com/Tom_Waits:New_Coat_Of_Paint'" + "}");
    }


}