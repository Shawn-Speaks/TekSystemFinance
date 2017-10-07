package shawn.c4q.nyc.chasemusic.model.itunesmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public class Result {

    @SerializedName("kind")
    private String kind;
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("collectionName")
    private String albumName;
    @SerializedName("trackName")
    private String trackName;
    @SerializedName("artworkUrl100")
    private String imgUrl;


    public String getKind() {
        return kind;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
