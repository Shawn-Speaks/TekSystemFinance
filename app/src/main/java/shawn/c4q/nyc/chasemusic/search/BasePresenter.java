package shawn.c4q.nyc.chasemusic.search;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public abstract class BasePresenter<V> {

    protected V view;

    public abstract void initialize();

    public abstract void destroy();

    public <T extends V> void bindView(T view){ this.view = view; }

    public void unBindView(){ this.view = null; }
}
