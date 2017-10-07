package shawn.c4q.nyc.chasemusic.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import shawn.c4q.nyc.chasemusic.model.itunesmodel.ItunesResponse;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public interface ItunesAPI {

    String BASE_URL = "https://itunes.apple.com/";

    @GET("search")
    Observable<ItunesResponse> getItunesResults(@Query("term") String searchTerm);

}
