package shawn.c4q.nyc.chasemusic.app;

import android.app.Application;

import shawn.c4q.nyc.chasemusic.dagger.AppComponent;
import shawn.c4q.nyc.chasemusic.dagger.AppModule;
import shawn.c4q.nyc.chasemusic.dagger.DaggerAppComponent;
import shawn.c4q.nyc.chasemusic.dagger.NetworkModule;

/**
 * Created by shawnspeaks on 10/7/17.
 */

public class ApplicationExtension extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = makeComponent();
    }

    public AppComponent getComponent() {
        return component;
    }

    private AppComponent makeComponent() {

        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }
}
