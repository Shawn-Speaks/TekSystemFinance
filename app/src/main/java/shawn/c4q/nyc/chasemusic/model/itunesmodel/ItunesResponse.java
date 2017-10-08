package shawn.c4q.nyc.chasemusic.model.itunesmodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public class ItunesResponse implements Serializable {

    @SerializedName("resultCount")
    private int resultCount;
    @SerializedName("results")
    private ArrayList<Result> resultList;

    public int getResultCount() {
        return resultCount;
    }

    public ArrayList<Result> getResultList() {
        return resultList;
    }
}
