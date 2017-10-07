package shawn.c4q.nyc.chasemusic.search;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import shawn.c4q.nyc.chasemusic.api.ItunesAPI;
import shawn.c4q.nyc.chasemusic.model.itunesmodel.ItunesResponse;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public class SearchPresenter extends BasePresenter<MainView> {

    private static final String TAG = "Debug tool";
    private ItunesAPI itunesclient;
    private CompositeDisposable disposable = new CompositeDisposable();


    @Inject
    public SearchPresenter(ItunesAPI client){
        this.itunesclient = client;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void destroy() {
        unBindView();
        disposable.clear();
    }


    void loadTracks(String searchQuery) {
        view.showLoading();
        Observable<ItunesResponse> obs = itunesclient.getItunesResults(searchQuery);
        disposable.add(obs.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::apiSuccess, this::error));
    }

    private void apiSuccess(ItunesResponse response){
        view.itunesSearchSuccess(response);
        view.hideLoading();
    }

    private void error(Throwable t){
        view.hideLoading();
        Log.d(TAG, t.toString());
    }

}
