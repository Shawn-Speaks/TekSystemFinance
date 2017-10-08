package shawn.c4q.nyc.chasemusic.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shawnspeaks on 10/7/17.
 */
@Module
public class AppModule {

    private Application application;

    public AppModule (Application application){
        this.application = application;
    }

    @Provides
    public Application provideApplication(){
        return application;
    }

    @Provides
    public Context providesApplicationContext(){
        return application.getApplicationContext();
    }

}
