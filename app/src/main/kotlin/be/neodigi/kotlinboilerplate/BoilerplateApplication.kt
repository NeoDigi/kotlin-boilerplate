package be.neodigi.kotlinboilerplate

import android.app.Application
import android.content.Context
import be.neodigi.kotlinboilerplate.injection.component.ApplicationComponent
import be.neodigi.kotlinboilerplate.injection.component.DaggerApplicationComponent
import be.neodigi.kotlinboilerplate.injection.module.ApplicationModule
import com.crashlytics.android.Crashlytics
import dagger.Lazy
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import javax.inject.Inject


class BoilerplateApplication : Application() {

    @Inject
    lateinit var debugTree: Lazy<Timber.DebugTree>

    companion object {
        lateinit var applicationComponent: ApplicationComponent
        fun get(context: Context): BoilerplateApplication {
            return context.applicationContext as BoilerplateApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        getComponent().inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(debugTree.get())
            Fabric.with(this, Crashlytics())
        }
    }

    fun getComponent(): ApplicationComponent {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        return applicationComponent
    }
}