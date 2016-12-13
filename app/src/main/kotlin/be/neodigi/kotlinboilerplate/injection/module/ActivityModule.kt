package be.neodigi.kotlinboilerplate.injection.module

import android.app.Activity
import android.content.Context
import be.neodigi.kotlinboilerplate.injection.ActivityContext
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @ActivityContext
    fun providesContext(): Context {
        return activity
    }
}