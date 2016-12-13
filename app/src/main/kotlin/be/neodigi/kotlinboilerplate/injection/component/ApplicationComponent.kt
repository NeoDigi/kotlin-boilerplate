package be.neodigi.kotlinboilerplate.injection.component

import android.app.Application
import android.content.Context
import be.neodigi.kotlinboilerplate.BoilerplateApplication
import be.neodigi.kotlinboilerplate.data.DataManager
import be.neodigi.kotlinboilerplate.data.local.PreferencesHelper
import be.neodigi.kotlinboilerplate.data.remote.RestService
import be.neodigi.kotlinboilerplate.injection.ApplicationContext
import be.neodigi.kotlinboilerplate.injection.module.ApplicationModule
import be.neodigi.kotlinboilerplate.injection.module.RestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, RestModule::class))
interface ApplicationComponent {

    fun inject(application: BoilerplateApplication)

    @ApplicationContext fun context(): Context
    fun application(): Application
    fun restService(): RestService
    fun dataManager(): DataManager
    fun preferencesHelper(): PreferencesHelper
}