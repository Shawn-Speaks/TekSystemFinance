package shawn.c4q.nyc.chasemusic.search;

import shawn.c4q.nyc.chasemusic.model.itunesmodel.ItunesResponse;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public interface MainView {

    void showLoading();
    void hideLoading();
    void itunesSearchSuccess(ItunesResponse response);
}
