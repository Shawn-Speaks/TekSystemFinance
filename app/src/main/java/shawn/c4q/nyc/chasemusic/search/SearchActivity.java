package shawn.c4q.nyc.chasemusic.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import shawn.c4q.nyc.chasemusic.R;
import shawn.c4q.nyc.chasemusic.app.ApplicationExtension;
import shawn.c4q.nyc.chasemusic.dagger.AppComponent;
import shawn.c4q.nyc.chasemusic.model.itunesmodel.ItunesResponse;
import shawn.c4q.nyc.chasemusic.model.itunesmodel.Result;
import shawn.c4q.nyc.chasemusic.search.recycler.SearchAdapter;

public class SearchActivity extends BaseActivitiy implements View.OnClickListener, MainView, SearchItemCallback{


    private static final String TAG = "DEBUG TOOL";
    @BindView(R.id.search_btn)
    Button searchBtn;
    @BindView(R.id.search_edit_text)
    EditText searchEditText;
    @BindView(R.id.search_recycler)
    RecyclerView searchRecycler;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private AppComponent component;

    @Inject SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchBtn.setOnClickListener(this);
        initRV();
    }

    @Override
    protected void killPresenter() {
        presenter.unBindView();
        presenter.destroy();
    }

    @Override
    protected void setupPresenter() {
        presenter.bindView(this);
    }


    @Override
    protected void setupInjector() {
        component = ((ApplicationExtension) getApplication()).getComponent();
        component.inject(this);
    }

    @Override
    protected void setLayoutId() {
        layoutId = R.layout.activity_search;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.search_btn:
                //check if the edit text field is empty
                if(searchEditText.getText().length() > 0){
                    makeRequest(searchEditText.getText().toString());
                    try {
                        //Style code to drop the keyboard after a search is submitted
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
                    } catch (Exception e) {
                       Log.d(TAG, e.toString());
                    }
                }
                break;


        }
    }

    private void initRV() {
        searchRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void makeRequest(String queryString){
        presenter.loadTracks(queryString);
    }

    @Override
    public void showLoading() {
        searchRecycler.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void itunesSearchSuccess(ItunesResponse response) {
        Toast.makeText(this, String.valueOf(response.getResultCount()), Toast.LENGTH_SHORT).show();
        SearchAdapter adapter = new SearchAdapter(response.getResultList());
        searchRecycler.setAdapter(adapter);
        searchRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void handleResultClick(View view, Result result) {


    }
}
