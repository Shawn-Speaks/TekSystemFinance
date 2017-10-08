package shawn.c4q.nyc.chasemusic.search;

import android.view.View;

import shawn.c4q.nyc.chasemusic.model.itunesmodel.Result;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public interface SearchItemCallback {

    void handleResultClick(View view,Result result);

}
