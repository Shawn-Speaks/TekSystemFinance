package shawn.c4q.nyc.chasemusic.dagger;

import javax.inject.Singleton;

import dagger.Component;
import shawn.c4q.nyc.chasemusic.search.SearchActivity;

/**
 * Created by shawnspeaks on 10/7/17.
 */
@Singleton
@Component
        (modules = {
                AppModule.class,
                NetworkModule.class
        })
public interface AppComponent {

    void inject(SearchActivity activity);

}
