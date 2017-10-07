package shawn.c4q.nyc.chasemusic.api;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public interface LyricsAPI {

    String BASE_URL = "http://lyrics.wikia.com/";

    @GET("api.php")
    Call<JSONObject> lyricResponseRequest(@QueryMap Map<String, String> queryParamMap);

}
