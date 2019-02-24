package com.m6world.tari_labs._dagger;

import android.app.Application;
import com.m6world.tari_labs.MainApplication;
import com.m6world.tari_labs.features._events.EventsViewModel;
import com.m6world.tari_labs.features._home.HomeViewModel;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class,
        AppModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(MainApplication mainApplication);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(HomeViewModel homeViewModel);
    void inject(EventsViewModel eventsViewModel);
}
