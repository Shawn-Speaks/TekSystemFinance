package shawn.c4q.nyc.chasemusic.search;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivitiy extends AppCompatActivity {

    @LayoutRes protected int layoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId();
        setContentView(layoutId);
        bindView();

        setupInjector();
        setupPresenter();
    }

    @Override
    protected void onDestroy() {
        killPresenter();
        super.onDestroy();
    }

    protected abstract void killPresenter();

    protected abstract void setupPresenter();

    protected abstract void setupInjector();

    protected abstract void setLayoutId();

    private void bindView(){
        ButterKnife.bind(this);
    }
}
