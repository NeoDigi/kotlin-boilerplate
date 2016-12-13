package be.neodigi.kotlinboilerplate.injection.component

import be.neodigi.kotlinboilerplate.injection.module.ActivityModule
import be.neodigi.kotlinboilerplate.injection.scope.PerActivity
import be.neodigi.kotlinboilerplate.ui.main.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun  inject(mainActivity: MainActivity)
}