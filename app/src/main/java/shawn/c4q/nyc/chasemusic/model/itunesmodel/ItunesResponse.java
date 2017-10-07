package shawn.c4q.nyc.chasemusic.model.itunesmodel;

import java.util.ArrayList;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public class ItunesResponse {

    private int resultCount;
    private ArrayList<Result> resultList;

    public int getResultCount() {
        return resultCount;
    }

    public ArrayList<Result> getResultList() {
        return resultList;
    }
}
