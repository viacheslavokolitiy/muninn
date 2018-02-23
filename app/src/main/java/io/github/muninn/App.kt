package io.github.muninn

import android.app.Activity
import android.app.Application
import android.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import io.github.muninn.di.component.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by viacheslavokolitiy on 12.02.2018.
 */
class App : Application(), HasActivityInjector, HasFragmentInjector {
    @Inject
    lateinit var activityDispatcher: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentDispatcher: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatcher
    }

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatcher
    }
}