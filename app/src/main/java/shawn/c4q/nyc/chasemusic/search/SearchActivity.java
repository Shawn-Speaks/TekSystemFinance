package shawn.c4q.nyc.chasemusic.search;

import android.content.Context;
import android.os.Bundle;
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
import shawn.c4q.nyc.chasemusic.model.itunesmodel.ItunesResponse;

public class SearchActivity extends BaseActivitiy implements View.OnClickListener, MainView{


    private static final String TAG = "DEBUG TOOL";
    @BindView(R.id.search_btn)
    Button searchBtn;
    @BindView(R.id.search_edit_text)
    EditText searchEditText;
    @BindView(R.id.search_recycler)
    RecyclerView searchRecycler;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchBtn.setOnClickListener(this);

//        button = (Button) findViewById(R.id.testBtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new GetLyrics().execute();
//            }
//        });
        presenter.initialize();


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

    }

    @Override
    protected void setLayoutId() {
        layoutId = R.layout.activity_search;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.search_btn:
                if(searchEditText.getText().length() > 0){
                    makeRequest(searchEditText.getText().toString());
                    try {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
                    } catch (Exception e) {
                       Log.d(TAG, e.toString());
                    }
                }
                break;


        }
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

    }
}
