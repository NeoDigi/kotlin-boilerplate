package be.neodigi.kotlinboilerplate.injection.module

import android.app.Application
import android.content.Context
import be.neodigi.kotlinboilerplate.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideDebugTree(): Timber.DebugTree = Timber.DebugTree()

    @Provides
    @Singleton
    fun provideRealmConfiguration(@ApplicationContext context: Context): RealmConfiguration {
        Realm.init(context)
        val builder = RealmConfiguration.Builder()
        builder.name("boilerplate.realm")
        return builder.build()
    }

    @Provides
    fun provideRealm(realmConfiguration: RealmConfiguration): Realm {
        return Realm.getInstance(realmConfiguration)
    }
}